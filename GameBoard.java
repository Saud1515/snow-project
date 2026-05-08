import java.util.ArrayList;

public class GameBoard {

    // Board size
    public static final int ROWS = 4;
    public static final int COLS = 5;

    // Store all game pieces
    private ArrayList<Piece> pieces;

    public GameBoard() {

        pieces = new ArrayList<>();

        // Blue head
        pieces.add(new Piece(
                3,
                0,
                "head",
                "images/head_blue.png"));

        // Small snowball
        pieces.add(new Piece(
                0,
                4,
                "small",
                "images/snowball_small.png"));

        // Large snowball
        pieces.add(new Piece(
                3,
                4,
                "large",
                "images/snowball_large.png"));

        // Tree obstacle
        pieces.add(new Piece(
                1,
                1,
                "tree",
                "images/tree.png"));
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}