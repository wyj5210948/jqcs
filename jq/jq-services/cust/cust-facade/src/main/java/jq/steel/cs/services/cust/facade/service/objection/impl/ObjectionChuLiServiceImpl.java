package jq.steel.cs.services.cust.facade.service.objection.impl;

import com.ebase.core.AssertContext;
import com.ebase.core.page.PageDTO;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.DateFormatUtil;
import jq.steel.cs.services.cust.api.vo.ObjectionChuLiVO;
import jq.steel.cs.services.cust.api.vo.ObjectionDiaoChaVO;
import jq.steel.cs.services.cust.api.vo.ObjectionTiBaoVO;
import jq.steel.cs.services.cust.facade.dao.CrmAgreementInfoMapper;
import jq.steel.cs.services.cust.facade.dao.CrmClaimApplyMapper;
import jq.steel.cs.services.cust.facade.dao.CrmClaimInfoMapper;
import jq.steel.cs.services.cust.facade.model.CrmAgreementInfo;
import jq.steel.cs.services.cust.facade.model.CrmClaimApply;
import jq.steel.cs.services.cust.facade.model.CrmClaimInfo;
import jq.steel.cs.services.cust.facade.service.objection.ObjectionChuLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObjectionChuLiServiceImpl implements ObjectionChuLiService{

    @Autowired
    private CrmClaimApplyMapper crmClaimApplyMapper;

    @Autowired
    private CrmClaimInfoMapper crmClaimInfoMapper;

    @Autowired
    private CrmAgreementInfoMapper crmAgreementInfoMapper;

    //条件查询
    @Override
    public PageDTO<ObjectionChuLiVO> findByPage(ObjectionChuLiVO record) {
        try {
            //转换mdel
            CrmClaimInfo crmClaimInfo  = new CrmClaimInfo();
            BeanCopyUtil.copy(record,crmClaimInfo);
            PageDTOUtil.startPage(record);
            String startDtStr = DateFormatUtil.getStartDateStr(crmClaimInfo.getStartDt());
            crmClaimInfo.setStartDtStr(startDtStr);
            String endDtStr = DateFormatUtil.getEndDateStr(crmClaimInfo.getEndDt());
            crmClaimInfo.setEndDtStr(endDtStr);
            List<CrmClaimInfo> list = crmClaimInfoMapper.findByPageChuLi(crmClaimInfo);
            List<ObjectionChuLiVO> objectionDiaoChaVOS = BeanCopyUtil.copyList(list, ObjectionChuLiVO.class);
            // 分页对象
            PageDTO<ObjectionChuLiVO> transform = PageDTOUtil.transform(objectionDiaoChaVOS);
            return transform;

        }finally {
            PageDTOUtil.endPage();
        }
    }


    /**
     * 公共信息查询
     * 甲方（发货单位）：甘肃酒钢集团宏兴钢铁股份有限公司碳钢薄板厂
     * 甲方（发货单位）：甘肃酒钢集团宏兴钢铁股份有限公司炼轧厂
     * 甲方（发货单位）：甘肃酒钢集团宏兴钢铁股份有限公司不锈钢分公司
     * 甲方（发货单位）：酒钢集团榆中钢铁有限责任公司
     * 管理单位代码（1000：不锈钢厂 2000：炼轧厂 2200：碳钢薄板厂 3000：榆钢工厂
     ）
     * */
    @Override
    public ObjectionChuLiVO findAll(ObjectionChuLiVO reqbody) {
        CrmAgreementInfo  crmAgreementInfo  = new CrmAgreementInfo();
        BeanCopyUtil.copy(reqbody,crmAgreementInfo);
        crmAgreementInfo.setClaimNo(reqbody.getClaimNo());
        CrmAgreementInfo crmAgreementInfo1  = crmAgreementInfoMapper.findAll(crmAgreementInfo);
        if (crmAgreementInfo1.getDeptCode().equals(1000)){
            crmAgreementInfo1.setDeptName("甘肃酒钢集团宏兴钢铁股份有限公司不锈钢分公司");
        }else if(crmAgreementInfo1.getDeptCode().equals(2000)){
            crmAgreementInfo1.setDeptName("甘肃酒钢集团宏兴钢铁股份有限公司炼轧厂");
        }else if(crmAgreementInfo1.getDeptCode().equals(2200)){
            crmAgreementInfo1.setDeptName("甘肃酒钢集团宏兴钢铁股份有限公司碳钢薄板厂");
        }else {
            crmAgreementInfo1.setDeptName("酒钢集团榆中钢铁有限责任公司");
        }
        BeanCopyUtil.copy(crmAgreementInfo1,reqbody);
        return reqbody;
    }

    /**
     * 协议书保存/提交/审核
     * 1是保存2是提交3是驳回4是通过
     *
     * */
    @Override
    public Integer agreementUpdate(ObjectionChuLiVO record) {
        CrmAgreementInfo  crmAgreementInfo  = new CrmAgreementInfo();
        BeanCopyUtil.copy(record,crmAgreementInfo);
        if (crmAgreementInfo.getOptionStuts()== 1){
            //先查询是否有此协议书
            crmAgreementInfo.setClaimNo(record.getClaimNo());
            List<CrmAgreementInfo> list= crmAgreementInfoMapper.findList(crmAgreementInfo);
            if (list.size()>0){
                crmAgreementInfo.setUpdatedBy(AssertContext.getAcctName());
                crmAgreementInfo.setUpdatedDt(new Date());
                crmAgreementInfo.setAgreementState("EDIT");
                crmAgreementInfo.setClaimNo(record.getClaimNo());
                Integer integer =crmAgreementInfoMapper.updateByPrimaryKeySelective(crmAgreementInfo);
                return  integer;
            }else {
                crmAgreementInfo.setCreatedDt(new Date());
                crmAgreementInfo.setCreatedBy(AssertContext.getAcctName());
                crmAgreementInfo.setAgreementState("EDIT");
                crmAgreementInfo.setClaimNo(record.getClaimNo());
                Integer integer  = crmAgreementInfoMapper.insertSelective(crmAgreementInfo);
                return  integer;
            }
        }else if(crmAgreementInfo.getOptionStuts()== 2){
            crmAgreementInfo.setUpdatedBy(AssertContext.getAcctName());
            crmAgreementInfo.setUpdatedDt(new Date());
            crmAgreementInfo.setAgreementState("COMPLETE");
            crmAgreementInfo.setClaimNo(record.getClaimNo());
            Integer integer =crmAgreementInfoMapper.updateByPrimaryKeySelective(crmAgreementInfo);
            return  integer;
        }else if(crmAgreementInfo.getOptionStuts()== 3){
            crmAgreementInfo.setUpdatedBy(AssertContext.getAcctName());
            crmAgreementInfo.setUpdatedDt(new Date());
            crmAgreementInfo.setAgreementState("REJECT");
            crmAgreementInfo.setClaimNo(record.getClaimNo());
            Integer integer =crmAgreementInfoMapper.updateByPrimaryKeySelective(crmAgreementInfo);
            return  integer;
        }else {
            crmAgreementInfo.setUpdatedBy(AssertContext.getAcctName());
            crmAgreementInfo.setUpdatedDt(new Date());
            crmAgreementInfo.setAgreementState("EXAMINE");
            crmAgreementInfo.setClaimNo(record.getClaimNo());
            Integer integer =crmAgreementInfoMapper.updateByPrimaryKeySelective(crmAgreementInfo);
            return  integer;
        }
    }

    //异议处理导出
    @Override
    public List<ObjectionChuLiVO> export(List<String> list) {
        //list---->对象
        List<CrmClaimInfo> crmClaimInfos = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            CrmClaimInfo crmClaimInfo = new CrmClaimInfo();
            crmClaimInfo.setClaimNo(list.get(i));
            crmClaimInfos.add(crmClaimInfo);
        }
        List<CrmClaimInfo> crmClaimInfos1 =new ArrayList<>();
        for (CrmClaimInfo crmClaimApply1:crmClaimInfos){
            CrmClaimInfo crmClaimInfo  = new CrmClaimInfo();
            crmClaimInfo.setClaimNo(crmClaimApply1.getClaimNo());
            CrmClaimInfo crmClaimInfo1 = crmClaimInfoMapper.findByPage(crmClaimInfo);
            crmClaimInfos1.add(crmClaimInfo1);
        }
        //转换返回对象
        List<ObjectionChuLiVO> list1 = BeanCopyUtil.copyList(crmClaimInfos1,ObjectionChuLiVO.class);
        return list1;
    }

    //打印/预览 实时生成pdf并且返回url地址
    @Override
    public ObjectionChuLiVO preview(ObjectionChuLiVO record) {
        String report = "";
        String templateType = record.getTemplateType();
        CreatePdf createPdf = new CreatePdf();
        //判断
        if(templateType.equals("1")){
            createPdf.createPdf(record.getClaimNo(),record.getReport(),"xieyishu");
        }else if(templateType.equals("2")){

        }else if(templateType.equals("3")){

        }else if(templateType.equals("4")){

        }else {

        }
        //record.set
        return record;
    }

    // 协议书模板下载pdf
    public ObjectionChuLiVO download(ObjectionChuLiVO record) {
        //根据润乾报表然后地址
        return null;
    }

    // 强制结案
    @Override
    public Integer compulsorySettlement(ObjectionChuLiVO record) {
        CrmClaimApply crmClaimApply = new CrmClaimApply();
        CrmClaimInfo crmClaimInfo = new CrmClaimInfo();
        BeanCopyUtil.copy(record,crmClaimApply);
        BeanCopyUtil.copy(record,crmClaimInfo);
        crmClaimApply.setUpdatedDt(new Date());
        crmClaimApply.setUpdatedBy(AssertContext.getAcctName());
        crmClaimApply.setClaimState("END");
        crmClaimApplyMapper.update(crmClaimApply);
        crmClaimInfo.setUpdatedBy(AssertContext.getAcctName());
        crmClaimInfo.setUpdatedDt(new Date());
        crmClaimInfo.setClaimState("END");
        int i =  crmClaimInfoMapper.updateByPrimaryKeySelective(crmClaimInfo);
        return i;
    }
}
