import java.util.Scanner;

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

Scanner input = new Scanner(System.in);

System.out.println("Move snowball (w/a/s/d): ");
char move = input.next().charAt(0);

int row = 2;
int col = 2;

// remove old position
board[row][col] = '.';

// move snowball
if (move == 'w') {
    row--;
}
else if (move == 's') {
    row++;
}
else if (move == 'a') {
    col--;
}
else if (move == 'd') {
    col++;
}

// place new position
board[row][col] = 'S';

 

        // print board
       for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
