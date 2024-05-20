package app;

public class addItemException extends RuntimeException {
    public addItemException(){
        super("The item was not created correctly");
    }
}
