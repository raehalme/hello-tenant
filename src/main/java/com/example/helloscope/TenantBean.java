package com.example.helloscope;

import com.example.helloscope.tenant.context.annotation.TenantScope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component @TenantScope
public class TenantBean {
    @Value("${tenant.id}")
    private String tenantId;

    public String toString() {
        return super.toString() + " for " + tenantId;
    }
}
