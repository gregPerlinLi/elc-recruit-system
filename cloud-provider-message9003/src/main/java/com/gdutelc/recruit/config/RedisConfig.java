package com.gdutelc.recruit.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * {@link RedisTemplate} 自定义配置
 *
 * @author gregPerlinLi
 * @date 2022-08-05
 */
public class RedisConfig {
    /**
     * 自定义 {@link RedisTemplate} 的序列化方式，使其支持 JSON
     * @return 支持 JSON 序列化的 {@link RedisTemplate}
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        // Key serialization mode
        template.setKeySerializer(redisSerializer);
        // Value serialization mode
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // value hashmap serialization mode
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * 自定义缓存，使其使用 Redis 实现，并使其使用 JSON 进行序列化
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // Solve the problem of abnormal query cache conversion
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // Configure serialization (solve the garbled code problem), and the expiration time is 600 seconds
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                                                .entryTtl(Duration.ofSeconds(600))
                                                                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                                                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                                                                .disableCachingNullValues();
        return RedisCacheManager.builder(factory)
                                .cacheDefaults(config)
                                .build();
    }
}
