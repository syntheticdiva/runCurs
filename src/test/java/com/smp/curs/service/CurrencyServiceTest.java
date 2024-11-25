package com.smp.curs.service;

import com.smp.curs.exception.CurrencyNotFoundException;
import com.smp.curs.exception.CurrencyServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    private CurrencyService currencyService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService(restTemplate);
        ReflectionTestUtils.setField(currencyService, "currencyUrl", "http://test-currency-url");
    }

    @Test
    void testGetYenExchangeRate_NullResponse() {
        // Arrange
        when(restTemplate.getForObject("http://test-currency-url", String.class))
                .thenReturn(null);

        // Act & Assert
        assertThrows(CurrencyNotFoundException.class, () -> {
            currencyService.getYenExchangeRate();
        });
    }
    @Test
    void testGetYenExchangeRate_InvalidXml() {
        // Arrange
        String invalidXmlResponse = "Invalid XML";

        when(restTemplate.getForObject("http://test-currency-url", String.class))
                .thenReturn(invalidXmlResponse);

        // Act & Assert
        assertThrows(CurrencyServiceException.class, () -> {
            currencyService.getYenExchangeRate();
        });
    }
}