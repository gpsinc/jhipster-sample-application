package gps.mx.poc.start.jhipster.sample.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(gps.mx.poc.start.jhipster.sample.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Persona.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Direccion.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.DocumentoProveedor.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Proveedor.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Proveedor.class.getName() + ".documentos", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Proveedor.class.getName() + ".adscripciones", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Adscripcion.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Adscripcion.class.getName() + ".empleados", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Adscripcion.class.getName() + ".proveedors", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Empleado.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.Empleado.class.getName() + ".adscripciones", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable.class.getName(), jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable.class.getName() + ".responsables", jcacheConfiguration);
            cm.createCache(gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable.class.getName() + ".adscripcions", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
