import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner myObj = new Scanner(System.in);

        int pass_counter = 0; // counter for pass turns

        board game = new board(); // create a new board object
        
        while(true) {
            game.print_board(); // print the board

            if (pass_counter == 3) { // if both players pass, end the game
                System.out.println("Both players passed. Game over.");
                break; // exit the loop
            }

            System.out.println("Pass turn? (y/n): ");
            String pass = myObj.next(); // get user input for pass turn

            if(pass.equals("y")) {
                pass_counter++; // increment pass counter
                game.player1 = !game.player1; // flip player1 boolean
                System.out.println("Turn passed.");
                continue; // skip to the next iteration of the loop
            } else if (pass.equals("n")) {
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                continue; // skip to the next iteration of the loop
            }
            
            System.out.println("Enter x and y coordinates to place a piece: ");
            int x = myObj.nextInt();
            int y = myObj.nextInt();
            game.place(x, y); // place a piece on the board
            game.print_board(); // print the board
            capture.captureGroup(); // check for captures
        } 
        // Test case for the capture method
        /* 
        game.place(3, 1); //b
        game.place(3, 2); //w
        game.place(4, 1); //b
        game.place(4, 2); //w
        game.place(5, 2); //b
        game.place(5, 1); //w
        game.place(5, 3); //b
        game.place(6, 4); //w
        game.place(3, 4); //b
        game.place(3, 3); //w
        game.place(2, 2); //b
        game.place(4, 3); //w
        game.place(2, 3); //b
        game.place(2, 7); //w
        game.place(0,4); //b
        game.place(1, 6); //w
        game.place(1, 4); //b
        game.place(0, 6); //w
        game.place(5, 0); //b
        game.place(2, 8); //w
        game.place(4, 4); //b
        capture.captureGroup();
        game.print_board();
        */
        
        // Calculate territory score for player 1 and player 2
        score.calculateTerritoryScores();
        System.out.println("Player 1 Score: " + score.player1_score);
        System.out.println("Player 2 Score: " + score.player2_score);
        

        myObj.close();
    }
}