package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Network {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
