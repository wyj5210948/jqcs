package jq.steel.cs.services.cust.facade.model;

import java.math.BigDecimal;
import java.util.Date;

public class MillChemistryData {
    private Long sid;

    private String zchehao;

    private String millSheetNo;

    private String zlph;

    private String zcharg;

    private String specs;

    private String zmatnr;

    private String specCode;

    private Long prueflosHx;

    private String errCheck;

    private Short seqNo;

    private String verwmerkm;

    private BigDecimal roundTestValue;

    private String kurztext;

    private BigDecimal originalInput;

    private String masseinhsw;

    private String maschine;

    private Short showFlag;

    private String createdBy;

    private Date createdDt;

    private String updatedBy;

    private Date updatedDt;

    private Short version;

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

    public String getMillSheetNo() {
        return millSheetNo;
    }

    public void setMillSheetNo(String millSheetNo) {
        this.millSheetNo = millSheetNo == null ? null : millSheetNo.trim();
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

    public String getZmatnr() {
        return zmatnr;
    }

    public void setZmatnr(String zmatnr) {
        this.zmatnr = zmatnr == null ? null : zmatnr.trim();
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    public Long getPrueflosHx() {
        return prueflosHx;
    }

    public void setPrueflosHx(Long prueflosHx) {
        this.prueflosHx = prueflosHx;
    }

    public String getErrCheck() {
        return errCheck;
    }

    public void setErrCheck(String errCheck) {
        this.errCheck = errCheck == null ? null : errCheck.trim();
    }

    public Short getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Short seqNo) {
        this.seqNo = seqNo;
    }

    public String getVerwmerkm() {
        return verwmerkm;
    }

    public void setVerwmerkm(String verwmerkm) {
        this.verwmerkm = verwmerkm == null ? null : verwmerkm.trim();
    }

    public BigDecimal getRoundTestValue() {
        return roundTestValue;
    }

    public void setRoundTestValue(BigDecimal roundTestValue) {
        this.roundTestValue = roundTestValue;
    }

    public String getKurztext() {
        return kurztext;
    }

    public void setKurztext(String kurztext) {
        this.kurztext = kurztext == null ? null : kurztext.trim();
    }

    public BigDecimal getOriginalInput() {
        return originalInput;
    }

    public void setOriginalInput(BigDecimal originalInput) {
        this.originalInput = originalInput;
    }

    public String getMasseinhsw() {
        return masseinhsw;
    }

    public void setMasseinhsw(String masseinhsw) {
        this.masseinhsw = masseinhsw == null ? null : masseinhsw.trim();
    }

    public String getMaschine() {
        return maschine;
    }

    public void setMaschine(String maschine) {
        this.maschine = maschine == null ? null : maschine.trim();
    }

    public Short getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Short showFlag) {
        this.showFlag = showFlag;
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