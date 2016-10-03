package com.machinelearning.game.machine.util;

import com.google.gson.*;

/**
 * Created by zuhai.jiang on 2016/5/6.
 */
public class JsonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static JsonElement parseJson(String json){
        JsonParser parser = new JsonParser();
        JsonElement ele = parser.parse(json);
        return ele;
    }

    public static JsonObject parseJsonObject(String json){
        return (JsonObject) parseJson(json);
    }

    public static JsonObject parseJsonArray(String json){
        return (JsonObject) parseJson(json);
    }


    public static Integer getInt(JsonObject json, String member){
        JsonElement ele = json.get(member);
        if (ele != null) {
            return ele.getAsInt();
        }
        return null;
    }

    public static String getString(JsonObject json, String member){
        JsonElement ele = json.get(member);
        if (ele != null) {
            return ele.getAsString();
        }
        return null;
    }

    public static Boolean getBoolean(JsonObject json, String member){
        JsonElement ele = json.get(member);
        if (ele != null) {
            return ele.getAsBoolean();
        }
        return null;
    }

    public static Double getDouble(JsonObject json, String member){
        JsonElement ele = json.get(member);
        if (ele != null) {
            return ele.getAsDouble();
        }
        return null;
    }
}
