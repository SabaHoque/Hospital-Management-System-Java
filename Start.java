import Frames.Login;

public class Start {
    public static void main(String[] args) {
        createAndShowInitialFrame();
    }

    private static void createAndShowInitialFrame() {
        Login lg = new Login();
        lg.setVisible(true);
    }
}