package com.study.spring.app.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CacheService {

    private static final CacheData EMPTY_DATA = new CacheData();

    /**
     * 캐시를 가져오는 메소드
     * 
     * cacheNames: bean으로 설정한 ConcurrentMapCache의 저장소명
     * #key : SpEL. (객체 안의 멤버변수를 비교해야 하는 경우 사용):
     * 예시:  @Cacheable(cacheNames = "저장소명", key = "#key.val", condition "#key.val > 5")
     * key 값이 없다면 빈 값, key가 1개라면 그 값으로 key가 생성, 여러개라면 모든 파라매터를 포함한 단일 키가 생성됨
     * side effect가 있을 수 있으므로 key는 명시하는 것이 좋다.
     *
     * @param key
     *
     * @return
     */
    @Cacheable(cacheNames = "exampleStore", key = "#key")
    public CacheData getCacheData(final String key) {
        log.info("이 로그는 해당 key에 대한 캐시가 없는 경우 찍힙니다.");
        return EMPTY_DATA;
    }

    /**
     * 저장소에 저장된 key에 대한 value를 업데이트
     *
     * value: cacheNames와 같다. (value는 cacheNames의 aliasFor이다.)
     *
     * @param key
     *
     * @return
     */
    @CachePut(cacheNames = "exampleStore", key = "#key")
    public CacheData updateCacheData(final String key, final String value) {
        log.info("이 로그는 해당 key에 대한 캐시가 업데이트 되는 경우 찍힙니다.");
        CacheData cacheData = new CacheData();
        cacheData.setValue(value);
        cacheData.setExpirationDate(LocalDateTime.now().plusDays(1));
        return cacheData;
    }

    @CacheEvict(cacheNames = "exampleStore", key = "#key")
    public boolean expireCacheData(final String key) {
        log.info("이 로그는 해당 key에 대한 캐시를 지울 경우 찍힙니다.");
        return true;
    }
}
