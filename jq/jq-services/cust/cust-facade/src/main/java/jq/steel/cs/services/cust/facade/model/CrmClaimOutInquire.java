package jq.steel.cs.services.cust.facade.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CrmClaimOutInquire {

    private String manufactor;

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    private Long sid;

    private String claimNo;

    private String logisticsProcess;

    private String endProcessingTech;

    private String defectName;

    private Date productDt;

    private String shift;

    private String fieldConclusion;

    private String userRequirement;

    private String handingSuggestion;

    private String inquireState;

    private String followReason;

    private String createBy;

    private Date createDt;

    private String updateBy;

    private Date updateDt;

    private Integer version;

    private String inquireInfo;

    private Integer amoutOfUse;


    /*********************************************************************************/



    private Long productId;

    private String productName;

    private String millSheetNo;

    private String customerId;

    private String customerName;

    private String custAddr;

    private String custEmpNo;

    private String custTel;

    private String lastUserId;

    private String lastUser;

    private String lastUserAddr;

    private String createEmpNo;

    private String lastUserTel;

    private String lastUserEmail;

    private String battenPlateNo;

    private String designation;

    private String used;

    private String contractNo;

    private BigDecimal contractVolume;

    private String sizeMark;

    private BigDecimal originalWeight;

    private String orderNo;

    private String originalCarNo;

    private BigDecimal contractUnitPrice;

    private BigDecimal objectionNum;

    private String claimDesc;

    private String claimReason;

    private String steelType;

    private String proProblem;

    private String proDetail;

    private String claimState;

    private String filePath;

    private String rejectReason;



    private String claimType;

    private Integer optionType;

    private Integer dissentingUnit;

    //受理时间
    private Date admissibilityTime;

    //提报时间
    private Date presentationDate;

    //受理人
    private  String admissibilityUser;

    //提报人
    private  String presentationUser;

    //强制结案原因
    private  String reasonsForCompulsoryClosure;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDt;
    private String endDtStr;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDt;
    private String startDtStr;

    /*************************************************************************************/

    //外部调查人
    private String externalLnvestigator;
    //外部调查时间
    private  Date externalLnvestigationDate;
    //内部调查人
    private  String internalLnvestigator;
    //内部调查时间
    private Date internalLnvestigationDate;


    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getEndDtStr() {
        return endDtStr;
    }

    public void setEndDtStr(String endDtStr) {
        this.endDtStr = endDtStr;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public String getStartDtStr() {
        return startDtStr;
    }

    public void setStartDtStr(String startDtStr) {
        this.startDtStr = startDtStr;
    }

    public Integer getAmoutOfUse() {
        return amoutOfUse;
    }

    public void setAmoutOfUse(Integer amoutOfUse) {
        this.amoutOfUse = amoutOfUse;
    }

    public String getExternalLnvestigator() {
        return externalLnvestigator;
    }

    public void setExternalLnvestigator(String externalLnvestigator) {
        this.externalLnvestigator = externalLnvestigator;
    }

    public Date getExternalLnvestigationDate() {
        return externalLnvestigationDate;
    }

    public void setExternalLnvestigationDate(Date externalLnvestigationDate) {
        this.externalLnvestigationDate = externalLnvestigationDate;
    }

    public String getInternalLnvestigator() {
        return internalLnvestigator;
    }

    public void setInternalLnvestigator(String internalLnvestigator) {
        this.internalLnvestigator = internalLnvestigator;
    }

    public Date getInternalLnvestigationDate() {
        return internalLnvestigationDate;
    }

    public void setInternalLnvestigationDate(Date internalLnvestigationDate) {
        this.internalLnvestigationDate = internalLnvestigationDate;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo == null ? null : claimNo.trim();
    }

    public String getLogisticsProcess() {
        return logisticsProcess;
    }

    public void setLogisticsProcess(String logisticsProcess) {
        this.logisticsProcess = logisticsProcess == null ? null : logisticsProcess.trim();
    }

    public String getEndProcessingTech() {
        return endProcessingTech;
    }

    public void setEndProcessingTech(String endProcessingTech) {
        this.endProcessingTech = endProcessingTech == null ? null : endProcessingTech.trim();
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName == null ? null : defectName.trim();
    }

    public Date getProductDt() {
        return productDt;
    }

    public void setProductDt(Date productDt) {
        this.productDt = productDt;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift == null ? null : shift.trim();
    }

    public String getFieldConclusion() {
        return fieldConclusion;
    }

    public void setFieldConclusion(String fieldConclusion) {
        this.fieldConclusion = fieldConclusion == null ? null : fieldConclusion.trim();
    }

    public String getUserRequirement() {
        return userRequirement;
    }

    public void setUserRequirement(String userRequirement) {
        this.userRequirement = userRequirement == null ? null : userRequirement.trim();
    }

    public String getHandingSuggestion() {
        return handingSuggestion;
    }

    public void setHandingSuggestion(String handingSuggestion) {
        this.handingSuggestion = handingSuggestion == null ? null : handingSuggestion.trim();
    }

    public String getInquireState() {
        return inquireState;
    }

    public void setInquireState(String inquireState) {
        this.inquireState = inquireState == null ? null : inquireState.trim();
    }

    public String getFollowReason() {
        return followReason;
    }

    public void setFollowReason(String followReason) {
        this.followReason = followReason == null ? null : followReason.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getInquireInfo() {
        return inquireInfo;
    }

    public void setInquireInfo(String inquireInfo) {
        this.inquireInfo = inquireInfo == null ? null : inquireInfo.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMillSheetNo() {
        return millSheetNo;
    }

    public void setMillSheetNo(String millSheetNo) {
        this.millSheetNo = millSheetNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getCustEmpNo() {
        return custEmpNo;
    }

    public void setCustEmpNo(String custEmpNo) {
        this.custEmpNo = custEmpNo;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getLastUserAddr() {
        return lastUserAddr;
    }

    public void setLastUserAddr(String lastUserAddr) {
        this.lastUserAddr = lastUserAddr;
    }

    public String getCreateEmpNo() {
        return createEmpNo;
    }

    public void setCreateEmpNo(String createEmpNo) {
        this.createEmpNo = createEmpNo;
    }

    public String getLastUserTel() {
        return lastUserTel;
    }

    public void setLastUserTel(String lastUserTel) {
        this.lastUserTel = lastUserTel;
    }

    public String getLastUserEmail() {
        return lastUserEmail;
    }

    public void setLastUserEmail(String lastUserEmail) {
        this.lastUserEmail = lastUserEmail;
    }

    public String getBattenPlateNo() {
        return battenPlateNo;
    }

    public void setBattenPlateNo(String battenPlateNo) {
        this.battenPlateNo = battenPlateNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getContractVolume() {
        return contractVolume;
    }

    public void setContractVolume(BigDecimal contractVolume) {
        this.contractVolume = contractVolume;
    }

    public String getSizeMark() {
        return sizeMark;
    }

    public void setSizeMark(String sizeMark) {
        this.sizeMark = sizeMark;
    }

    public BigDecimal getOriginalWeight() {
        return originalWeight;
    }

    public void setOriginalWeight(BigDecimal originalWeight) {
        this.originalWeight = originalWeight;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOriginalCarNo() {
        return originalCarNo;
    }

    public void setOriginalCarNo(String originalCarNo) {
        this.originalCarNo = originalCarNo;
    }

    public BigDecimal getContractUnitPrice() {
        return contractUnitPrice;
    }

    public void setContractUnitPrice(BigDecimal contractUnitPrice) {
        this.contractUnitPrice = contractUnitPrice;
    }

    public BigDecimal getObjectionNum() {
        return objectionNum;
    }

    public void setObjectionNum(BigDecimal objectionNum) {
        this.objectionNum = objectionNum;
    }

    public String getClaimDesc() {
        return claimDesc;
    }

    public void setClaimDesc(String claimDesc) {
        this.claimDesc = claimDesc;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getSteelType() {
        return steelType;
    }

    public void setSteelType(String steelType) {
        this.steelType = steelType;
    }

    public String getProProblem() {
        return proProblem;
    }

    public void setProProblem(String proProblem) {
        this.proProblem = proProblem;
    }

    public String getProDetail() {
        return proDetail;
    }

    public void setProDetail(String proDetail) {
        this.proDetail = proDetail;
    }

    public String getClaimState() {
        return claimState;
    }

    public void setClaimState(String claimState) {
        this.claimState = claimState;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    public Integer getDissentingUnit() {
        return dissentingUnit;
    }

    public void setDissentingUnit(Integer dissentingUnit) {
        this.dissentingUnit = dissentingUnit;
    }

    public Date getAdmissibilityTime() {
        return admissibilityTime;
    }

    public void setAdmissibilityTime(Date admissibilityTime) {
        this.admissibilityTime = admissibilityTime;
    }

    public Date getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(Date presentationDate) {
        this.presentationDate = presentationDate;
    }

    public String getAdmissibilityUser() {
        return admissibilityUser;
    }

    public void setAdmissibilityUser(String admissibilityUser) {
        this.admissibilityUser = admissibilityUser;
    }

    public String getPresentationUser() {
        return presentationUser;
    }

    public void setPresentationUser(String presentationUser) {
        this.presentationUser = presentationUser;
    }

    public String getReasonsForCompulsoryClosure() {
        return reasonsForCompulsoryClosure;
    }

    public void setReasonsForCompulsoryClosure(String reasonsForCompulsoryClosure) {
        this.reasonsForCompulsoryClosure = reasonsForCompulsoryClosure;
    }
}