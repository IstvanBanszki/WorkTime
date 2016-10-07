package hu.unideb.worktime.core.cache;

import hu.unideb.worktime.api.model.login.LoginRecord;
import hu.unideb.worktime.jdbc.login.SqlCallLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "logins")
public class LoginCache {
    
    @Autowired private SqlCallLogin sqlCallLogin;

    @Cacheable(key="#loginName")
    public LoginRecord findByName(String loginName) {
        return this.sqlCallLogin.getLoginRecord(loginName);
    }

    @CachePut(key="#loginName")
    public LoginRecord putByName(LoginRecord record, String loginName) {
        return record;
    }

    @CacheEvict(key="#loginName")
    public void deleteByName(String loginName) {
        //nothing to do here
    }

}
