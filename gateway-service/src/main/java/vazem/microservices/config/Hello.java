package vazem.microservices.config;

public class Hello {

    String message;

    Hello() {
    }

    Hello(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
