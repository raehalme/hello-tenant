package com.example.hellotenant.tenant.context.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Beans annotated with {@link TenantScope} are instantiated for each tenant separately. The actual bean reference
 * will be a proxy that forwards calls to the tenant specific instance.
 */
@Scope(scopeName = com.example.hellotenant.tenant.context.scope.TenantScope.SCOPE_NAME, proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface TenantScope {
}
