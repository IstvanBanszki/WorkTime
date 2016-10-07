package hu.unideb.worktime.core.cache;

import hu.unideb.worktime.api.model.login.LoginRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface ILoginCache {
    
    @Cacheable(key="#loginName")
    LoginRecord getByName(String loginName);

    @CachePut(key="#loginName")
    LoginRecord updateByName(LoginRecord record, String loginName);

    @CacheEvict(key="#loginName")
    void deleteByName(String loginName);
}
