package com.example.helloscope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TenantJmsListener {
    private static final Logger log = LoggerFactory.getLogger(TenantJmsListener.class);

    @Autowired
    private TenantBean tenantBean;

    @JmsListener(destination = "shared-queue")
    public void onMessage(String payload) {
        log.info("Processing message with {}: {}", tenantBean, payload);
    }
}
