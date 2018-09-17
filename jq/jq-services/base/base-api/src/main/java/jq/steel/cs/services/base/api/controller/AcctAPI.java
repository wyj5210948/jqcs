package jq.steel.cs.services.base.api.controller;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import jq.steel.cs.services.base.api.vo.AcctInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 后台账号
 * @Auther: wangyu
 */
@FeignClient(value = "${ser.name.base}")
public interface AcctAPI {

    /**
     * 账号注册
     * @param acctInfoVO
     * @return
     */
    @RequestMapping(value = "/acct/register",method = RequestMethod.POST)
    ServiceResponse<String> userRegister(AcctInfoVO acctInfoVO);

    /**
     * 账号登录
     * @param acctLogin
     * @return
     */
    @RequestMapping(value = "/acct/login",method = RequestMethod.POST)
    ServiceResponse<AcctSession> userLogin(AcctLogin acctLogin);


    /**
     * 从缓存中拿到session信息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/acct/getCacheUser",method = RequestMethod.GET)
    ServiceResponse<AcctSession> getUser(@RequestParam(value = "sessionId", required = false) String sessionId);

    /**
     * 账号注销
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/acct/delCacheUser",method = RequestMethod.POST)
    ServiceResponse<Boolean> delUser(@RequestParam(value = "sessionId", required = false) String sessionId);

    /**
     * 获得用户信息
     * @return
     */
    @RequestMapping(value = "acct/getAcct",method = RequestMethod.POST)
    public ServiceResponse<AcctInfoVO> getAcct(@RequestParam(value = "acctName", required = false) String acctName);

    @RequestMapping(value = "acct/getAcctAuth",method = RequestMethod.POST)
    public ServiceResponse<Map<String, String>> getAcctAuth(@RequestParam(value = "acctId") String acctId);

    @RequestMapping(value = "acct/getAcctAuthPath",method = RequestMethod.POST)
    public ServiceResponse<Map<String, String>> getAcctAuthPath(@RequestParam(value = "acctId") String acctId);
}
