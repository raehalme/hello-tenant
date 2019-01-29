package com.example.hellotenant.tenant;

public interface TenantContextHolder extends TenantContextProvider {
    void setTenantContext(TenantContext context);

    void clearTenantContext();
}
