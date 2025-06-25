package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpClient;

public final class GlobalVarSingleton {

    private static GlobalVarSingleton instance;
    public final HttpClient tvApiHTTPclient = HttpClient.newHttpClient();
    public final ObjectMapper globalJsonWrapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(SerializationFeature.INDENT_OUTPUT);
    public String userName;

    private GlobalVarSingleton() {

    }

    public static GlobalVarSingleton getInstance() {
        if (instance == null) {
            instance = new GlobalVarSingleton();
        }
        return instance;
    }
}
