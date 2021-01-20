

public class ExProjectCodeAlreadyExists extends Exception{

    public ExProjectCodeAlreadyExists(){
        super("Project code already exists.");
    }
    public ExProjectCodeAlreadyExists(String msg){
        super(msg);
    }
}
