package jq.steel.cs.webapps.cs.controller.objection;

import com.ebase.core.AssertContext;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.JsonUtil;
import com.ebase.utils.excel.ExportExcelUtils;
import com.ebase.utils.file.ZipUtils;
import feign.FeignException;
import jq.steel.cs.services.base.api.controller.RoleInfoAPI;
import jq.steel.cs.services.base.api.vo.RoleInfoVO;
import jq.steel.cs.services.cust.api.controller.ObjectionChuLiAPI;
import jq.steel.cs.services.cust.api.vo.MillSheetHostsVO;
import jq.steel.cs.services.cust.api.vo.ObjectionChuLiVO;
import jq.steel.cs.services.cust.api.vo.ObjectionDiaoChaVO;
import jq.steel.cs.webapps.cs.controller.file.UploadConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objectionChuLi")
public class ObjectionChuLiController {

    private final static Logger logger = LoggerFactory.getLogger(ObjectionChuLiController.class);


    @Autowired
    private ObjectionChuLiAPI objectionChuLiAPI;

    @Autowired
    UploadConfig uploadConfig;


    @Autowired
    private RoleInfoAPI roleInfoAPI;


    /**
     *  条件分页查询
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    public JsonResponse<PageDTO<ObjectionChuLiVO>> findByPage(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest){
        JsonResponse<PageDTO<ObjectionChuLiVO>> jsonResponse = new JsonResponse<>();
        String acctId = AssertContext.getAcctId();
        ServiceResponse<List<RoleInfoVO>>  listServiceResponse = roleInfoAPI.getRoleCodeByAcctId(acctId);
        List<String> list = new ArrayList<>();
        for (RoleInfoVO roleInfoVO:listServiceResponse.getRetContent()){
            list.add(roleInfoVO.getRoleCode());
        }
        if (list.size()>0){
            jsonRequest.getReqBody().setDeptCodes(list);
        }else {
            jsonRequest.getReqBody().setDeptCodes(null);
        }
        try {
            ServiceResponse<PageDTO<ObjectionChuLiVO>> serviceResponse = objectionChuLiAPI.findByPage(jsonRequest);
            if (ServiceResponse.SUCCESS_CODE.equals(serviceResponse.getRetCode())) {
                jsonResponse.setRspBody(serviceResponse.getRetContent());
            } else {
                if (serviceResponse.isHasError()) {
                    jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                }else {
                    jsonResponse.setRetCode(serviceResponse.getRetCode());
                    jsonResponse.setRetDesc(serviceResponse.getRetMessage());
                    return jsonResponse;
                }
            }
        } catch (BusinessException e) {
            logger.error("获取分页列表错误 = {}", e);
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }


    /**
     *  公共信息查询
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public JsonResponse<ObjectionChuLiVO> findAll(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest) {
        JsonResponse<ObjectionChuLiVO> jsonResponse = new JsonResponse<>();
        try {
            // 根据service层返回的编码做不同的操作
            ServiceResponse<ObjectionChuLiVO> response = objectionChuLiAPI.findAll(jsonRequest);
            if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
                jsonResponse.setRspBody(response.getRetContent());
                // 如果需要异常信息
            else if (response.isHasError())
                // 系统异常
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                // 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
            else if (ServiceResponse.SUCCESS_CODE.equals("1")){
                jsonResponse.setRetCode("7777777");
                jsonResponse.setRetDesc(response.getRetMessage());
            }
            else {
                // 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
                jsonResponse.setRetCode(response.getRetCode());
                jsonResponse.setRetDesc(response.getRetMessage());
            }
        } catch (FeignException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            return jsonResponse;
        }

        return jsonResponse;

    }



    /**
     *  协议书保存/提交/审核
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/agreementUpdate",method = RequestMethod.POST)
    public JsonResponse<Integer> agreementUpdate(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest) {
        JsonResponse<Integer> jsonResponse = new JsonResponse<>();
        try {
            jsonRequest.getReqBody().setOrgCode(AssertContext.getOrgCode());
            jsonRequest.getReqBody().setOrgName(AssertContext.getOrgName());
            jsonRequest.getReqBody().setAcctName(AssertContext.getAcctName());
            // 根据service层返回的编码做不同的操作
            ServiceResponse<Integer> response = objectionChuLiAPI.agreementUpdate(jsonRequest);
            if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
                jsonResponse.setRspBody(response.getRetContent());
                // 如果需要异常信息
            else if (response.isHasError())
                // 系统异常
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                // 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
            else {
                // 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
                jsonResponse.setRetCode(response.getRetCode());
                jsonResponse.setRetDesc(response.getRetMessage());
            }
        } catch (FeignException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            return jsonResponse;
        }

        return jsonResponse;

    }

    /**
     *  异议处理导出
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public JsonResponse<List<ObjectionChuLiVO>> export(@RequestParam("name") String jsonRequest) {
        JsonResponse<List<ObjectionChuLiVO>> jsonResponse = new JsonResponse<>();
        try {
            List<String> list = JsonUtil.parseObject(jsonRequest,List.class);
            JsonRequest<List<String>> jsonRequest1 = new JsonRequest();
            jsonRequest1.setReqBody(list);
            ServiceResponse<List<ObjectionChuLiVO>> response = objectionChuLiAPI.export(jsonRequest1);
            // 根据service层返回的编码做不同的操作
            if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode())) {
                jsonResponse.setRspBody(response.getRetContent());
                //转正报表
                List<String> headers = getParam();
                List<ObjectionChuLiVO> arr =  jsonResponse.getRspBody();
                try {
                    ExportExcelUtils.createExcelDownload("异议处理", "异议处理", "异议处理" +
                            System.currentTimeMillis(), headers.toArray(new String[headers.size()]), arr);

                } catch (Exception e) {
                    logger.error("error = {}",e);
                }

            }   // 如果需要异常信息
            else if (response.isHasError())
                // 系统异常
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                // 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
            else {
                // 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
                jsonResponse.setRetCode(response.getRetCode());
                jsonResponse.setRetDesc(response.getRetMessage());
            }
        } catch (FeignException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            return jsonResponse;
        }

        return jsonResponse;

    }
    private List<String> getParam() {
        List<String> headers = new ArrayList<>();
        headers.add("异议编号@claimNo@4000");
        headers.add("异议状态@claimState@4000");
        headers.add("调查报告状态@inquireState@4000");
        headers.add("协议书状态@agreementState@4000");
        headers.add("生产厂家@deptCode@4000");
        headers.add("产品大类@zcpmc@4000");
        headers.add("客户@customerName@4000");
        headers.add("牌号@zph@4000");
        headers.add("规格@specs@4000");
        headers.add("异议类别@claimType@4000");
        headers.add("异议量(吨)@objectionNum@8000");
        headers.add("使用单位@lastUser@8000");
        headers.add("提报日期@ast@4000");
        headers.add("强制结案原因@reasonsForCompulsoryClosure@8000");
        headers.add("协议书驳回原因@rejectReason@8000");
        return headers;
    }

    /**
     * 打印/预览 实时生成pdf并且返回url地址
     * */
    @RequestMapping(value = "/preview",method = RequestMethod.POST)
    public JsonResponse<ObjectionChuLiVO> preview(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest){
        logger.info("参数", JsonUtil.toJson(jsonRequest));
        CreatePdf createPdf = new CreatePdf();
        String pathPattern = uploadConfig.getPathPattern();
        JsonResponse<ObjectionChuLiVO>  jsonResponse = new JsonResponse<>();
        String createPdfPath = uploadConfig.getModelUrl();
        jsonRequest.getReqBody().setReport(createPdfPath);
        jsonRequest.getReqBody().setAcctName(AssertContext.getAcctName());
        try {
            ServiceResponse<ObjectionChuLiVO> serviceResponse = objectionChuLiAPI.preview(jsonRequest);
            String report = "";
            if (serviceResponse.getRetContent().getTemplateType()==1){
                report = uploadConfig.getDomain() +"/"+ uploadConfig.getPathPattern()+serviceResponse.getRetContent().getReport();
            }else if(jsonRequest.getReqBody().getTemplateType()==3) {
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "S.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"shoulidan");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }else if(jsonRequest.getReqBody().getTemplateType()==6){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "X.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"xieyishu");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
                //协议书下载时需要记录操作时间和操作人，多次下载记录最后一次的时间。
                jsonRequest.getReqBody().setOptionStuts(5);
                objectionChuLiAPI.agreementUpdate(jsonRequest);

            }else if(jsonRequest.getReqBody().getTemplateType()==7){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "T.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"tongzhidan");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }
            serviceResponse.getRetContent().setReport(report);
            jsonResponse.setRspBody(serviceResponse.getRetContent());
        } catch (BusinessException e) {
            logger.error("打印报错", e);
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }



    /**
     * 预览润乾 实时生成pdf并且返回url地址
     *
     * 1、质量异议报告--新建异议保存后，就出现预览报告按钮，可以调用异议报告模板，查看报告及数据。
        2、外部调查报告--外部调查保存后，就出现预览报告按钮，可以调用外部调查报告模板，查看报告及数据。
        3、内部调查报告--内部调查保存后，就出现预览报告按钮，可以调用内部调查报告模板，查看报告及数据。
        4、确认书--确认书审核界面，加预览按钮，可以直接查看确认书报告。
        5、协议书--协议书编辑和协议书审核界面，可以直接直接点击“预览”按钮来查看协议书模板及数据。
     * */
    @RequestMapping(value = "/look",method = RequestMethod.POST)
    public JsonResponse<ObjectionChuLiVO> look(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest){
        logger.info("参数", JsonUtil.toJson(jsonRequest));
        CreatePdf createPdf = new CreatePdf();
        String pathPattern = uploadConfig.getPathPattern();
        JsonResponse<ObjectionChuLiVO>  jsonResponse = new JsonResponse<>();
        String createPdfPath = uploadConfig.getModelUrl();
        jsonRequest.getReqBody().setReport(createPdfPath);
        try {
            ServiceResponse<ObjectionChuLiVO> serviceResponse = objectionChuLiAPI.look(jsonRequest);
            String report = "";
            if (jsonRequest.getReqBody().getTemplateType()==1){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "Y.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"yiyibaogao");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }else if(jsonRequest.getReqBody().getTemplateType()==2) {
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "W.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"waibudiaocha");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }else if(jsonRequest.getReqBody().getTemplateType()==3){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "N.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"neibudiaocha");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }else if(jsonRequest.getReqBody().getTemplateType()==4){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "Q.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"waibudiaocha");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }else if(jsonRequest.getReqBody().getTemplateType()==5){
                String  pdfName = jsonRequest.getReqBody().getClaimNo() + "X.pdf";
                String report1 = createPdf.createPdf(jsonRequest.getReqBody().getClaimNo() ,createPdfPath,pdfName,"xieyishu");
                String hh1 = report1.replace("/data/kf_web","/res");
                report =uploadConfig.getDomain()+hh1;
            }
            serviceResponse.getRetContent().setReport(report);
            jsonResponse.setRspBody(serviceResponse.getRetContent());
        } catch (BusinessException e) {
            logger.error("打印报错", e);
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
        }
        return jsonResponse;
    }

    /**
     *  下载 返回文件流
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public void download(@RequestParam("name") String jsonRequest,HttpServletResponse response){
        try {
            CreatePdf createPdf = new CreatePdf();
            ObjectionChuLiVO objectionChuLiVO = (ObjectionChuLiVO)JsonUtil.parseObject(jsonRequest,ObjectionChuLiVO.class);
            //下载地址
            String createPdfPath = uploadConfig.getModelUrl();
            objectionChuLiVO.setReport(createPdfPath);
            JsonRequest<ObjectionChuLiVO> jsonRequest1 = new JsonRequest();
            jsonRequest1.setReqBody(objectionChuLiVO);

            String claimNo = (String) objectionChuLiVO.getClaimNos().get(0);
            String orgName = AssertContext.getOrgName();
            String orgCode = AssertContext.getOrgCode();
            ServiceResponse<List<ObjectionChuLiVO>> serviceResponse = objectionChuLiAPI.download(jsonRequest1);
            String report ="";
            if (serviceResponse.getRetContent().size()>1){
                List<File> fileList = new ArrayList<>();
                for (int i=0;i<serviceResponse.getRetContent().size();i++){
                    fileList.add(new File(serviceResponse.getRetContent().get(i).getReport()));
                }
                try {
                    response.setHeader("Content-Disposition", "attachment;fileName="+"zhibaoshu.zip");
                    FileOutputStream fos2 = new FileOutputStream(new File("zhibaoshu.zip"));
                    ZipUtils.toZip(fileList, fos2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                report = "zhibaoshu.zip";

            }else {
                Integer templateType =  serviceResponse.getRetContent().get(0).getTemplateType();
                if (templateType ==1){
                    //协议书图片
                    report = uploadConfig.getUploadPath() + serviceResponse.getRetContent().get(0).getReport();
                    String a = serviceResponse.getRetContent().get(0).getReport();
                    String  fileName = a.substring(a.lastIndexOf("/")+1);
                    response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                }else if(templateType ==2 ){
                    String  pdfName = claimNo + "Y.pdf";
                    report = createPdf.createPdf(claimNo,createPdfPath,pdfName,"yiyibaogao");
                    String  fileName = report.substring(report.lastIndexOf("/")+1);
                    response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                }else if (templateType ==4){
                    String  pdfName = claimNo + "N.pdf";
                    report = createPdf.createPdf(claimNo,createPdfPath,pdfName,"neibudiaocha");
                    String  fileName = report.substring(report.lastIndexOf("/")+1);
                    response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                }else if(templateType ==5){
                    String  pdfName = claimNo + "W.pdf";
                    report = createPdf.createPdf(claimNo,createPdfPath,pdfName,"waibudiaocha");
                    String  fileName = report.substring(report.lastIndexOf("/")+1);
                    response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                }

            }


            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");

            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫zms.jpg,这里是设置名称)

            ServletOutputStream out=null;
            FileInputStream inputStream=null;
            File file = new File(report);
            try {
                inputStream  = new FileInputStream(file);

                //3.通过response获取ServletOutputStream对象(out)
                out = response.getOutputStream();

                int b = 0;
                byte[] buffer = new byte[512];
                while (b != -1){
                    b = inputStream.read(buffer);
                    if(b != -1){
                        out.write(buffer,0,b);//4.写到输出流(out)中
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try{
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    if(out!=null){
                        out.close();
                        out.flush();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //删除文件
            boolean success = deleteDir(new File(report));
            if (success) {
                System.out.println("Successfully deleted populated directory: " + report);
            } else {
                System.out.println("Failed to delete populated directory: " + report);
            }
        } catch (BusinessException e) {
            logger.error("下载报错", e);
            e.printStackTrace();
        }



    }

    /**
     *  强制结案
     * @param  jsonRequest
     * @return
     *
     * */
    @RequestMapping(value = "/compulsorySettlement",method = RequestMethod.POST)
    public JsonResponse<Integer> compulsorySettlement(@RequestBody JsonRequest<ObjectionChuLiVO> jsonRequest) {
        JsonResponse<Integer> jsonResponse = new JsonResponse<>();
        try {
            jsonRequest.getReqBody().setOrgCode(AssertContext.getOrgCode());
            jsonRequest.getReqBody().setOrgName(AssertContext.getOrgName());
            jsonRequest.getReqBody().setAcctName(AssertContext.getAcctName());
            // 根据service层返回的编码做不同的操作
            ServiceResponse<Integer> response = objectionChuLiAPI.compulsorySettlement(jsonRequest);
            if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
                jsonResponse.setRspBody(response.getRetContent());
                // 如果需要异常信息
            else if (response.isHasError())
                // 系统异常
                jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
                // 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
            else {
                // 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
                jsonResponse.setRetCode(response.getRetCode());
                jsonResponse.setRetDesc(response.getRetMessage());
            }
        } catch (FeignException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
            return jsonResponse;
        }

        return jsonResponse;

    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir
     *            将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     *         deletion fails, the method stops attempting to delete and returns
     *         "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
