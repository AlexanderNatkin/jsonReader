package com.example;

import com.google.gson.Gson;

import java.io.FileReader;

public class GSONParser {

    public Root parse() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("./tickets.json")) {
            return gson.fromJson(reader, Root.class);
        } catch (Exception e) {
            System.out.println("Exception error " + e);
        }
        return null;
    }
}
