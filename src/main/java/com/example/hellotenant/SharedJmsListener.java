package com.example.hellotenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * This single instance serves all tenants.
 */
@Component
public class SharedJmsListener {
    private static final Logger log = LoggerFactory.getLogger(SharedJmsListener.class);

    /**
     * This is a proxy which connects the tenant specific instance.
     */
    @Autowired
    private TenantBean tenantBean;

    @JmsListener(destination = "shared-queue")
    public void onMessage(String payload) {
        log.info("Processing message with {}: {}", tenantBean, payload);
    }
}
