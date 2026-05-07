import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // Create game window
        JFrame frame = new JFrame("Snow Problem");

        // Close game when window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add game panel to window
        frame.add(new GamePanel());

        // Fit window to correct size
        frame.pack();

        // Open window in center of screen
        frame.setLocationRelativeTo(null);

        // Make window visible
        frame.setVisible(true);
    }
}