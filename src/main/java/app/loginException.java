package app;

public class loginException extends RuntimeException {
    public loginException(){
        super("Incorrect credentials have been inserted");
    }
}
