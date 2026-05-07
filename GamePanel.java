import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    // Game board object
    private GameBoard board;

    // Selected piece
    private Piece selectedPiece;

    // Size of each square
    private final int CELL_SIZE = 100;

    public GamePanel() {

        // Create board
        board = new GameBoard();

        // Set window size
        setPreferredSize(new Dimension(500, 400));

        // Mouse click controls
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                handleClick(e.getX(), e.getY());
            }
        });
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

    private void handleClick(int mouseX, int mouseY) {

        int col = mouseX / CELL_SIZE;
        int row = mouseY / CELL_SIZE;

        // Select piece
        if (selectedPiece == null) {

            for (Piece piece : board.getPieces()) {

                if (piece.getRow() == row &&
                        piece.getCol() == col) {

                    // Only snowballs can move
                    if (!piece.getType().equals("head")
                            &&
                            !piece.getType().equals("tree")) {

                        selectedPiece = piece;
                    }
                }
            }
        }

        // Move selected piece
        else {

            int dx = 0;
            int dy = 0;

            // Move right
            if (col > selectedPiece.getCol()) {
                dx = 1;
            }

            // Move left
            else if (col < selectedPiece.getCol()) {
                dx = -1;
            }

            // Move down
            else if (row > selectedPiece.getRow()) {
                dy = 1;
            }

            // Move up
            else if (row < selectedPiece.getRow()) {
                dy = -1;
            }

            movePiece(selectedPiece, dx, dy);

            selectedPiece = null;

            repaint();
        }
    }

    private void movePiece(Piece piece, int dx, int dy) {

    int newRow = piece.getRow();
    int newCol = piece.getCol();

    while (true) {

        newRow += dy;
        newCol += dx;

        // Check outside board
        if (newRow < 0 || newRow >= 4
                || newCol < 0 || newCol >= 5) {

            board.getPieces().remove(piece);

            return;
        }

        boolean blocked = false;

        // Check collision
        for (Piece other : board.getPieces()) {

            if (other != piece
                    &&
                    other.getRow() == newRow
                    &&
                    other.getCol() == newCol) {

                blocked = true;
                break;
            }
        }

        // Stop before obstacle
        if (blocked) {

            break;
        }
    }

    // Final position
    piece.setRow(newRow - dy);
    piece.setCol(newCol - dx);
}
}

