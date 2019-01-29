package com.example.hellotenant;

import com.example.hellotenant.tenant.TenantContextHolder;
import com.example.hellotenant.tenant.context.annotation.TenantScope;
import com.example.hellotenant.tenant.impl.ThreadLocalTenantContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloTenantConfig {
    @Bean
    public TenantContextHolder tenantContextHolder() {
        return new ThreadLocalTenantContextHolder();
    }

    @Bean @TenantScope
    public TenantBean tenantBean() {
        return new TenantBean();
    }
}
