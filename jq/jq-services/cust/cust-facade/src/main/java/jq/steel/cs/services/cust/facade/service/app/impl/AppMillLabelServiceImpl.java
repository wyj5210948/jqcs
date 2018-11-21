package jq.steel.cs.services.cust.facade.service.app.impl;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.DateFormatUtil;
import com.ebase.utils.JsonUtil;
import jq.steel.cs.services.cust.api.vo.CrmMillCoilInfoVO;
import jq.steel.cs.services.cust.api.vo.MillLabelVO;
import jq.steel.cs.services.cust.api.vo.MillSheetHostsVO;
import jq.steel.cs.services.cust.facade.dao.MillCoilInfoMapper;
import jq.steel.cs.services.cust.facade.dao.MillLabelMapper;
import jq.steel.cs.services.cust.facade.dao.MillSheetHostsMapper;
import jq.steel.cs.services.cust.facade.model.CrmMillCoilInfo;
import jq.steel.cs.services.cust.facade.model.MillLabel;
import jq.steel.cs.services.cust.facade.model.MillSheetHosts;
import jq.steel.cs.services.cust.facade.service.app.AppMillLabelService;
import jq.steel.cs.services.cust.facade.service.millsheet.MillCoilInfoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * dal Interface:MillLabel
 *
 * @author
 * @date 2018-9-19
 */
@Service
@Transactional
public class AppMillLabelServiceImpl implements AppMillLabelService {

    @Autowired
    private MillLabelMapper millLabelMapper;
    @Autowired
    private MillCoilInfoService millCoilInfoService;


    public List<MillLabelVO> selectAll() {
        //List<MillLabel> millLabels = millLabelMapper.selectAll();
        //List<MillLabelVO> millLabelVOs = BeanCopyUtil.copyList(millLabels, MillLabelVO.class);
        //return millLabelVOs;
        return null;
    }

    public List<MillLabelVO> select(MillLabelVO record) {

        MillLabel model = BeanCopyUtil.copy(record, MillLabel.class);
        List<MillLabel> millLabels = millLabelMapper.select(model);
        List<MillLabelVO> millLabelVOs = BeanCopyUtil.copyList(millLabels, MillLabelVO.class);
        return millLabelVOs;
    }

    public MillLabelVO selectByPrimaryKey(Long key) {
        MillLabel millLabel = millLabelMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(millLabel, MillLabelVO.class);
    }

    public Integer insert(MillLabelVO record) {
        MillLabel millLabel = BeanCopyUtil.copy(record, MillLabel.class);
        return millLabelMapper.insert(millLabel);

    }

    public Integer insertSelective(MillLabelVO record) {
        MillLabel millLabel = BeanCopyUtil.copy(record, MillLabel.class);
        return millLabelMapper.insertSelective(millLabel);
    }

    public Integer updateByPrimaryKey(MillLabelVO record) {
        MillLabel millLabel = BeanCopyUtil.copy(record, MillLabel.class);
        return millLabelMapper.updateByPrimaryKey(millLabel);
    }

    public Integer updateByPrimaryKeySelective(MillLabelVO record) {
        MillLabel millLabel = BeanCopyUtil.copy(record, MillLabel.class);
        return millLabelMapper.updateByPrimaryKeySelective(millLabel);
    }

    public Integer deleteByPrimaryKey(Long key) {
        return millLabelMapper.deleteByPrimaryKey(key);
    }

    /**
     * IN
     * <foreach collection="keys" open="(" close=")" item="key" separator=",">
     * {key}
     * </foreach>
     */
    public Integer deleteByPrimaryKeys(Set<Long> keys) {
        //return millLabelMapper.deleteByPrimaryKeys(keys);
        return null;
    }


	/*public Integer keep(List<MillLabelVO> millLabelVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (MillLabelVO millLabelVO : millLabelVOs) {
			String opt = millLabelVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(millLabelVO.getId());
				int i = deleteByPrimaryKey(millLabelVO.getId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(millLabelVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(millLabelVO);
				if (i > 0) {
					result++;
				}
			}
		}
//		if(!keys.isEmpty())
//			result = result + deleteByPrimaryKeys(keys);
		return result;
	}*/

    /**
     * 根据二维码信息查询数据
     *
     * @param jsonRequest " 榆中县 04 甲\n2017-07-15 13:17\nHRB400E\nф14\n170708101  46\n123支"
     *                    "  2 甲\n2018-11-19 12:21\n\n热轧带肋钢筋\nHRB400E\nΦ10\nN8111515  8\n"
     * @return
     */
    @Transactional(readOnly = true)
    public List<CrmMillCoilInfoVO> queryByQrCode(JsonRequest<String> jsonRequest) {
        String str = jsonRequest.getReqBody();
        String[] strs = str.split("\n");
        int l = strs.length;
        MillLabelVO vo = new MillLabelVO();
        for (int i = 0; i < l; i++) {
            //操作员id
            if(i == 0){
                //通过判断数字型字符串的长度获取到某一字符串中的数字
                String operatorId = strs[i];
                operatorId = operatorId.trim();
                String str2 = "";
                if(str != null && !"".equals(str)){
                    for(int j = 0 ; j < operatorId.length() ; j++){
                        if(operatorId.charAt(j) >= 48 && operatorId.charAt(j) <= 57){
                            str2+= operatorId.charAt(j);
                        }
                    }
                }
                int index = str2.lastIndexOf("0");
                if(index == 0){
                    String str3 = str2.substring(1);
                    vo.setOperatorId(str3);
                }else{
                    vo.setOperatorId(str2);
                }
            }
            //生产时间
            if (i == 1) {
                String strDate = strs[i];
                vo.setProductionTimeStr(strDate);
            }
            //牌号
            if (i == 4) {
                String strZph = strs[i];
                vo.setZph(strZph);
            }
            //规格
            if (i == 5) {
                String strSpecs = strs[i];
                vo.setSpecs(strSpecs);
            }
            //批次
            if (i == 6) {
                String strZcharg1 = strs[i];
                String[] strs2 = strZcharg1.split("  ");
                vo.setZcharg(strs2[0]);
                //卷号
                vo.setCoilId(strs2[1]);
            }
        }

        if(vo.getOperatorId() == null
                || vo.getProductionTimeStr() == null
                || vo.getZph() == null
                || vo.getSpecs() == null
                || vo.getZcharg() == null
                || vo.getCoilId() == null
        ){
            throw new BusinessException("此二维码信息有误");
        }
        List<MillLabel> millLabels = millLabelMapper.queryByQrcode(vo);
        //假信息,如果没有对应数据返回一个状态
        if (millLabels == null) {
            CrmMillCoilInfoVO crmMillCoilInfoVO = new CrmMillCoilInfoVO();
            crmMillCoilInfoVO.setState("0");
            List<CrmMillCoilInfoVO> list = new ArrayList<>();
            list.add(crmMillCoilInfoVO);
            return list;
        }
        //真信息,如果有数据执行另外一条sql,用于返回质证书结构化数据
        List<CrmMillCoilInfoVO> list = new ArrayList<>();
        for (MillLabel MillLabel : millLabels) {
            CrmMillCoilInfoVO crmMillCoilInfoVO = new CrmMillCoilInfoVO();
            crmMillCoilInfoVO.setZcharg(MillLabel.getZcharg());
            crmMillCoilInfoVO.setShowFlag(1);
            list = millCoilInfoService.getCoilDetail(crmMillCoilInfoVO);
            for (CrmMillCoilInfoVO voForList : list) {
                voForList.setState("1");
            }
        }
        return list;
    }
}