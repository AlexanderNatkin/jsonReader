package com.example;

/*
Класс с методами для считывания файла JSON с папки resources
 */

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GSONParser {

    public Root parse() throws IOException {
        Gson gson = new Gson();

        ClassLoader classLoader = gson.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("tickets.json");
        assert inputStream != null;
        String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        return gson.fromJson(jsonString, Root.class);
    }
}
