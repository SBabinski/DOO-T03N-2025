package org.example.Personalize;

import java.util.InputMismatchException;

public class Exececoes extends RuntimeException {
    public Exececoes(String message) {
        super(message);

    }
    public Exececoes(InputMismatchException e) {
        super(e);

    }

}
