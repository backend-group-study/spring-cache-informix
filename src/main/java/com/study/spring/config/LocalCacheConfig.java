package com.study.spring.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableCaching // 캐싱에 반드시 필요한 어노테이션
@Configuration
public class LocalCacheConfig {

    /**
     * exampleStore라는 이름의 저장소를 만든다.
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(new ConcurrentMapCache("exampleStore"), new ConcurrentMapCache("memberData")));
        return simpleCacheManager;
    }
}
