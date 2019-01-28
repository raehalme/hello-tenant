package com.example.helloscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {
    @Autowired
    private TenantBean tenantBean;

    @RequestMapping("/")
    public String sayHello() {
        return tenantBean.toString();
    }
}
