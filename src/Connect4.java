import java.util.Scanner;

public class Connect4 {
    private char[][] grid;
    private int turn;
    private char player;
    private boolean winner;
    private Scanner in;

    public Connect4() {
        grid = new char[6][7];
        turn = 1;
        player = 'R';
        winner = false;
        in = new Scanner(System.in);

        //initialize array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.play();
    }


    public void play() {
        while (!winner && turn <= 42) {
            boolean validPlay;
            int play;
            do {
                display();

                System.out.print("Player " + player + ", choose a column: ");
                play = in.nextInt();

                //validate play
                validPlay = validate(play);

            } while (!validPlay);

            //drop the checker
            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][play] == ' ') {
                    grid[row][play] = player;
                    break;
                }
            }

            //determine if there is a winner
            winner = isWinner();

            //switch players
            if (player == 'R') {
                player = 'B';
            } else {
                player = 'R';
            }

            turn++;
        }
        display();

        if (winner) {
            if (player == 'R') {
                System.out.println("Black won");
            } else {
                System.out.println("Red won");
            }
        } else {
            System.out.println("It's a tie");
        }
    }

    private void display() {
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (int row = 0; row < grid.length; row++) {
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }

    private boolean validate(int column) {
        //valid column?
        if (column < 0 || column > grid[0].length) {
            return false;
        }

        //full column?
        if (grid[0][column] != ' ') {
            return false;
        }

        return true;
    }

    private boolean isWinner() {
        //check for 4 across
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row][col + 1] == player &&
                        grid[row][col + 2] == player &&
                        grid[row][col + 3] == player) {
                    return true;
                }
            }
        }
        //check for 4 up and down
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == player &&
                        grid[row + 1][col] == player &&
                        grid[row + 2][col] == player &&
                        grid[row + 3][col] == player) {
                    return true;
                }
            }
        }
        //check upward diagonal
        for (int row = 3; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row - 1][col + 1] == player &&
                        grid[row - 2][col + 2] == player &&
                        grid[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        //check downward diagonal
        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == player &&
                        grid[row + 1][col + 1] == player &&
                        grid[row + 2][col + 2] == player &&
                        grid[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }
}