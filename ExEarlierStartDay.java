

public class ExEarlierStartDay extends Exception{
    public ExEarlierStartDay() {
        super("The earliest start day is tomorrow.");
    }
    public ExEarlierStartDay(String msg) {
        super(msg);
    }

}
