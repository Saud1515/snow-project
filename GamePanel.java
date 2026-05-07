import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // Game board object
    private GameBoard board;

    // Size of each square
    private final int CELL_SIZE = 100;

    public GamePanel() {

        // Create board
        board = new GameBoard();

        // Set window size
        setPreferredSize(new Dimension(500, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw board
        drawBoard(g);

        // Draw all pieces
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {

        // Draw 5x4 board
        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Draw square
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

                // Load hole image
                ImageIcon hole =
                        new ImageIcon("images/hole.png");

                // Draw hole
                g.drawImage(
                        hole.getImage(),
                        x + 25,
                        y + 25,
                        50,
                        50,
                        null);
            }
        }
    }

    private void drawPieces(Graphics g) {

        // Draw every piece
        for (Piece piece : board.getPieces()) {

            int x = piece.getCol() * CELL_SIZE;
            int y = piece.getRow() * CELL_SIZE;

            // Load image
            ImageIcon image =
                    new ImageIcon(piece.getImagePath());

            // Draw image
            g.drawImage(
                    image.getImage(),
                    x + 10,
                    y + 10,
                    80,
                    80,
                    null);
        }
    }
}