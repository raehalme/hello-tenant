package com.example.helloscope.tenant.http;

import javax.servlet.http.HttpServletRequest;

public interface TenantHttpResolver {
    String resolveTenantId(HttpServletRequest request);
}
