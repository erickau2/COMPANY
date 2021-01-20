

public class ExInsufficientCArguments extends Exception{

   

    public ExInsufficientCArguments() {
        super("Insufficient command arguments.");
    }
    public ExInsufficientCArguments(String msg) {
        super(msg);
    }
}
