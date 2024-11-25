package com.smp.curs.controller;

import com.smp.curs.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/config")
    public String getConfig(Model model) {
        String configContent = configService.loadConfig();
        model.addAttribute("configContent", configContent);
        return "configView";
    }
}