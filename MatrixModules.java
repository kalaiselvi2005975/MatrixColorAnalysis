package src;

import java.util.*;

/**
 * MatrixModules Class
 * Contains all 10 independent matrix operations (modules).
 */
public class MatrixModules {

    // 1️⃣ Store Matrix (7x7)
    public static void storeMatrix(Scanner sc) {
        System.out.println("Enter 7x7 matrix (1=Orange, 0=Blue):");
        for (int i = 0; i < MatrixMain.SIZE; i++) {
            for (int j = 0; j < MatrixMain.SIZE; j++) {
                MatrixMain.MATRIX[i][j] = sc.nextInt();
            }
        }
        MatrixMain.isMatrixFilled = true;
        System.out.println("✅ Matrix stored successfully!");
    }

    // 2️⃣ Count Color Blocks
    public static void countColorBlocks() {
        checkMatrix();
        int orange = 0, blue = 0;
        for (int[] row : MatrixMain.MATRIX) {
            for (int val : row) {
                if (val == MatrixMain.ORANGE) orange++;
                else blue++;
            }
        }
        System.out.println("🟠 Orange blocks: " + orange);
        System.out.println("🔵 Blue blocks: " + blue);
    }

    // 3️⃣ Detect Shape or Pattern
    public static void detectShapePattern() {
        checkMatrix();
        System.out.println("Detecting pattern...");
        // Basic example – detects filled square at center
        int sum = 0;
        for (int i = 2; i <= 4; i++)
            for (int j = 2; j <= 4; j++)
                sum += MatrixMain.MATRIX[i][j];
        System.out.println(sum == 9 ? "🟧 Square pattern detected!" : "⚪ No clear shape detected.");
    }

    // 4️⃣ Border vs Center
    public static void borderVsCenter() {
        checkMatrix();
        int border = 0, center = 0;
        for (int i = 0; i < MatrixMain.SIZE; i++) {
            for (int j = 0; j < MatrixMain.SIZE; j++) {
                if (i == 0 || j == 0 || i == MatrixMain.SIZE - 1 || j == MatrixMain.SIZE - 1)
                    border += MatrixMain.MATRIX[i][j];
                else
                    center += MatrixMain.MATRIX[i][j];
            }
        }
        System.out.println("Border sum: " + border + ", Center sum: " + center);
    }

    // 5️⃣ Rotate Pattern 90°
    public static void rotatePattern() {
        checkMatrix();
        int[][] rotated = new int[MatrixMain.SIZE][MatrixMain.SIZE];
        for (int i = 0; i < MatrixMain.SIZE; i++)
            for (int j = 0; j < MatrixMain.SIZE; j++)
                rotated[j][MatrixMain.SIZE - 1 - i] = MatrixMain.MATRIX[i][j];
        printMatrix(rotated, "Rotated Matrix (90°)");
    }

    // 6️⃣ Submatrix Extraction
    public static void extractSubmatrix() {
        checkMatrix();
        System.out.println("Extracting 3x3 submatrix (center):");
        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++) System.out.print(MatrixMain.MATRIX[i][j] + " ");
            System.out.println();
        }
    }

    // 7️⃣ Symmetry Check
    public static void symmetryCheck() {
        checkMatrix();
        boolean symmetric = true;
        for (int i = 0; i < MatrixMain.SIZE; i++) {
            for (int j = 0; j < MatrixMain.SIZE / 2; j++) {
                if (MatrixMain.MATRIX[i][j] != MatrixMain.MATRIX[i][MatrixMain.SIZE - 1 - j])
                    symmetric = false;
            }
        }
        System.out.println(symmetric ? "✅ Matrix is symmetric!" : "❌ Matrix is not symmetric.");
    }

    // 8️⃣ Largest Connected Region (DFS)
    public static void largestConnectedRegion() {
        checkMatrix();
        boolean[][] visited = new boolean[MatrixMain.SIZE][MatrixMain.SIZE];
        int maxRegion = 0;
        for (int i = 0; i < MatrixMain.SIZE; i++) {
            for (int j = 0; j < MatrixMain.SIZE; j++) {
                if (MatrixMain.MATRIX[i][j] == 1 && !visited[i][j])
                    maxRegion = Math.max(maxRegion, dfs(i, j, visited));
            }
        }
        System.out.println("🔶 Largest connected orange region size: " + maxRegion);
    }

    private static int dfs(int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= MatrixMain.SIZE || y >= MatrixMain.SIZE ||
            MatrixMain.MATRIX[x][y] == 0 || visited[x][y]) return 0;
        visited[x][y] = true;
        int count = 1;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++)
            count += dfs(x + dx[k], y + dy[k], visited);
        return count;
    }

    // 9️⃣ Replace Pattern Color
    public static void replacePatternColor() {
        checkMatrix();
        for (int i = 0; i < MatrixMain.SIZE; i++)
            for (int j = 0; j < MatrixMain.SIZE; j++)
                MatrixMain.MATRIX[i][j] = 1 - MatrixMain.MATRIX[i][j]; // Flip colors
        printMatrix(MatrixMain.MATRIX, "Color replaced (1↔0)");
    }

    // 🔟 Pattern Matching
    public static void patternMatchingExtended(Scanner sc) {
        checkMatrix();
        System.out.println("Enter 3x3 pattern to search (0/1):");
        int[][] pattern = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pattern[i][j] = sc.nextInt();
        boolean found = false;
        for (int i = 0; i <= 4; i++)
            for (int j = 0; j <= 4; j++) {
                if (match(i, j, pattern)) found = true;
            }
        System.out.println(found ? "✅ Pattern found!" : "❌ Pattern not found.");
    }

    private static boolean match(int x, int y, int[][] pattern) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (MatrixMain.MATRIX[x + i][y + j] != pattern[i][j]) return false;
        return true;
    }

    // Utility Methods
    private static void printMatrix(int[][] matrix, String title) {
        System.out.println("\n" + title + ":");
        for (int[] row : matrix) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }

    private static void checkMatrix() {
        if (!MatrixMain.isMatrixFilled) {
            System.out.println("⚠️ Please store the matrix first (Option 1).");
            System.exit(0);
        }
    }
}
