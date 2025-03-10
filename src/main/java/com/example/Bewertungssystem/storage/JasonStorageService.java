package com.example.Bewertungssystem.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonStorageService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> ladeJsonDatei(String dateipfad, Class<T> typ) {
        try {
            File file = new File(dateipfad);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return Arrays.asList(objectMapper.readValue(file, typ.arrayType()));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void speichereJsonDatei(String dateipfad, Object daten) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dateipfad), daten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
