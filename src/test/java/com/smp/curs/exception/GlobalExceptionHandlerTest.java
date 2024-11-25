package com.smp.curs.exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleConfigFileNotFoundException() {
        // Arrange
        ConfigFileNotFoundException exception = new ConfigFileNotFoundException("Configuration file not found");

        // Act
        ResponseEntity<String> response = exceptionHandler.handleConfigFileNotFoundException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Configuration file not found", response.getBody());
    }

    @Test
    public void testHandleCurrencyNotFound() {
        // Arrange
        CurrencyNotFoundException exception = new CurrencyNotFoundException("Currency not found");
        Model model = mock(Model.class);

        // Act
        String viewName = exceptionHandler.handleCurrencyNotFound(exception, model);

        // Assert
        assertEquals("error", viewName);
        Mockito.verify(model).addAttribute("errorMessage", "Курс не найден");
        Mockito.verify(model).addAttribute("technicalDetails", "Currency not found");
    }

    @Test
    public void testHandleCurrencyServiceException() {
        // Arrange
        CurrencyServiceException exception = new CurrencyServiceException("Service error", null);
        Model model = mock(Model.class);

        // Act
        String viewName = exceptionHandler.handleCurrencyServiceException(exception, model);

        // Assert
        assertEquals("error", viewName);
        Mockito.verify(model).addAttribute("errorMessage", "Ошибка получения данных о курсе валют");
        Mockito.verify(model).addAttribute("technicalDetails", "Пожалуйста, попробуйте позже");
    }

    @Test
    public void testHandleNoHandlerFound() {
        // Arrange
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/unknown", null);
        Model model = mock(Model.class);

        // Act
        String viewName = exceptionHandler.handleNoHandlerFound(exception, model);

        // Assert
        assertEquals("error", viewName);
        Mockito.verify(model).addAttribute("errorMessage", "Запрашиваемая страница не найдена. Проверьте правильность URL.");
        Mockito.verify(model).addAttribute("technicalDetails", exception.getMessage());
    }

    @Test
    public void testHandleAllUncaughtExceptions() {
        // Arrange
        Exception exception = new Exception("Unexpected error");
        Model model = mock(Model.class);

        // Act
        String viewName = exceptionHandler.handleAllUncaughtExceptions(exception, model);

        // Assert
        assertEquals("error", viewName);
        Mockito.verify(model).addAttribute("errorMessage", "Произошла системная ошибка");
        Mockito.verify(model).addAttribute("technicalDetails", "Пожалуйста, попробуйте позже или обратитесь к администратору");
    }
}