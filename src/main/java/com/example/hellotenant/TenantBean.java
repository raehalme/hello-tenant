package com.example.hellotenant;

import org.springframework.beans.factory.annotation.Value;

/**
 * This bean has @TenantScope defined either at class level or in the configuration.
 * Each tenant will have its own instance with its own properties.
 */
//@Component @TenantScope
public class TenantBean {
    @Value("${tenant.id}")
    private String tenantId;

    public String toString() {
        return super.toString() + " for " + tenantId;
    }
}
