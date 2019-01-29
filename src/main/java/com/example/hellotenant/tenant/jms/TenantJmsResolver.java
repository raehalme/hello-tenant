package com.example.hellotenant.tenant.jms;

import javax.jms.Message;

/**
 * Resolves tenant id from the given {@link Message}.
 */
public interface TenantJmsResolver {
    String resolveTenantId(Message message);
}
