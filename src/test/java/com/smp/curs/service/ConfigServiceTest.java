package com.smp.curs.service;

import com.smp.curs.exception.ConfigFileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigServiceTest {

    private final String configFilePath = "./config.txt";
    private ConfigService configService;

    @BeforeEach
    public void setUp() {
        configService = new ConfigService();
    }

    @AfterEach
    public void tearDown() {
        File file = new File(configFilePath);
        if (file.exists()) {
            file.delete(); // Удаляем файл после теста
        }
    }

    @Test
    public void testLoadConfig_FileNotFound() {
        // Проверка, что выбрасывается исключение
        ConfigFileNotFoundException exception = assertThrows(ConfigFileNotFoundException.class, () -> {
            configService.loadConfig();
        });

        assertEquals("Configuration file not found: " + new File(configFilePath).getAbsolutePath(), exception.getMessage());
    }

    @Test
    public void testLoadConfig_Success() throws Exception {
        // Создание файла конфигурации
        String expectedContent = "This is a test configuration.";
        try (PrintWriter writer = new PrintWriter(configFilePath)) {
            writer.println(expectedContent);
        }

        // Вызов метода loadConfig
        String content = configService.loadConfig();

        // Проверка, что содержимое загружено правильно
        assertEquals(expectedContent, content.trim()); //
    }
}