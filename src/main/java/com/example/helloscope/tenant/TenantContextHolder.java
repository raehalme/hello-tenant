package com.example.helloscope.tenant;

public interface TenantContextHolder {
    boolean isTenantDefined();

    TenantContext getTenantContext();

    void setTenantContext(TenantContext context);

    void clearTenantContext();
}
