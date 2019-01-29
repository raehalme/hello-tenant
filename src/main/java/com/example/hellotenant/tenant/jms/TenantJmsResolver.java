package com.example.hellotenant.tenant.jms;

import javax.jms.Message;

public interface TenantJmsResolver {
    String resolveTenantId(Message message);
}
