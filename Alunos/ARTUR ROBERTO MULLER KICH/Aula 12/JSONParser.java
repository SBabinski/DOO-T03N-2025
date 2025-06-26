package org.example.service;

import java.util.HashMap;
import java.util.Map;


public class JSONParser {


    public static String getString(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return "";

        int valueStart = json.indexOf('"', keyIndex + searchKey.length());
        if (valueStart == -1) return "";

        int valueEnd = json.indexOf('"', valueStart + 1);
        if (valueEnd == -1) return "";

        return json.substring(valueStart + 1, valueEnd);
    }


    public static double getDouble(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return 0.0;

        int valueStart = keyIndex + searchKey.length();
        int valueEnd = json.indexOf(',', valueStart);
        if (valueEnd == -1) {
            valueEnd = json.indexOf('}', valueStart);
        }
        if (valueEnd == -1) return 0.0;

        String valueStr = json.substring(valueStart, valueEnd).trim();
        try {
            return Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }


    public static String getObject(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return "";

        int objectStart = json.indexOf('{', keyIndex);
        if (objectStart == -1) return "";

        int objectEnd = findMatchingBrace(json, objectStart);
        if (objectEnd == -1) return "";

        return json.substring(objectStart, objectEnd + 1);
    }


    public static String getFirstArrayObject(String json, String arrayKey) {
        String searchKey = "\"" + arrayKey + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return "";

        int arrayStart = json.indexOf('[', keyIndex);
        if (arrayStart == -1) return "";

        int objectStart = json.indexOf('{', arrayStart);
        if (objectStart == -1) return "";

        int objectEnd = findMatchingBrace(json, objectStart);
        if (objectEnd == -1) return "";

        return json.substring(objectStart, objectEnd + 1);
    }


    private static int findMatchingBrace(String json, int openBraceIndex) {
        int count = 1;
        for (int i = openBraceIndex + 1; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                count++;
            } else if (c == '}') {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
}
