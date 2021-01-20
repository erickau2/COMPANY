public class ExEmployeeNameNotExist extends Exception{

   

    public ExEmployeeNameNotExist(){
        super("Employee name does not exist.");
    }
    public ExEmployeeNameNotExist(String msg){
        super(msg);
    }
}
