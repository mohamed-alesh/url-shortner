package alesh.mohamed.urlshortener.cache;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class CacheConfig {
	@Bean
	public org.springframework.data.redis.cache.RedisCacheConfiguration cacheConfiguration() {
		return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(60)).disableCachingNullValues()
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}
	
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
	    return (builder) -> builder
	      .withCacheConfiguration("urlCache",
	        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
	}
}
