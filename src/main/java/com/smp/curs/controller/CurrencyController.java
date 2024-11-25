package com.smp.curs.controller;

import com.smp.curs.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
public class CurrencyController {

    private final CurrencyService currencyService;

    private static final String ERROR_VIEW = "error";

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        String yenRate = currencyService.getYenExchangeRate();
        model.addAttribute("yenRate", yenRate);
        return "index";
    }

    @GetMapping("/**")
    public String handleUnknownPaths(Model model) {
        log.error("Запрашиваемая страница не найдена");
        model.addAttribute("errorMessage", "Запрашиваемая страница не найдена. Проверьте правильность URL.");
        model.addAttribute("technicalDetails", "");
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("timestamp", now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        return ERROR_VIEW;
    }
}