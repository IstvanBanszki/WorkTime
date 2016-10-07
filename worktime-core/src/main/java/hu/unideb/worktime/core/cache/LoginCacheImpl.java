package hu.unideb.worktime.core.cache;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "loginCache")
public class LoginCacheImpl implements ILoginCache {
    
    @Autowired private SqlCallLogin sqlCallLogin;

    @Override
    public LoginRecord getByName(String loginName) {
        return this.sqlCallLogin.getLoginRecord(loginName);
    }

    @Override
    public LoginRecord updateByName(LoginRecord record, String loginName) {
        return record;
    }

    @Override
    public void deleteByName(String loginName) {
        //nothing to do here
    }

}
