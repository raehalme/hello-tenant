package com.example.hellotenant;

import org.springframework.beans.factory.annotation.Value;

//@Component @TenantScope
public class TenantBean {
    @Value("${tenant.id}")
    private String tenantId;

    public String toString() {
        return super.toString() + " for " + tenantId;
    }
}
