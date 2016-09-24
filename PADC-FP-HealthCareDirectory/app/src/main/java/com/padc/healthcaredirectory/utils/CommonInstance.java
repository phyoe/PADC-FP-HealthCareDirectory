package com.padc.healthcaredirectory.utils;

import com.google.gson.Gson;

/**
 * Created by aung on 12/12/15.
 */
public class CommonInstance {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
