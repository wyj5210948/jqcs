package jq.steel.cs.services.cust.api.controller;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import jq.steel.cs.services.cust.api.vo.ObjectionLedgerVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${ser.name.cust}") // 这个是服务名
public interface ObjectionLendgerAPI {

    /**
     * 条件分页查询
     *
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/objectionLendger/findLedgerByPage", method = RequestMethod.POST)
    ServiceResponse<PageDTO<ObjectionLedgerVO>> findLedgerByPage(@RequestBody JsonRequest<ObjectionLedgerVO> jsonRequest);
}
