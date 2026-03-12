import Frames.Login;

public class Start1 {
    public static void main(String[] args) {
        createAndShowInitialFrame();
    }

    private static void createAndShowInitialFrame() {
        Login lg = new Login();
        lg.setVisible(true);
    }
}