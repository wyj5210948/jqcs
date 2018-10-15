package jq.steel.cs.services.cust.api.vo;

import java.util.Date;
import java.util.List;

public class CrmProductCategoryVO {
    // 产品分类ID
    private Long cid;
    // 产品分类名称
    private String categoryName;
    // 产品分类描述
    private String categoryDesc;
    // 父级分类ID
    private Long parentCid;
    // 状态 1-保存 2-提交
    private String status;

    private Long createByid;

    private String createBy;

    private Date createDt;

    private Long updateByid;

    private String updateBy;

    private Date updateDt;

    private String factory;

    private List<String> factoryCodes;

    private String isSubmit;

    private String opt;

    private int pageNum = 1;
    private int pageSize = 10;

    //工厂
    private String factory;

    public String getFactory() { return factory; }

    public void setFactory(String factory) { this.factory = factory; }


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public Long getParentCid() {
        return parentCid;
    }

    public void setParentCid(Long parentCid) {
        this.parentCid = parentCid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreateByid() {
        return createByid;
    }

    public void setCreateByid(Long createByid) {
        this.createByid = createByid;
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

    public Long getUpdateByid() {
        return updateByid;
    }

    public void setUpdateByid(Long updateByid) {
        this.updateByid = updateByid;
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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public List<String> getFactoryCodes() {
        return factoryCodes;
    }

    public void setFactoryCodes(List<String> factoryCodes) {
        this.factoryCodes = factoryCodes;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}