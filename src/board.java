public class board {
    // 2D array for 9 X 9 go board
    static String[][] Board = {
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""},
                              {"", "", "", "", "", "", "", "", ""}
                            };

    static int black_total = 0; // total number of black pieces on the board
    static int white_total = 0; // total number of white pieces on the board

    // boolean to keep track of player1
    public boolean player1 = true; // true = player1, false = player2

    // Print the board
    public void print_board() {
        // print board as '|' or '-|' using nested for loops
        // Print horizontal grid numberings
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for(int i = 0; i < Board.length; i++) {
            System.out.print(i+" "); // Print vertical grid numberings
            for(int j = 0; j < Board[i].length; j++) {
                if(Board[i][j].equals("")) {
                    if(j == 0) {
                        System.out.print("|");
                    } else {
                        System.out.print("-|");
                    }
                } else {
                    if (j == 0) {
                        System.out.print(Board[i][j]);
                    } else {
                        System.out.print("-" + Board[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    public void place(int x, int y) {
        // if the space is empty, the move is played, otherwise a new move is asked for
        if(Board[y][x].equals("")) {
            // conditional that checks value of player1 and prints either a white or black piece
            Board[y][x] = (player1) ? "o" : "*"; 
            if (player1) {
                black_total++; // increment black total
            } else {
                white_total++; // increment white total
            }
            player1 = !player1; // flip player1 boolean  
        } 
        else if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            System.out.println("Out of Bounds! Try again.");
        } else {
            System.out.println("Space is already taken! Try again.");
        }
    }
}
