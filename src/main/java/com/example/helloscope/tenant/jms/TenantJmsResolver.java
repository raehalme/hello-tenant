package com.example.helloscope.tenant.jms;

import javax.jms.Message;

public interface TenantJmsResolver {
    String resolveTenantId(Message message);
}
