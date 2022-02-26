package com.study.spring;

import com.study.spring.app.domain.CacheData;
import com.study.spring.app.domain.CacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@SpringBootTest
class SpringBoliderplateApplicationTests {

    private final CacheService cacheService;

    @Autowired
    SpringBoliderplateApplicationTests(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Test
    @DisplayName("로컬 캐시 초기화 테스트 - 성공해야 합니다")
    void cacheinitTest() {
        CacheData key1 = cacheService.getCacheData("key1");
        CacheData result = cacheService.updateCacheData("key1", "value1");
        Assertions.assertTrue(isValidation(result));
    }


    @Test
    @DisplayName("로컬 캐시 초기화 테스트 - 성공해야 합니다")
    void cacheMultipleKeyTest() {
        CacheData key1 = cacheService.getCacheData("key1");
        CacheData result = cacheService.updateCacheData("key1", "value1");
        Assertions.assertTrue(isValidation(result));
    }

    private boolean isValidation(final CacheData cacheData) {
        return !ObjectUtils.isEmpty(cacheData)
                && !ObjectUtils.isEmpty(cacheData.getExpirationDate())
                && !ObjectUtils.isEmpty(cacheData.getValue())
                && cacheData.getExpirationDate().isAfter(LocalDateTime.now());
    }

}
