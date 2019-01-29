package com.example.hellotenant.tenant.holder;

import com.example.hellotenant.tenant.TenantContext;
import com.example.hellotenant.tenant.TenantContextProvider;

/**
 * Extends {@link TenantContextProvider} by adding methods to define the current {@link TenantContext}. Clients should
 * not use this interface as it represents the service provider view responsible of resolving and activating the
 * {@link TenantContext}. In the application runtime configuration, however, the instance implementing
 * {@link TenantContextProvider} and this interface should be one and the same.
 */
public interface TenantContextHolder extends TenantContextProvider {
    /**
     * Activates the given {@link TenantContext}. Subsequent calls to {@link #getTenantContext()} should return the
     * given instance.
     *
     * @param context the currently active {@link TenantContext}.
     */
    void setTenantContext(TenantContext context);

    /**
     * Clears the {@link TenantContext}. Subsequent calls to {@link #getTenantContext()} should throw
     * {@link IllegalStateException}.
     */
    void clearTenantContext();
}
