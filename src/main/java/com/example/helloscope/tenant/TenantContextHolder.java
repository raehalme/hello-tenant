package com.example.helloscope.tenant;

public interface TenantContextHolder extends TenantContextProvider {
    void setTenantContext(TenantContext context);

    void clearTenantContext();
}
