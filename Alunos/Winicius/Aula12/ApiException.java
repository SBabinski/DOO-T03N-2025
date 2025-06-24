package weather;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }
}
