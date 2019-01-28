package com.example.helloscope.tenant;

public interface TenantContextHolder {
    TenantContext getTenantContext();

    void setTenantContext(TenantContext context);

    void clearTenantContext();
}
