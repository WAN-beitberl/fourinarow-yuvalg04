import java.util.Scanner;

public class Connect4 {
    static char[][] board = new char[6][7];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initBoard();
        while (true) {
            printBoard();
            playerMove(1);
            if (checkWin(1)) {
                System.out.println("Player 1 wins!");
                break;
            }
            printBoard();
            playerMove(2);
            if (checkWin(2)) {
                System.out.println("Player 2 wins!");
                break;
            }
        }
    }

    public static void initBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '.';
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void playerMove(int player) {
        System.out.print("Player " + player + ", choose a column: ");
        int col = sc.nextInt();
        while(board[0][col] != '.'){
            System.out.println("Column is full. Choose another column.");
            col = sc.nextInt();
        }
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == '.') {
                if (player == 1) {
                    board[i][col] = 'X';
                } else {
                    board[i][col] = 'O';
                }
                break;
            }
        }
    }


    public static boolean checkWin(int player) {
        char marker = 'X';
        if (player == 2) {
            marker = 'O';
        }

        // check horizontal
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == marker && board[i][j+1] == marker && board[i][j+2] == marker && board[i][j+3] == marker) {
                    return true;
                }
            }
        }

        // check vertical
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == marker && board[i+1][j] == marker && board[i+2][j] == marker && board[i+3][j] == marker) {
                    return true;
                }
            }
        }

        // check diagonal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == marker && board[i+1][j+1] == marker && board[i+2][j+2] == marker && board[i+3][j+3] == marker) {
                    return true;
                }
                if (board [i][j+3] == marker && board[i+1][j+2] == marker && board[i+2][j+1] == marker && board[i+3][j] == marker) {
                    return true;
                }
            }
        }
        return false;
    }
}