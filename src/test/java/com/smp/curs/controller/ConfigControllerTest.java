package com.smp.curs.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.smp.curs.service.ConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

public class ConfigControllerTest {

    @InjectMocks
    private ConfigController configController;

    @Mock
    private ConfigService configService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetConfig() {
        // Данные для теста
        String mockConfigContent = "mock config content";

        // Настройка мока
        when(configService.loadConfig()).thenReturn(mockConfigContent);

        // Вызов метода контроллера
        String viewName = configController.getConfig(model);

        // Проверка взаимодействия с моделью
        verify(model).addAttribute("configContent", mockConfigContent);

        // Проверка имени возвращаемого представления
        assertEquals("configView", viewName);
    }
}