package com.smp.curs.service;

import com.smp.curs.exception.ConfigFileNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ConfigService {

    public String loadConfig() {
        File file = new File("./config.txt");
        if (!file.exists()) {
            throw new ConfigFileNotFoundException("Configuration file not found: " + file.getAbsolutePath());
        }

        try (Scanner scanner = new Scanner(file)) {
            // чтение содержимого файла
            return scanner.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new ConfigFileNotFoundException("Configuration file not found: " + e.getMessage());
        }
    }
}