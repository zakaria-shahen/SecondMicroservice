package com.mycompany.secondmicroservice;

import com.mycompany.secondmicroservice.context.CustomUserContext;
import com.mycompany.secondmicroservice.context.CustomUserContextFactory;
import feign.Feign;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class Config {

    private static final ClientHttpRequestInterceptor addHeaderInterceptor = (request, body, execution) -> {
        CustomUserContext context = CustomUserContextFactory.getUserContext().get();
        String token = context.getAuthToken();
        String correlationId = context.getCorrelationId();
        String userId = context.getUserId();

        request.getHeaders().add(CustomUserContext.CORRELATION_ID, correlationId);
        request.getHeaders().add(CustomUserContext.AUTH_TOKEN, token);
        request.getHeaders().add(CustomUserContext.USER_ID, userId);
        return execution.execute(request, body);
    };

    // RestTemplate Approach
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors(List.of(addHeaderInterceptor));
        return restTemplate;

    }


    // Feign Approach
    @Bean
    public Feign feignBuilder() {
        return Feign.builder().requestInterceptor(requestTemplate -> {
            CustomUserContext context = CustomUserContextFactory.getUserContext().get();
            String token = context.getAuthToken();
            String correlationId = context.getCorrelationId();
            String userId = context.getUserId();

            requestTemplate.header(CustomUserContext.CORRELATION_ID, correlationId);
            requestTemplate.header(CustomUserContext.AUTH_TOKEN, token);
            requestTemplate.header(CustomUserContext.USER_ID, userId);
        }).build();
    }


    // Spring Cache - serialize Json
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }


}
