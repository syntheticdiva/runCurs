package com.smp.curs.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigFileNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        // Данные для теста
        String expectedMessage = "Configuration file not found";

        // Создание экземпляра исключения
        ConfigFileNotFoundException exception = new ConfigFileNotFoundException(expectedMessage);

        // Проверка, что сообщение исключения соответствует ожидаемому
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionInheritance() {
        // Создание экземпляра исключения
        ConfigFileNotFoundException exception = new ConfigFileNotFoundException("Some message");

        // Проверка, что исключение является экземпляром RuntimeException
        assertEquals(RuntimeException.class, exception.getClass().getSuperclass());
    }
}