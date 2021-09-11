package com.example;

import com.google.gson.Gson;

import java.io.FileReader;

public class GSONParser {

    public Root parse() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("tickets.json")) {
            Root root = gson.fromJson(reader, Root.class);
            return root;
        } catch (Exception e) {
            System.out.println("Exception error " + e);
        }
        return null;
    }
}
