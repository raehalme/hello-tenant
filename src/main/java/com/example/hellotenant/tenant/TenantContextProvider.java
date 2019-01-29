package com.example.hellotenant.tenant;

/**
 * Provides access to {@link TenantContext}.
 */
public interface TenantContextProvider {
    /**
     * Returns a <code>boolean</code> indicating whether or not a {@link TenantContext} is available.
     * @return a <code>boolean</code> indicating whether or not a {@link TenantContext} is available.
     */
    boolean isTenantDefined();

    /**
     * Returns the {@link TenantContext} currently associated with the runtime. The method never returns
     * <code>null</code>.
     *
     * @return the {@link TenantContext} currently associated with the runtime.
     * @throws IllegalStateException is thrown if there is no {@link TenantContext} available.
     */
    TenantContext getTenantContext() throws IllegalStateException;
}
