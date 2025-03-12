import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner myObj = new Scanner(System.in);

        boolean player1 = true; // this boolean flips between t/f for player 1 and 2

        boolean playing = true; // when playing is true, game plays

        // the following creates a start menu for the user to adjust their playing settings
        System.out.println("Welcome to GO! Please enter the dimensions of your desired board size:");
        System.out.println("X-Coordinate: ");
        int X = myObj.nextInt(); // command-line
        System.out.println("Y-Coordinate: ");
        int Y = myObj.nextInt(); // command-line

        // 2D array for 9 X 9 go board
        String[][] Board = new String[Y][X]; // Array is initialized with values set to null
        // print board as '|' or '-|' using nested for loops
        while(playing){
            // Print horizontal grid numberings
            System.out.println("  0 1 2 3 4 5 6 7 8");
            for(int i = 0; i < Board.length; i++) {
                System.out.print(i+" "); // Print vertical grid numberings
                for(int j = 0; j < Board[i].length; j++) {
                    if(Board[i][j] == null) {
                        if(j == 0) {
                            System.out.print("|");
                        }
                        else {
                            System.out.print("-|");
                        }
                    }
                    else {
                        System.out.print(Board[i][j]);
                    }
                }
                System.out.println();
            }

            // The following while loop asks for a move and checks if it is valid
            while(true) {
                System.out.println("Input X Coordinate: ");
                int moveX = myObj.nextInt(); // command-line 
                System.out.println("Input Y Coordinate: ");
                int moveY = myObj.nextInt(); // command-line

                // if the space is empty, the move is played, otherwise a new move is asked for
                if(Board[moveY][moveX] == null) {
                // conditional that checks value of player1 and prints either a white or black piece
                Board[moveY][moveX] = (player1) ? "-o" : "-*"; 
                break;
                }
                else {
                    System.out.println("Not a valid move");
                }
            }
            player1 = !player1; // next players turn
        }
    }
}
