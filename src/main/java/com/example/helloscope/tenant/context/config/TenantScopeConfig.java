package com.example.helloscope.tenant.context.config;

import com.example.helloscope.tenant.TenantContextHolder;
import com.example.helloscope.tenant.scope.TenantScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantScopeConfig {
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
}
