import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class score {
    static int player1_score = 0; // Player 1 score
    static int player2_score = 0; // Player 2 score
    static boolean[][] visited = new boolean[9][9]; // Array to track visited spaces

    static void calculateTerritoryScores() {
        // Reset visited array
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
        player1_score = board.black_total;
        player2_score = board.white_total; // Start with the number of captured stones
        // Check all empty spaces and form territories
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!visited[i][j] && board.Board[i][j].equals("")) { // Only unvisited empty spaces
                    // Find and process the territory group
                    List<int[]> territoryGroup = new ArrayList<>();
                    findTerritory(i, j, territoryGroup);
                    
                    // Determine the type of the territory (which player controls it, or neutral)
                    String territoryOwner = determineTerritoryOwner(territoryGroup);
                    
                    // Update the score based on the territory owner
                    if (territoryOwner != null) {
                        int territorySize = territoryGroup.size();
                        if (territoryOwner.equals("o")) {
                            player1_score += territorySize;
                        } else if (territoryOwner.equals("*")) {
                            player2_score += territorySize;
                        }
                    }
                }
            }
        }
    }

    // Use DFS to find all the empty spaces that belong to the same territory group
    static void findTerritory(int x, int y, List<int[]> territoryGroup) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || visited[x][y] || !board.Board[x][y].equals("")) {
            return; // Out of bounds or already visited or not an empty space
        }
        
        visited[x][y] = true;
        territoryGroup.add(new int[]{x, y}); // Add this point to the territory group
        
        // Explore in all four directions
        findTerritory(x - 1, y, territoryGroup); // Up
        findTerritory(x + 1, y, territoryGroup); // Down
        findTerritory(x, y - 1, territoryGroup); // Left
        findTerritory(x, y + 1, territoryGroup); // Right
    }

    // Determine the owner of the territory (either "o", "*", or "neutral")
    static String determineTerritoryOwner(List<int[]> territoryGroup) {
        Set<String> borderingStones = new HashSet<>(); // Set to track bordering stones
        // Check the border stones surrounding the territory group
        for (int[] point : territoryGroup) {
            int x = point[0];
            int y = point[1];
            
            // Check all 4 neighboring directions for stones
            checkNeighboringStone(x - 1, y, borderingStones); // Up
            checkNeighboringStone(x + 1, y, borderingStones); // Down
            checkNeighboringStone(x, y - 1, borderingStones); // Left
            checkNeighboringStone(x, y + 1, borderingStones); // Right
        }

        // Determine the result based on the bordering stones
        if (borderingStones.size() == 1) {
            return borderingStones.iterator().next(); // Return the single stone color that controls the territory
        }
        
        // If both colors are present, it's neutral territory
        return null;
    }

    // Check the stone at a specific neighbor and add it to the borderingStones set
    static void checkNeighboringStone(int x, int y, Set<String> borderingStones) {
        if (x >= 0 && x < 9 && y >= 0 && y < 9 && !board.Board[x][y].equals("")) {
            borderingStones.add(board.Board[x][y]); // Add the stone color to the set
        }
    }
}