package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseMenu {

    protected static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    protected static int readInt() throws IOException {
        return Integer.parseInt(input.readLine());
    }
}
