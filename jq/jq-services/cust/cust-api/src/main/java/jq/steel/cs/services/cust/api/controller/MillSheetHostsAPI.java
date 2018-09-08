package jq.steel.cs.services.cust.api.controller;

import com.ebase.core.web.json.JsonResponse;
import jq.steel.cs.services.cust.api.vo.MillCoilInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import jq.steel.cs.services.cust.api.vo.MillSheetHostsVO;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;;import java.nio.charset.MalformedInputException;
import java.util.List;

@FeignClient(value = "${ser.name.cust}") // 这个是服务名
public interface MillSheetHostsAPI {
    /**
     * 条件分页查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value ="/millsheet/findMillSheetByPage", method = RequestMethod.POST)
    ServiceResponse<PageDTO<MillSheetHostsVO>> findMillSheetByPage(@RequestBody JsonRequest<MillSheetHostsVO> jsonRequest);

    /**
     * 查询质证书地址
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value ="/millsheet/preview", method = RequestMethod.POST)
    ServiceResponse<List<MillSheetHostsVO>> findUrl(@RequestBody JsonRequest<List<MillSheetHostsVO>> jsonRequest);

    /**
     * 查询质证书地址
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value ="/millsheet/rollbackQuery", method = RequestMethod.POST)
    ServiceResponse<MillSheetHostsVO> rollbackQuery(@RequestBody JsonRequest<MillSheetHostsVO> jsonRequest);

    /**
     * 下载返回地址
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/millsheet/downFile",method = RequestMethod.POST)
    ServiceResponse<List<MillSheetHostsVO>> downFile(@RequestBody JsonRequest<List<String>> jsonRequest);
    //ServiceResponse<List<MillSheetHostsVO>> downFile(@RequestBody JsonRequest<List<String>> jsonRequest, @RequestParam("orgName")String orgName,@RequestParam("orgCode")String orgCode);

    /**
     * 诉赔校验输入质证书正确与否
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/millsheet/findIsTrue",method = RequestMethod.POST)
    ServiceResponse<MillSheetHostsVO> findIsTrue(@RequestBody JsonRequest<MillSheetHostsVO> jsonRequest);

}
