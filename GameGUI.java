import javax.swing.*;
import java.awt.*;

public class GameGUI {
        static int snowRow = 2;
        static int snowCol = 2;
    public static void main(String[] args) {

        JFrame frame = new JFrame("Snow Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));

        // load images
        ImageIcon treeIcon = new ImageIcon("images/tree.png");
        ImageIcon snowIcon = new ImageIcon("images/snowball_small.png");
        ImageIcon headIcon = new ImageIcon("images/head_blue.png");
        //SNOWBALL POSITION
        

        // create board
        for (int i = 0; i < 20; i++) {

            JLabel label = new JLabel();
             label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            // tree
            if (i == 6) {
                label.setIcon(treeIcon);
            }

            // snowball
            else if (i == (snowRow * 5 + snowCol)) {
              label.setIcon(snowIcon);
}

            // head
            else if (i == 18) {
                label.setIcon(headIcon);
            }

            panel.add(label);
        }

        frame.add(panel);

        frame.setVisible(true);
        frame.addKeyListener(new java.awt.event.KeyAdapter() {

    public void keyPressed(java.awt.event.KeyEvent e) {

        int key = e.getKeyCode();

        // move right
        if (key == java.awt.event.KeyEvent.VK_D) {
            snowCol++;
        }

        // move left
        if (key == java.awt.event.KeyEvent.VK_A) {
            snowCol--;
        }

        // move up
        if (key == java.awt.event.KeyEvent.VK_W) {
            snowRow--;
        }

        // move down
        if (key == java.awt.event.KeyEvent.VK_S) {
            snowRow++;
        }

        System.out.println("Snowball moved!");
    }
});
    }
}
