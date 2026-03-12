import Frames.Login;
// Removed unnecessary star imports and java.lang.* (default imported)
// Assuming other imports like Classes.* and Interfaces.* are not used here; if needed, specify explicitly

public class Start {
    public static void main(String[] args) {
        createAndShowInitialFrame();
    }

    // Extracted method for GUI initialization (SRP: isolates creation logic for easier testing/extension)
    private static void createAndShowInitialFrame() {
        Login lg = new Login();
        lg.setVisible(true);
    }
}