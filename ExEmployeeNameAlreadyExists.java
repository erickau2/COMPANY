

public class ExEmployeeNameAlreadyExists extends Exception{
    

    public ExEmployeeNameAlreadyExists() {
        super("Employee name already exists.");
    }
    public ExEmployeeNameAlreadyExists(String msg) {
        super(msg);
    }
}
