package com.example.helloscope.config;

import com.example.helloscope.TenantBean;
import com.example.helloscope.tenant.TenantContextHolder;
import com.example.helloscope.tenant.context.annotation.TenantScope;
import com.example.helloscope.tenant.impl.ThreadLocalTenantContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloScopeConfig {
    @Bean
    public TenantContextHolder tenantContextHolder() {
        return new ThreadLocalTenantContextHolder();
    }

    @Bean @TenantScope
    public TenantBean tenantBean() {
        return new TenantBean();
    }
}
