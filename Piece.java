public class Piece {

    // Position of piece on board
    private int row;
    private int col;

    // Type of piece
    private String type;

    // Image file path
    private String imagePath;

    public Piece(int row, int col, String type, String imagePath) {

        this.row = row;
        this.col = col;
        this.type = type;
        this.imagePath = imagePath;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }
}