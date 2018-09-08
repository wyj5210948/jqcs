package jq.steel.cs.services.cust.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MillCoilInfoVO implements Serializable {

    private String spiltCustomer;

    public String getSpiltCustomer() {
        return spiltCustomer;
    }

    public void setSpiltCustomer(String spiltCustomer) {
        this.spiltCustomer = spiltCustomer;
    }

    private  String orgCode;

    private  String orgName;

    private int pageNum = 1;

    private int pageSize = 10;

    private String millsheetType;
    private String splitMaxValue;
    private String zcpmc;
    private String zkunnr;
    private String zkunwe;
    private Long sid;

    private String zchehao;

    private String zlph;

    private String zcharg;

    private String specs;

    private BigDecimal zdiameter;

    private BigDecimal zthick;

    private BigDecimal zwidth;

    private BigDecimal zlength;

    private Long zjishu;

    private BigDecimal zlosmenge;

    private String zjishuUnit;

    private String zmeins;

    private String zdczt;

    private String zjhzt;

    private String millsheetNo;

    private String zbmjd;

    private String zbzfs;

    private String ztsbz;

    private String zhdpc;

    private String createdBy;

    private Date createdDt;

    private String updatedBy;

    private Date updatedDt;

    private Short version;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getSplitMaxValue() {
        return splitMaxValue;
    }

    public void setSplitMaxValue(String splitMaxValue) {
        this.splitMaxValue = splitMaxValue;
    }

    public String getZcpmc() {
        return zcpmc;
    }

    public void setZcpmc(String zcpmc) {
        this.zcpmc = zcpmc;
    }

    public String getZkunnr() {
        return zkunnr;
    }

    public void setZkunnr(String zkunnr) {
        this.zkunnr = zkunnr;
    }

    public String getZkunwe() {
        return zkunwe;
    }

    public void setZkunwe(String zkunwe) {
        this.zkunwe = zkunwe;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getZchehao() {
        return zchehao;
    }

    public void setZchehao(String zchehao) {
        this.zchehao = zchehao == null ? null : zchehao.trim();
    }

    public String getZlph() {
        return zlph;
    }

    public void setZlph(String zlph) {
        this.zlph = zlph == null ? null : zlph.trim();
    }

    public String getZcharg() {
        return zcharg;
    }

    public void setZcharg(String zcharg) {
        this.zcharg = zcharg == null ? null : zcharg.trim();
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs == null ? null : specs.trim();
    }

    public BigDecimal getZdiameter() {
        return zdiameter;
    }

    public void setZdiameter(BigDecimal zdiameter) {
        this.zdiameter = zdiameter;
    }

    public BigDecimal getZthick() {
        return zthick;
    }

    public void setZthick(BigDecimal zthick) {
        this.zthick = zthick;
    }

    public BigDecimal getZwidth() {
        return zwidth;
    }

    public void setZwidth(BigDecimal zwidth) {
        this.zwidth = zwidth;
    }

    public BigDecimal getZlength() {
        return zlength;
    }

    public void setZlength(BigDecimal zlength) {
        this.zlength = zlength;
    }

    public Long getZjishu() {
        return zjishu;
    }

    public void setZjishu(Long zjishu) {
        this.zjishu = zjishu;
    }

    public BigDecimal getZlosmenge() {
        return zlosmenge;
    }

    public void setZlosmenge(BigDecimal zlosmenge) {
        this.zlosmenge = zlosmenge;
    }

    public String getZjishuUnit() {
        return zjishuUnit;
    }

    public void setZjishuUnit(String zjishuUnit) {
        this.zjishuUnit = zjishuUnit == null ? null : zjishuUnit.trim();
    }

    public String getZmeins() {
        return zmeins;
    }

    public void setZmeins(String zmeins) {
        this.zmeins = zmeins == null ? null : zmeins.trim();
    }

    public String getZdczt() {
        return zdczt;
    }

    public void setZdczt(String zdczt) {
        this.zdczt = zdczt == null ? null : zdczt.trim();
    }

    public String getZjhzt() {
        return zjhzt;
    }

    public void setZjhzt(String zjhzt) {
        this.zjhzt = zjhzt == null ? null : zjhzt.trim();
    }

    public String getMillsheetType() {
        return millsheetType;
    }

    public void setMillsheetType(String millsheetType) {
        this.millsheetType = millsheetType;
    }

    public String getMillsheetNo() {
        return millsheetNo;
    }

    public void setMillsheetNo(String millsheetNo) {
        this.millsheetNo = millsheetNo;
    }

    public String getZbmjd() {
        return zbmjd;
    }

    public void setZbmjd(String zbmjd) {
        this.zbmjd = zbmjd == null ? null : zbmjd.trim();
    }

    public String getZbzfs() {
        return zbzfs;
    }

    public void setZbzfs(String zbzfs) {
        this.zbzfs = zbzfs == null ? null : zbzfs.trim();
    }

    public String getZtsbz() {
        return ztsbz;
    }

    public void setZtsbz(String ztsbz) {
        this.ztsbz = ztsbz == null ? null : ztsbz.trim();
    }

    public String getZhdpc() {
        return zhdpc;
    }

    public void setZhdpc(String zhdpc) {
        this.zhdpc = zhdpc == null ? null : zhdpc.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }
}