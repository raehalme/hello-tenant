package com.example.helloscope.tenant.context.config;

import com.example.helloscope.tenant.TenantContextHolder;
import com.example.helloscope.tenant.scope.TenantScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.PostConstruct;

@Configuration
public class TenantScopeConfig {
    private static final Logger log = LoggerFactory.getLogger(TenantScopeConfig.class);

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer(TenantScope tenantScope) {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope(TenantScope.SCOPE_NAME, tenantScope);
        return customScopeConfigurer;
    }

    @Bean
    public TenantScope tenantScope(TenantContextHolder tenantContextHolder) {
        return new TenantScope(tenantContextHolder);
    }

    @Bean
    public TenantPropertySource tenantPropertySource(TenantContextHolder tenantContextHolder) {
        return new TenantPropertySource(tenantContextHolder);
    }

    @Configuration
    public static class TenantEnvironmentConfig {
        @Autowired
        private ConfigurableEnvironment env;
        @Autowired
        private TenantPropertySource tenantPropertySource;

        @PostConstruct
        public void init() {
            env.getPropertySources().addFirst(tenantPropertySource);
        }
    }
}
