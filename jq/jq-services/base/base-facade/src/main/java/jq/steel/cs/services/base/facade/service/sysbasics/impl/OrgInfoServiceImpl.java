package jq.steel.cs.services.base.facade.service.sysbasics.impl;

import com.ebase.core.StringHelper;
import com.ebase.core.page.PageDTO;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.math.MathHelper;
import jq.steel.cs.services.base.api.vo.MessageVO;
import jq.steel.cs.services.base.api.vo.OrgInfoVO;
import jq.steel.cs.services.base.facade.common.IsDelete;
import jq.steel.cs.services.base.facade.common.Status;
import jq.steel.cs.services.base.facade.dao.AcctInfoMapper;
import jq.steel.cs.services.base.facade.dao.OrgInfoMapper;
import jq.steel.cs.services.base.facade.model.AcctInfo;
import jq.steel.cs.services.base.facade.model.OrgInfo;
import jq.steel.cs.services.base.facade.service.message.MessageService;
import jq.steel.cs.services.base.facade.service.sysbasics.OrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class OrgInfoServiceImpl implements OrgInfoService {

	@Autowired
	private OrgInfoMapper orgInfoMapper;

	@Autowired
	private AcctInfoMapper acctInfoMapper;

	@Autowired
    private MessageService messageService;

    private static String TITLE = "酒钢客服系统注册成功通知";

    private static String SUCCESS_CODE = "shcg";

    private static String FAILE_CODE = "shsb";

	private static String AUDIT_CUST = "1000";

    /**
	 * 组织机构信息添加
	 * 传入父类ParentId添加子类OrgCode
	 */
	@Override
	public synchronized Long addOrgInfo(OrgInfo orgInfo) {
		String orgInfoId = getOrgInfoId(orgInfo.getParentId());
		orgInfo.setId(orgInfoId);
		orgInfo.setCreatedTime(new Date());
		Long l = orgInfoMapper.insertOrgInfo(orgInfo);
		return l;
	}

	/**
	 * 查询指定长度的id最大值
	 * 如果存在加一，不存在就将父节点的parentId后面拼上101
	 * 最高节点parentId为1
	 * @return
	 */
	public String getOrgInfoId(String orgCode) {
		StringBuilder result = new StringBuilder(orgCode);
		String orgInfoId = orgInfoMapper.getOrgInfoId(orgCode);
		if (StringHelper.isBlank(orgInfoId))
			orgInfoId = result.append("1001").toString();
		else
			orgInfoId = MathHelper.add(orgInfoId);
		return orgInfoId;
	}

	/**
	 * 组织机构信息刪除
	 * 判断该组织及其子类组织是否有用户绑定，
	 * 有：不准删除并抛出异常提示      没有：可以删除
	 */
	@Override
	public Integer removeOrgInfo(OrgInfo orgInfo) {
		//需要删除的组织id集合，如果有返回需要删除的组织，如果没有返回空
		List<String> cascadeDeletionOrgInfo = cascadeDeletionOrgInfo(orgInfo);
		cascadeDeletionOrgInfo.add(orgInfo.getId());
		//到用户表查询是否有用户绑定组织
		int a = acctInfoMapper.getAcctOrgid(cascadeDeletionOrgInfo);
		//批量删除组织
		int i = 0;
		if(a == 0) {
			i = orgInfoMapper.deleteOrgInfo(cascadeDeletionOrgInfo);
		}
		return i;
	}
	/**
	 * 根据父节点id级联删除下面所有的子id
	 * @param orgInfo
	 * @return
	 */
	public List<String> cascadeDeletionOrgInfo(OrgInfo orgInfo) {
		List<String> childorgInfoMapper = orgInfoMapper.getOrgInfoIdAll(orgInfo);
		return childorgInfoMapper;
	}

	/**
	 * 组织机构信息父查子
	 */
	@Override
	public List<OrgInfo> getListTreeOrgInfo(OrgInfo orgInfo) {
		//如果id有值就查子类，如果id无值就按id2查询父类id
		List<OrgInfo> childorgInfoMapper = null;
		if(orgInfo.getId() != "") {
			childorgInfoMapper = orgInfoMapper.getChildOrgInfo(orgInfo);
		}else {
			childorgInfoMapper = orgInfoMapper.selectListOrgInfoAll(orgInfo);
		}
		return childorgInfoMapper;
	}

	/**
	 * 组织机构信息递归查询
	 */
	@Override
	public OrgInfo getChildTreeOrgInfo(OrgInfo orgInfo) {

		List<OrgInfo> childorgInfoMapper = orgInfoMapper.queryChildOrgInfo(orgInfo);
		Map<String, OrgInfo> map = new HashMap<>();
		OrgInfo rootOrg = null;

		childorgInfoMapper.forEach(org -> map.put(org.getId(), org));

		for (OrgInfo org : childorgInfoMapper) {
			if (!org.getStatus().toString().equals(Status.HOLD_AUDIT.getCode()) &&
					!org.getStatus().toString().equals(Status.PASS.getCode())) {
				OrgInfo parent = map.get(org.getParentId());
				if (parent != null)
					parent.addChild(org);
				else
					rootOrg = org;
			}

		}
		return rootOrg;
	}

	/**
	 * 组织机构信息修改
	 */
	@Override
	public Integer saveOrgInfo(OrgInfo OrgInfo) {
		int i = orgInfoMapper.updateOrgInfo(OrgInfo);
		return i;
	}

	/**
	 * 组织机构信息查詢
	 */
	@Override
	public OrgInfo getOrgInfo(OrgInfo orgInfo) {
		OrgInfo selectOrgInfo = orgInfoMapper.selectOrgInfo(orgInfo);
		return selectOrgInfo;
	}

	/**
	 * 根据当前用户的组织id，查询出当前用户及当前用户一下的组织
	 * 物料综合查询用
	 * @return
	 */
	@Override
	public List<OrgInfo> getMaterielOrginfo(AcctInfo acctInfo) {
		//根据当前用户id查出当前用户绑定的组织
		String acctInfoId = acctInfoMapper.getAcctInfo(acctInfo.getAcctId());
		List<OrgInfo> listOrgInfo= orgInfoMapper.getMaterielOrginfo(acctInfoId);
		return listOrgInfo;
	}

	@Override
	public PageDTO<OrgInfoVO> getAuditOrgList(JsonRequest<OrgInfoVO> jsonRequest) {
		OrgInfoVO reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(reqBody, orgInfo);
		try {

			//可能还有更多参数
			PageDTOUtil.startPage(reqBody);
			List<OrgInfo> list = orgInfoMapper.selectAuditOrg(orgInfo);
			PageDTO<OrgInfo> page = PageDTOUtil.transform(list);

			//转换
			PageDTO<OrgInfoVO> pageVo = new PageDTO<OrgInfoVO>(page.getPageNum(),page.getPageSize());
			pageVo.setTotal(page.getTotal());
			List<OrgInfo> resultData = page.getResultData();

			List<OrgInfoVO> result = BeanCopyUtil.copyList(resultData, OrgInfoVO.class);
			pageVo.setResultData(result);
			return pageVo;
		} finally {
			PageDTOUtil.endPage();
		}
	}

	@Override
	@Transactional
	public Integer getAuditOrg(OrgInfoVO orgInfoVO) {
	    // 通过客户id获取用户信息
        AcctInfo acctInfo = acctInfoMapper.findByOrgId(orgInfoVO.getId());
        OrgInfo record = new OrgInfo();
        record.setParentId(orgInfoVO.getParentId());
        OrgInfo orgInfo = orgInfoMapper.getOrgInfo(record);
        // 发邮件
        MessageVO messageVO = new MessageVO();
        messageVO.setDestination(acctInfo.getEmail());
        Map<String, Object> map = new HashMap<>();
        map.put("username", acctInfo.getAcctTitle());
        messageVO.setVariables(map);

		String orgId = getOrgInfoId(orgInfoVO.getParentId());
		acctInfo.setoInfoId(orgId);
		acctInfo.setStatus(Byte.valueOf(Status.START.getCode()));
		acctInfo.setIsDelete(Byte.valueOf(IsDelete.NO.getCode()));
		acctInfo.setAcctType(2L);
		orgInfoVO.setOrgCode("C" + orgId);


//		} else {
//		    orgInfoVO.setStatus(Status.NOT_PASS.getCode());
//		    acctInfo.setStatus(Byte.valueOf(Status.NOT_PASS.getCode()));
//			acctInfo.setIsDelete(Byte.valueOf(IsDelete.YES.getCode()));
//
//            messageService.sendEmailWithAttachment(FAILE_CODE, TITLE, messageVO, null, null);
//        }
        int i = acctInfoMapper.updateByPrimaryKeySelective(acctInfo);

		OrgInfo orgInfo1 = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo1);
		orgInfo1.setStatus(Status.PASS.getCode());
		orgInfo1.setParentId(AUDIT_CUST);
		int j = orgInfoMapper.updateOrgInfo(orgInfo1);
		orgInfoVO.setId(orgId);
		orgInfoVO.setStatus(Status.START.getCode());
		OrgInfo orgInfo2 = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo2);
		orgInfo2.setStatus(Status.START.getCode());
		orgInfo2.setTel(orgInfo.getTel());
		Long k = orgInfoMapper.insertOrgInfo(orgInfo2);
		messageService.sendEmailWithAttachment(SUCCESS_CODE, TITLE, messageVO, null, null);
		return i;
	}


	/**
	 * 组织机构信息查詢分页
	 */
	@Override
	public  PageDTO<OrgInfoVO> getListOrgInfo(JsonRequest<OrgInfoVO> jsonRequest) {
		OrgInfoVO reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(reqBody, orgInfo);
		try {

			//可能还有更多参数
			PageDTOUtil.startPage(reqBody);
			List<OrgInfo> list = orgInfoMapper.selectListOrgInfo(orgInfo);
			PageDTO<OrgInfo> page = PageDTOUtil.transform(list);

			//转换
			PageDTO<OrgInfoVO> pageVo = new PageDTO<OrgInfoVO>(page.getPageNum(),page.getPageSize());
			pageVo.setTotal(page.getTotal());
			List<OrgInfo> resultData = page.getResultData();

			List<OrgInfoVO> result = BeanCopyUtil.copyList(resultData, OrgInfoVO.class);
			pageVo.setResultData(result);
			return pageVo;
		} finally {
			PageDTOUtil.endPage();
		}
	}


	/**
	 * 在内存中拼接出查询树
	 *//*
	@Override
	public JsonResponse<List<OrgInfo>> getListRecursionOrgInfo(JsonRequest<OrgInfoVO> jsonRequest) {
		JsonResponse<List<OrgInfo>> jsonResponse = new JsonResponse<List<OrgInfo>>();
		OrgInfoVO reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(reqBody, orgInfo);

		List<OrgInfo> orgInfos = new ArrayList<OrgInfo>();
		List<OrgInfo> allOrgInfo = orgInfoMapper.selectListOrgInfo(orgInfo);
		Map<Long, OrgInfo> orgInfoMap = new HashMap<Long, OrgInfo>();
		for ( OrgInfo orgInfo1 : allOrgInfo ) {
			orgInfoMap.put(orgInfo1.getId(), orgInfo1);
		}
		for ( OrgInfo orgInfo1 : allOrgInfo ) {
			// 子菜单
			OrgInfo child = orgInfo1;
			if ( child.getParentId() == 0 ) {
				orgInfos.add(orgInfo1);
			} else {
				// 父菜单
				OrgInfo parent = orgInfoMap.get(child.getParentId());
				// 组合菜单之间的关系
				if(parent != null) {
					parent.getChildren().add(child);
				}
			}
		}
		jsonResponse.setRspBody(orgInfos);
		return jsonResponse;
	}*/

	@Override
	public OrgInfo  selectOrgName(OrgInfoVO orgInfoVO) {
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		List<OrgInfo> list = orgInfoMapper.selectOrgName(orgInfo);
		if (list.size()>0){
			orgInfo.setTrue(true);
			return  orgInfo;
		}else {
			orgInfo.setTrue(false);
			return  orgInfo;
		}
	}
}


