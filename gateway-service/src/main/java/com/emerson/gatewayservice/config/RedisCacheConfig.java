package com.emerson.gatewayservice.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	
	@Value("${app.cache.default-ttl:60s}")
	private Duration defaultTtl;
	
	@Value("${app.cache.auth-service.user-roles.duration:3m}")
	private Duration userRolesCacheDuration;
	
	@Bean
	CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		// configuração padrão
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
																			.entryTtl(defaultTtl) // TTL padrão
																			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
																					new GenericJackson2JsonRedisSerializer()
																			));

		// TTLs personalizados por cache
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		cacheConfigurations.put("userRoles", defaultCacheConfig.entryTtl(userRolesCacheDuration));

		return RedisCacheManager.builder(connectionFactory)
								.cacheDefaults(defaultCacheConfig)
								.withInitialCacheConfigurations(cacheConfigurations)
								.build();
	}
	
}