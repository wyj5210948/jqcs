package jq.steel.cs.services.cust.facade.service.objection;

import com.ebase.core.page.PageDTO;
import jq.steel.cs.services.cust.api.vo.ObjectionChuLiVO;

import java.util.List;

public interface ObjectionChuLiService {

    //分页条件查询
    PageDTO<ObjectionChuLiVO> findByPage(ObjectionChuLiVO objectionTiBaoVO);

    //公共信息查询
    ObjectionChuLiVO findAll(ObjectionChuLiVO objectionTiBaoVO);

    //协议书保存/提交/审核
    Integer agreementUpdate(ObjectionChuLiVO record);

    //异议处理导出
    List<ObjectionChuLiVO> export (List<String> record);

    //打印预览
    ObjectionChuLiVO preview(ObjectionChuLiVO record);

    // 下载pdf
    ObjectionChuLiVO download(ObjectionChuLiVO record);

    //强制结案
    Integer compulsorySettlement(ObjectionChuLiVO record);
}
