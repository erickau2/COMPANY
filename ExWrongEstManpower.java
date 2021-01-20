
public class ExWrongEstManpower extends Exception {

    private int Wmanpower;
    public void printWmanpowerMsg() {
        System.out.println("Estimated manpower should not be zero or negative.");
        System.out.printf("Consider changing %s to a positive non-zero amount.", Wmanpower);
    } 
    public ExWrongEstManpower(int W) {
        this.Wmanpower = W;
    }
}
