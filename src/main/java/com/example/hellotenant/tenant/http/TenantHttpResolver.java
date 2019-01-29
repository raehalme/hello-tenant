package com.example.hellotenant.tenant.http;

import javax.servlet.http.HttpServletRequest;

/**
 * Resolves tenant id from the given {@link HttpServletRequest}.
 */
public interface TenantHttpResolver {
    String resolveTenantId(HttpServletRequest request);
}
