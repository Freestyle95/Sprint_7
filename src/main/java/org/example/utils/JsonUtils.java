package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

}
