package app;

public class addOutfitException extends RuntimeException {
    public addOutfitException(){
        super("The outfit was not created correctly");
    }
}
