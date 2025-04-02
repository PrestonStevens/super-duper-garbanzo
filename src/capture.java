import java.util.ArrayList;
import java.util.List;

public class capture {
    // Group class to hold information about a group of stones
    private static class Group {
        String color; // Color of the group
        List<Position> stones; // List of positions of stones in the group

        // Constructor
        Group(String color) {
            this.color = color;
            this.stones = new ArrayList<>();
        }
    }

    // Position class to store coordinates
    private static class Position {
        int x, y;

        // Constructor
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Explore the group recursively
    private static void exploreGroup(int x, int y, boolean[][] visited, Group group) {
        // Base case, if the position is out of bounds, already visited, or the stone is not of the same color, return
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || visited[x][y] || board.Board[x][y].equals("") || !board.Board[x][y].equals(group.color)) {
            return;
        }

        visited[x][y] = true; // Mark the position as visited
        group.stones.add(new Position(x, y)); // Add the position to the group

        // Check the adjacent positions (up, down, left, right)
        exploreGroup(x - 1, y, visited, group); // up
        exploreGroup(x + 1, y, visited, group); // down
        exploreGroup(x, y - 1, visited, group); // left
        exploreGroup(x, y + 1, visited, group); // right
    }

    // Check for liberties (empty adjacent spaces)
    private static boolean hasLiberty(Group group) {
        for (Position pos : group.stones) {
            int x = pos.x;
            int y = pos.y;

            // Check adjacent positions (up, down, left, right) for empty spaces
            if (x - 1 >= 0 && board.Board[x - 1][y].equals("")) return true; // Up
            if (x + 1 < 9 && board.Board[x + 1][y].equals("")) return true; // Down
            if (y - 1 >= 0 && board.Board[x][y - 1].equals("")) return true; // Left
            if (y + 1 < 9 && board.Board[x][y + 1].equals("")) return true; // Right
        }
        return false; // No liberties found
    }

    // Method to handle capture logic
    static void captureGroup() {
        boolean[][] visited = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!visited[i][j] && !board.Board[i][j].equals("")) { // Only check stones (not empty spaces)
                    String color = board.Board[i][j]; // Determine the color of the stone at (i, j)
                    Group group = new Group(color);
                    exploreGroup(i, j, visited, group); // Identify all stones in this group
                    
                    // If the group has no liberties, capture the group
                    if (!hasLiberty(group)) {
                        for (Position pos : group.stones) {
                            board.Board[pos.x][pos.y] = "";  // Empty the captured stones
                            if (group.color.equals("o")) {
                                score.player2_score++; // Increment Player 2 score (capturing black stones)
                                board.black_total--; // Decrement black total
                            } else {
                                score.player1_score++; // Increment Player 1 score (capturing white stones)
                                board.white_total--; // Decrement white total
                            }
                        }
                    }
                }
            }
        }
    }
}