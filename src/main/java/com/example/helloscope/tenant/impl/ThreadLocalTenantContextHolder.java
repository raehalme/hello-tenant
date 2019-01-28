package com.example.helloscope.tenant.impl;

import com.example.helloscope.tenant.TenantContext;
import com.example.helloscope.tenant.TenantContextHolder;

import java.util.Objects;

public class ThreadLocalTenantContextHolder implements TenantContextHolder {
    private ThreadLocal<TenantContext> holder = new ThreadLocal();

    @Override
    public boolean isTenantDefined() {
        return holder.get() != null;
    }

    @Override
    public TenantContext getTenantContext() {
        TenantContext context = holder.get();
        if (context == null) {
            throw new IllegalStateException("Tenant context has not been set");
        }
        return context;
    }

    @Override
    public void setTenantContext(TenantContext context) {
        holder.set(Objects.requireNonNull(context));
    }

    @Override
    public void clearTenantContext() {
        holder.set(null);
    }
}
