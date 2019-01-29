package com.example.hellotenant;

import com.example.hellotenant.tenant.context.annotation.TenantScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloTenantConfig {
    @Bean @TenantScope
    public TenantBean tenantBean() {
        return new TenantBean();
    }
}
