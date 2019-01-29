package com.example.helloscope.tenant;

public interface TenantContextProvider {
    boolean isTenantDefined();

    TenantContext getTenantContext();
}
