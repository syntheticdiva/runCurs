package com.smp.curs.controller;

import com.smp.curs.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyControllerTest {

    @InjectMocks
    private CurrencyController currencyController;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndex() {
        // Данные для теста
        String mockYenRate = "110.25";

        // Настройка мока
        when(currencyService.getYenExchangeRate()).thenReturn(mockYenRate);

        // Вызов метода контроллера
        String viewName = currencyController.index(model);

        // Проверка взаимодействия с моделью
        verify(model).addAttribute("yenRate", mockYenRate);

        // Проверка имени возвращаемого представления
        assertEquals("index", viewName);
    }

    @Test
    public void testHandleUnknownPaths() {
        // Вызов метода контроллера
        String viewName = currencyController.handleUnknownPaths(model);

        // Проверка взаимодействия с моделью
        verify(model).addAttribute("errorMessage", "Запрашиваемая страница не найдена. Проверьте правильность URL.");
        verify(model).addAttribute("technicalDetails", "");

        // Проверка имени возвращаемого представления
        assertEquals("error", viewName);
    }
}