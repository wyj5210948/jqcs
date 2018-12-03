package jq.steel.cs.webapps.cs.controller.millsheet;

import com.ebase.core.AssertContext;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.JsonUtil;
import com.ebase.utils.excel.ImportExcelUtils;
import com.ebase.utils.file.FileUtil;
import com.raqsoft.expression.function.Iterate;
import jq.steel.cs.services.cust.api.controller.CrmMillSheetSplitApplyAPI;
import jq.steel.cs.services.cust.api.vo.CrmMillSheetSplitApplyVO;
import jq.steel.cs.services.cust.api.vo.CrmMillSheetSplitDetailVO;
import jq.steel.cs.services.cust.api.vo.MillSheetHostsVO;
import jq.steel.cs.webapps.cs.controller.file.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("/split")
public class CrmMillSheetSplitApplyController {
    private final static Logger logger = LoggerFactory.getLogger(CrmMillSheetSplitApplyController.class);

    @Autowired
    private CrmMillSheetSplitApplyAPI crmMillSheetSplitApplyAPI;

    /**
     * 拆分页面（强制拆分）提交按钮
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/splitInsert", method = RequestMethod.POST)
    public JsonResponse<CrmMillSheetSplitApplyVO> splitInsert(@RequestBody JsonRequest<List<CrmMillSheetSplitApplyVO>> jsonRequest) {
        logger.info("{}", JsonUtil.toJson(jsonRequest));
        JsonResponse<CrmMillSheetSplitApplyVO> jsonResponse = new JsonResponse<>();
        for (CrmMillSheetSplitApplyVO crmMillSheetSplitApplyVO : jsonRequest.getReqBody()) {
            crmMillSheetSplitApplyVO.setAcctName(AssertContext.getAcctName());
        }
        try {
            ServiceResponse<CrmMillSheetSplitApplyVO> serviceResponse = crmMillSheetSplitApplyAPI.splitInsert(jsonRequest);
            jsonResponse.setRspBody(serviceResponse.getRetContent());

        } catch (BusinessException e) {
            logger.error("提交报错", e);
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }

    /**
     * 拆分历史数据
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/splitFindAll", method = RequestMethod.POST)
    public JsonResponse<List<CrmMillSheetSplitDetailVO>> splitFindAll(@RequestBody JsonRequest<CrmMillSheetSplitDetailVO> jsonRequest) {
        logger.info("{}", JsonUtil.toJson(jsonRequest));
        JsonResponse<List<CrmMillSheetSplitDetailVO>> jsonResponse = new JsonResponse<>();
        try {
            ServiceResponse<List<CrmMillSheetSplitDetailVO>> serviceResponse = crmMillSheetSplitApplyAPI.splitFindAll(jsonRequest);
            jsonResponse.setRspBody(serviceResponse.getRetContent());
        } catch (BusinessException e) {
            logger.error("查询报错", e);
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }

        return jsonResponse;
    }


    @PostMapping("/upload")
    public JsonResponse<CrmMillSheetSplitApplyVO> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        JsonRequest<List<CrmMillSheetSplitApplyVO>> jsonRequest = new JsonRequest<>();
        JsonResponse<CrmMillSheetSplitApplyVO> jsonResponse = new JsonResponse<CrmMillSheetSplitApplyVO>();

        if (null != file) {
            try {
                Map<Integer, Map<Integer, Object>> map = new HashMap<>();
                try {
                    map = ImportExcelUtils.readExcelContentz(file);
                    if (map.size() > 0) {
                        //计数+校验每条数据
                        int a = 0;
                        for (int i = 1; i <= map.size(); i++) {
                            Map<Integer, Object> mapItem = map.get(i);
                            if (!mapItem.get(0).equals("")) {
                                a++;
                            }
                        }
                        String b = "";
                        for (int i = 1; i <= a; i++) {
                            Map<Integer, Object> mapItem = map.get(i);
                            for (int j = 0; j < mapItem.size(); j++) {
                                if (map.get(i).get(j).equals("")) {
                                    b = "b";
                                }
                            }
                        }
                        if (a > 0) {
                            List<CrmMillSheetSplitApplyVO> applyVOS = new ArrayList<>();
                            for (int i = 1; i <= a; i++) {
                                CrmMillSheetSplitApplyVO crmMillSheetSplitApplyVO = new CrmMillSheetSplitApplyVO();
                                List<String> arrayList = new ArrayList<>();
                                Map<Integer, Object> mapItem = map.get(i);
                                if (mapItem.size() == 6) {
                                    if (b.equals("")) {
                                        for (int j = 0; j < mapItem.size(); j++) {
                                            arrayList.add((String) map.get(i).get(j));
                                        }
                                        crmMillSheetSplitApplyVO.setAcctName(AssertContext.getAcctName());
                                        crmMillSheetSplitApplyVO.setMillsheetNo(arrayList.get(0));
                                        crmMillSheetSplitApplyVO.setZchehao(arrayList.get(1));
                                        String jianshu = "";
                                        if (arrayList.get(2).indexOf(".") > 0) {
                                            jianshu = arrayList.get(2).substring(0, arrayList.get(2).lastIndexOf("."));
                                            ;
                                        } else {
                                            jianshu = arrayList.get(2);
                                        }
                                        String zcharg = "";
                                        if (arrayList.get(3).indexOf(".") >= 0) {
                                            zcharg = arrayList.get(3).replace(".", "");
                                        } else {
                                            zcharg = arrayList.get(3);
                                        }
                                        crmMillSheetSplitApplyVO.setZjishu(Long.valueOf(jianshu));
                                        crmMillSheetSplitApplyVO.setZcharg(zcharg);
                                        crmMillSheetSplitApplyVO.setSpecs(arrayList.get(4));
                                        crmMillSheetSplitApplyVO.setSpiltCustomer(arrayList.get(5));
                                        applyVOS.add(crmMillSheetSplitApplyVO);
                                    } else {
                                        jsonResponse.setRetCode("0000001");
                                        jsonResponse.setRetDesc("请补充模板中数据，保证数据完整性");
                                    }
                                } else {
                                    jsonResponse.setRetCode("0000001");
                                    jsonResponse.setRetDesc("excel中数据不完善");
                                    return jsonResponse;
                                }
                            }
                            jsonRequest.setReqBody(applyVOS);
                            ServiceResponse<CrmMillSheetSplitApplyVO> serviceResponse = crmMillSheetSplitApplyAPI.splitInsertAll(jsonRequest);
                            if (serviceResponse.getRetContent().getCode() > 0) {
                                jsonResponse.setRetCode("0000001");
                                jsonResponse.setRetDesc(serviceResponse.getRetContent().getMessage());
                            } else {
                                jsonResponse.setRetCode("0000001");
                                jsonResponse.setRetDesc(serviceResponse.getRetContent().getMessage());
                            }
                        } else {
                            jsonResponse.setRetCode("0000001");
                            jsonResponse.setRetDesc("请在模板中输入有效数据");
                        }
                    } else {
                        jsonResponse.setRetCode("0000001");
                        jsonResponse.setRetDesc("请在模板中输入有效数据");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                logger.error("异常：", e);
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            }
        } else {
            logger.info("上传文件不能为空");
        }

        return jsonResponse;
    }



    @PostMapping("/uploadNeed")
    public JsonResponse<CrmMillSheetSplitApplyVO> uploadNeed(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        JsonRequest<List<CrmMillSheetSplitApplyVO>> jsonRequest = new JsonRequest<>();
        JsonResponse<CrmMillSheetSplitApplyVO> jsonResponse = new JsonResponse<CrmMillSheetSplitApplyVO>();
        if (null != file) {
            try {
                Map<Integer, Map<Integer, Object>> map = new HashMap<>();
                try {
                    map = ImportExcelUtils.readExcelContentz(file);
                    if (map.size() > 0) {
                        //计数+校验每条数据
                        int a = 0;
                        for (int i = 1; i <= map.size(); i++) {
                            Map<Integer, Object> mapItem = map.get(i);
                            if (!mapItem.get(0).equals("")) {
                                a++;
                            }
                        }
                        String b = "";
                        for (int i = 1; i <= a; i++) {
                            Map<Integer, Object> mapItem = map.get(i);
                            for (int j = 0; j < mapItem.size(); j++) {
                                if (map.get(i).get(j).equals("")) {
                                   // b = "b";
                                }
                            }
                        }
                        if (a > 0) {
                            List<CrmMillSheetSplitApplyVO> applyVOS = new ArrayList<>();
                            for (int i = 1; i <= a; i++) {
                                CrmMillSheetSplitApplyVO crmMillSheetSplitApplyVO = new CrmMillSheetSplitApplyVO();
                                List<String> arrayList = new ArrayList<>();
                                Map<Integer, Object> mapItem = map.get(i);
                                if (mapItem.size() == 3) {
                                    if (b.equals("")) {
                                        for (int j = 0; j < mapItem.size(); j++) {
                                            arrayList.add((String) map.get(i).get(j));
                                        }
                                        crmMillSheetSplitApplyVO.setAcctName(AssertContext.getAcctName());
                                        String zcharg = "";
                                        if (arrayList.get(0).indexOf(".") >= 0) {
                                            zcharg = arrayList.get(0).replace(".", "");
                                        } else {
                                            zcharg = arrayList.get(0);
                                        }
                                        crmMillSheetSplitApplyVO.setZcharg(zcharg);
                                        crmMillSheetSplitApplyVO.setSpiltCustomer(arrayList.get(1));
                                        crmMillSheetSplitApplyVO.setZchehao(arrayList.get(2));
                                        applyVOS.add(crmMillSheetSplitApplyVO);
                                    } else {
                                        jsonResponse.setRetCode("0000001");
                                        jsonResponse.setRetDesc("请补充模板中数据，保证数据完整性");
                                    }
                                } else {
                                    jsonResponse.setRetCode("0000001");
                                    jsonResponse.setRetDesc("excel中数据不完善");
                                    return jsonResponse;
                                }
                            }
                            jsonRequest.setReqBody(applyVOS);
                            ServiceResponse<CrmMillSheetSplitApplyVO> serviceResponse = crmMillSheetSplitApplyAPI.splitInsertSpecial(jsonRequest);
                            if (serviceResponse.getRetContent().getCode() > 0) {
                                jsonResponse.setRetCode("0000001");
                                jsonResponse.setRetDesc(serviceResponse.getRetContent().getMessage());
                            } else {
                                jsonResponse.setRetCode("0000001");
                                jsonResponse.setRetDesc(serviceResponse.getRetContent().getMessage());
                            }
                        } else {
                            jsonResponse.setRetCode("0000001");
                            jsonResponse.setRetDesc("请在模板中输入有效数据");
                        }
                    } else {
                        jsonResponse.setRetCode("0000001");
                        jsonResponse.setRetDesc("请在模板中输入有效数据");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                logger.error("异常：", e);
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            }
        } else {
            logger.info("上传文件不能为空");
        }

        return jsonResponse;
    }
}
