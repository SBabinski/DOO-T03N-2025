/*Extrai objetos e campos específicos (ex: "name":"...)

Trata listas simples (gêneros)

Extrai nome da emissora

Retorna valores opcionais para campos numéricos */

package com.example.util;

import java.util.Optional;

public class JsonUtils {

    public static String extractShowObject(String fullJson) {
        int idx = fullJson.indexOf("\"show\":{");
        if (idx < 0) return "";
        String js = fullJson.substring(idx + 7);
        int braces = 1, i = 0;
        for (; i < js.length(); i++) {
            char c = js.charAt(i);
            if (c == '{') braces++;
            else if (c == '}') braces--;
            if (braces == 0) break;
        }
        return js.substring(0, i + 1);
    }

    public static String extrairCampo(String json, String chave) {
        int start = json.indexOf(chave);
        if (start < 0) return "";
        start += chave.length();
        int end = json.indexOf("\"", start);
        if (end < 0) return "";
        return json.substring(start, end);
    }

    public static Optional<String> extrairCampoNumerico(String json, String chave) {
        int start = json.indexOf(chave);
        if (start < 0) return Optional.empty();
        start += chave.length();
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '.')) {
            end++;
        }
        return Optional.of(json.substring(start, end));
    }

    public static String extrairLista(String json, String inicio, String fim) {
        int s = json.indexOf(inicio);
        if (s < 0) return "";
        s += inicio.length();
        int e = json.indexOf(fim, s);
        if (e < 0) return "";
        return json.substring(s, e);
    }

    public static String extractNetworkName(String showJson) {
        if (!showJson.contains("\"network\":")) return "Desconhecida";
        String net = showJson.substring(showJson.indexOf("\"network\":"));
        String name = extrairCampo(net, "\"name\":\"");
        return name.isEmpty() ? "Desconhecida" : name;
    }
}
