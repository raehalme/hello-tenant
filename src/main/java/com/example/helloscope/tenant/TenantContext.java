package com.example.helloscope.tenant;

import org.springframework.util.StringUtils;

public class TenantContext {
    private final String tenantId;

    private TenantContext(String tenantId) {
        if (!StringUtils.hasText(tenantId)) {
            throw new IllegalArgumentException("Must provide tenantId");
        }
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String toString() {
        return "TenantContext{" + tenantId + '}';
    }

    public static TenantContext of(String tenantId) {
        return new TenantContext(tenantId);
    }
}
