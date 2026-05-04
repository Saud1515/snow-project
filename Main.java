public class Main {

    public static void main(String[] args) {

        // board 5x4
        char[][] board = new char[4][5];

        // fill with empty spaces
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = '.';
            }
        }

        // add some pieces (example level 1)
        board[1][1] = 'T'; // tree
        board[2][2] = 'S'; // small snowball
        board[3][3] = 'H'; // head

        // print board
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
