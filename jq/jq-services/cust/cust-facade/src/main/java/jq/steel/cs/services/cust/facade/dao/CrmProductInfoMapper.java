package jq.steel.cs.services.cust.facade.dao;

import jq.steel.cs.services.cust.facade.model.CrmProductInfo;

import java.util.List;

public interface CrmProductInfoMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(CrmProductInfo record);

    int insertSelective(CrmProductInfo record);

    CrmProductInfo selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(CrmProductInfo record);

    int updateByPrimaryKey(CrmProductInfo record);

    List<CrmProductInfo> getList(CrmProductInfo record);

    CrmProductInfo getOne(CrmProductInfo record);

    Long getMaxSortNumber();

    List<CrmProductInfo> getIntroductList(CrmProductInfo crmProductInfoVO);

    List<CrmProductInfo> getIntroductIndexList(CrmProductInfo crmProductInfo);
}