package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    @JsonProperty("show")
    private Maratona show;

    public Maratona getShow() {
        return show;
    }

    public void setShow(Maratona show) {
        this.show = show;
    }
}