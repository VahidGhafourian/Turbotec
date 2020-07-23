import BackWork.OnlineData;
import panels.MainPanel;

public class Main {
    public static void main(String[] args) {
        disableWarning();
        new MainPanel();
    }
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

}
