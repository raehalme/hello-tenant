package com.example.hellotenant.tenant;

public interface TenantContextProvider {
    boolean isTenantDefined();

    TenantContext getTenantContext();
}
