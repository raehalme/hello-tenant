package com.example.hellotenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This single instance serves all tenants.
 */
@RestController
public class SharedController {
    /**
     * This is a proxy which connects the tenant specific instance.
     */
    @Autowired
    private TenantBean tenantBean;

    @RequestMapping("/")
    public String sayHello() {
        return tenantBean.toString();
    }
}
