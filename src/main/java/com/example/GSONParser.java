package com.example;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GSONParser {

    public Root parse() throws FileNotFoundException {
        Gson gson = new Gson();

        FileReader reader = new FileReader("tickets.json");
        return gson.fromJson(reader, Root.class);
    }
}
