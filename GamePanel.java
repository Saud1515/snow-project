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

        // Set panel size
        setPreferredSize(new Dimension(500, 400));

        // Mouse clicks
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

        drawBoard(g);
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {

        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 5; col++) {

                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Draw square
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

                // Draw hole image
                ImageIcon hole =
                        new ImageIcon("images/hole.png");

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

                if (piece.getRow() == row
                        &&
                        piece.getCol() == col) {

                    // Trees cannot move
                    if (!piece.getType().equals("tree")) {

                        selectedPiece = piece;
                    }
                }
            }
        }

        // Move piece
        else {

            int dx = 0;
            int dy = 0;

            // Right
            if (col > selectedPiece.getCol()) {
                dx = 1;
            }

            // Left
            else if (col < selectedPiece.getCol()) {
                dx = -1;
            }

            // Down
            else if (row > selectedPiece.getRow()) {
                dy = 1;
            }

            // Up
            else if (row < selectedPiece.getRow()) {
                dy = -1;
            }

            movePiece(selectedPiece, dx, dy);

            selectedPiece = null;

            repaint();
        }
    }

    private void movePiece(Piece piece, int dx, int dy) {

        int currentRow = piece.getRow();
        int currentCol = piece.getCol();

        while (true) {

            int nextRow = currentRow + dy;
            int nextCol = currentCol + dx;

            // Outside board
            if (nextRow < 0 || nextRow >= 4
                    ||
                    nextCol < 0 || nextCol >= 5) {

                board.getPieces().remove(piece);

                return;
            }

            Piece hitPiece = null;

            // Collision check
            for (Piece other : board.getPieces()) {

                if (other != piece
                        &&
                        other.getRow() == nextRow
                        &&
                        other.getCol() == nextCol) {

                    hitPiece = other;
                    break;
                }
            }

            // Piece collision
            if (hitPiece != null) {

                // Small + large = stack
                if (piece.getType().equals("small")
                        &&
                        hitPiece.getType().equals("large")) {

                    board.getPieces().remove(piece);
                    board.getPieces().remove(hitPiece);

                    board.getPieces().add(new Piece(
                            hitPiece.getRow(),
                            hitPiece.getCol(),
                            "stack",
                            "images/snowman_stack.png"));

                    return;
                }

                // Head + stack = complete snowman
                if (piece.getType().equals("head")
                        &&
                        hitPiece.getType().equals("stack")) {

                    board.getPieces().remove(piece);
                    board.getPieces().remove(hitPiece);

                    board.getPieces().add(new Piece(
                            hitPiece.getRow(),
                            hitPiece.getCol(),
                            "snowman",
                            "images/snowman_blue.png"));

                    JOptionPane.showMessageDialog(
                            this,
                            "You Win!");

                    return;
                }

                // Stop before obstacle
                piece.setRow(currentRow);
                piece.setCol(currentCol);

                return;
            }

            // Continue moving
            currentRow = nextRow;
            currentCol = nextCol;
        }
    }
}