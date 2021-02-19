package org.springframework.samples.petclinic.configuration;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.model.vet.Vet;

import javax.cache.CacheManager;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {
	public static final String DB_CACHE = "db_cache";
	public static final String SERVICE_CACHE = "service_cache";
	private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;
	private final javax.cache.configuration.Configuration<Integer, Vet> vetConfiguration;

	public CacheConfiguration() {
		jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
			CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
				ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10000, EntryUnit.ENTRIES))
				.withSizeOfMaxObjectSize(1, MemoryUnit.KB)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(60)))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(120))));
		vetConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
			CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Vet.class,
				ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10000, EntryUnit.ENTRIES))
				.withSizeOfMaxObjectSize(1, MemoryUnit.KB)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10))));
	}

	@Bean
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CacheManager cacheManager) {
		return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cacheManager -> {
			cacheManager.createCache(DB_CACHE, jcacheConfiguration);
			cacheManager.createCache(SERVICE_CACHE, vetConfiguration);
		};
	}
}
