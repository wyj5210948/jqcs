package jq.steel.cs.services.base.facade.service.user;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.AcctLogin;
import com.ebase.core.session.AcctSession;
import jq.steel.cs.services.base.api.vo.AcctInfoVO;

import java.util.Map;

/**
 * 后台用户
 * @Auther: wangyu
 */
public interface AcctService {

    /**
     * 用户注册
     * @param acctInfoVO
     * @return
     */
    ServiceResponse<String> userRegister(AcctInfoVO acctInfoVO);

    //用户登录
    ServiceResponse<AcctSession> userLogin(AcctLogin acctLogin);

    /**
     * 根据账号名 获得 账号信息
     * @param acctName
     * @return
     */
    AcctInfoVO getAcct(String acctName);

    /**
     * @param:
     * @return:  
     * @description:  获取权限id
     * @author: lirunze
     * @Date: 2018/9/16
     */
    Map<String, String> getAcctAuth(String acctId);

    /**
     * @param:
     * @return:
     * @description:  获取权限路径
     * @author: lirunze
     * @Date: 2018/9/16
     */
    Map<String, String> getAcctAuthPath(String acctId);

    void expire(String authKey);
}
