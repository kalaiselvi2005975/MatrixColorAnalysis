import java.util.Scanner;

public class CubePatternApp {

    private static final int ORANGE = 1;
    private static final int BLUE = 0;
    private static final int SIZE = 7;
    private static final int[][] MATRIX = new int[SIZE][SIZE];
    private static boolean isMatrixFilled = false;

    public static void useDefaultMatrix() {
        int[][] defaultMatrix = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1}
        };
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                MATRIX[i][j] = defaultMatrix[i][j];
    }

    public static void storeMatrix(Scanner sc) {
        System.out.print("Do you want to use the default 7x7 matrix? (y/n): ");
        char choice = sc.next().toLowerCase().charAt(0);

        if (choice == 'y') {
            useDefaultMatrix();
            System.out.println("Default matrix loaded successfully!");
        } else if (choice == 'n') {
            System.out.println("Enter 7x7 matrix values (0 for Blue, 1 for Orange):");

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    int value = -1;
                    while (true) {
                        if (sc.hasNextInt()) {
                            value = sc.nextInt();

                            if (value == 0 || value == 1) {
                                MATRIX[i][j] = value;
                                break;
                            } else {
                                System.out.println("Wrong value entered! Only 0 or 1 allowed.");
                                System.out.print("Re-enter value for position [" + i + "][" + j + "]: ");
                            }
                        } else {
                            System.out.println("Invalid input! Please enter only numbers (0 or 1).");
                            System.out.print("Re-enter value for position [" + i + "][" + j + "]: ");
                            sc.next(); 
                        }
                    }
                }
            }

            System.out.println("Matrix entered successfully!");
        } else {
            System.out.println("Invalid choice! Please enter 'y' or 'n'.");
            sc.nextLine();
            storeMatrix(sc);
            return;
        }

        isMatrixFilled = true;

        System.out.println("Matrix stored successfully:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MATRIX[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Module 2: Count Colors
    public static void countColorBlocks() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        System.out.println("=== Count Color Blocks Module ===");
        int orangeCount = 0, blueCount = 0;

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (MATRIX[i][j] == ORANGE) orangeCount++;
                else blueCount++;

        System.out.println("Total Orange Blocks (1): " + orangeCount);
        System.out.println("Total Blue Blocks (0): " + blueCount);

        System.out.println("\nCount per Row:");
        for (int i = 0; i < SIZE; i++) {
            int rowOrange = 0, rowBlue = 0;
            for (int j = 0; j < SIZE; j++)
                if (MATRIX[i][j] == ORANGE) rowOrange++;
                else rowBlue++;
            System.out.println("Row " + i + " :: Orange: " + rowOrange + ", Blue: " + rowBlue);
        }

        System.out.println("\nCount per Column:");
        for (int j = 0; j < SIZE; j++) {
            int colOrange = 0, colBlue = 0;
            for (int i = 0; i < SIZE; i++)
                if (MATRIX[i][j] == ORANGE) colOrange++;
                else colBlue++;
            System.out.println("Column " + j + " :: Orange: " + colOrange + ", Blue: " + colBlue);
        }
        System.out.println("=================================");
    }

    // Module 3: Detect Shape 
    public static void detectShapePattern() { 
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        System.out.println("=== Detect Shape / Pattern Module ===");
        System.out.println("Blue Block Coordinates:");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MATRIX[i][j] == BLUE) 
                    System.out.println("(" + i + ", " + j + ")");
            }
        }

        boolean symmetric = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                if (MATRIX[i][j] != MATRIX[i][SIZE - 1 - j]) {
                    symmetric = false;
                    break;
                }
            }
            if (!symmetric) break;
        }

        System.out.println("\nSymmetry Check (Vertical Axis):");
        if (symmetric)
            System.out.println("The blue shape is symmetric along the vertical axis.");
        else
            System.out.println("The blue shape is NOT symmetric along the vertical axis.");
    }

    // Module 4: Border vs Center Analysis
    public static void borderVsCenter() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        int n = MATRIX.length;

        System.out.println("--- Original " + n + "x" + n + " Cube Face Matrix ---");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(MATRIX[i][j] + " ");
            System.out.println();
        }

        System.out.println("\n--- Extracting Border Elements ---");
        java.util.ArrayList<Integer> borderElements = new java.util.ArrayList<>();

        for (int j = 0; j < n; j++) borderElements.add(MATRIX[0][j]);
        for (int i = 1; i < n - 1; i++) borderElements.add(MATRIX[i][n - 1]);
        for (int j = n - 1; j >= 0; j--) borderElements.add(MATRIX[n - 1][j]);
        for (int i = n - 2; i > 0; i--) borderElements.add(MATRIX[i][0]);

        System.out.println("Total Border Elements: " + borderElements.size());
        System.out.print("Border Elements: ");
        for (int val : borderElements) System.out.print(val + " ");
        System.out.println();
        
        System.out.println("\n--- Border Pattern Visual ---");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
                    System.out.print(MATRIX[i][j] + " ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
        
        System.out.println("\n--- Extracting Inner Matrix ---");
        int innerSize = n - 2;
        if (innerSize > 0) {
            int[][] innerMatrix = new int[innerSize][innerSize];
            for (int i = 0; i < innerSize; i++)
                for (int j = 0; j < innerSize; j++)
                    innerMatrix[i][j] = MATRIX[i + 1][j + 1];

            System.out.println("Inner Matrix (" + innerSize + "x" + innerSize + "):");
            for (int i = 0; i < innerSize; i++) {
                for (int j = 0; j < innerSize; j++)
                    System.out.print(innerMatrix[i][j] + " ");
                System.out.println();
            }
        } else {
            System.out.println("No inner matrix for size " + n);
        }
    }

    // Module 5: Rotate Pattern
    public static void rotatePattern() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        System.out.println("Original Matrix:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MATRIX[i][j] + " ");
            }
            System.out.println();
        }
        
        int[][] rotated = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                rotated[j][SIZE - 1 - i] = MATRIX[i][j];
        
        System.out.println("\nMatrix After 90° Clockwise Rotation:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(rotated[i][j] + " ");
            System.out.println();
        }
        System.out.println("\nObservation: The shape is now rotated sideways.");
    }

    // Module 6: Extract Submatrix
    public static void extractSubmatrix() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter submatrix size (k): ");
        int k = scan.nextInt();

        if (k > SIZE || k <= 0) {
            System.out.println("Invalid submatrix size!");
            return;
        }

        System.out.println("Original Matrix:\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(MATRIX[i][j] + "\t");
            System.out.println();
        }

        System.out.println("\nAll possible " + k + "x" + k + " submatrices:\n");
        for (int i = 0; i <= SIZE - k; i++) {
            for (int j = 0; j <= SIZE - k; j++) {
                System.out.println("Submatrix starting at (" + i + ", " + j + "):");
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++)
                        System.out.print(MATRIX[x][y] + "\t");
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    // Module 7: Symmetry Check
    public static void symmetryCheck() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        System.out.println("=== Symmetry Check ===");
        boolean verticalSymmetry = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE / 2; j++) {
                if (MATRIX[i][j] != MATRIX[i][SIZE - 1 - j]) {
                    verticalSymmetry = false;
                    break;
                }
            }
            if (!verticalSymmetry) break;
        }
        if (verticalSymmetry)
            System.out.println("The matrix is vertically symmetric.");
        else
            System.out.println("The matrix is NOT vertically symmetric.");

        boolean horizontalSymmetry = true;
        for (int i = 0; i < SIZE / 2; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MATRIX[i][j] != MATRIX[SIZE - 1 - i][j]) {
                    horizontalSymmetry = false;
                    break;
                }
            }
            if (!horizontalSymmetry) break;
        }
        if (horizontalSymmetry)
            System.out.println("The matrix is horizontally symmetric.");
        else
            System.out.println("The matrix is NOT horizontally symmetric.");
    }

    // Module 8: largest Connected Region
    public static void largestConnectedRegion() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        int[][] visited = new int[SIZE][SIZE];
        int maxRegion = 0;

        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MATRIX[i][j] == BLUE && visited[i][j] == 0) {
                    int size = dfs(MATRIX, visited, i, j, dRow, dCol);
                    if (size > maxRegion)
                        maxRegion = size;
                }
            }
        }
        System.out.println("Largest Connected Region of Blue (0's): " + maxRegion);
    }

    private static int dfs(int[][] matrix, int[][] visited, int row, int col, int[] dRow, int[] dCol) {
        visited[row][col] = 1;
        int size = 1;

        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];

            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && 
                matrix[newRow][newCol] == BLUE && visited[newRow][newCol] == 0) {
                size += dfs(matrix, visited, newRow, newCol, dRow, dCol);
            }
        }
        return size;
    }

    // Module 9: Colour Replacement
    public static void replacePatternColor() {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println(" Color Replacement ");
        int fromValue;
        while (true) {
            System.out.print("Enter the number you want to replace (0 for Blue or 1 for Orange): ");
            fromValue = sc.nextInt();
            if (fromValue == 0 || fromValue == 1) break;
            else System.out.println("Invalid input! Please enter only 0 or 1.");
        }

        System.out.println("Choose the new color code: ");
        System.out.println("0 = BLUE, 1 = ORANGE, 2 = GREEN, 3 = YELLOW");
        int toValue = sc.nextInt();

        int replaceCount = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (MATRIX[i][j] == fromValue) {
                    MATRIX[i][j] = toValue;
                    replaceCount++;
                }

        System.out.println(" UPDATED MATRIX ");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(MATRIX[i][j] + " ");
            System.out.println();
        }

        System.out.println("Replaced " + replaceCount + " cells from " + fromValue + " to " + toValue);
    }

    // Module 10: Pattern Matching
    public static void patternMatchingExtended(Scanner sc) {
        if (!isMatrixFilled) {
            System.out.println("Please enter the matrix first (Option 1).");
            return;
        }

        System.out.println("Enter 3x3 pattern values (0 for Blue, 1 for Orange):");
        int[][] PATTERN = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                PATTERN[i][j] = sc.nextInt();

        int matches = 0;
        for (int r = 0; r <= SIZE - 3; r++) {
            for (int c = 0; c <= SIZE - 3; c++) {
                boolean match = true;
                for (int i = 0; i < 3 && match; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (PATTERN[i][j] != MATRIX[r + i][c + j]) {
                            match = false;
                            break;
                        }
                    }
                }
                if (match) {
                    matches++;
                    System.out.printf("Match found at (Row %d, Col %d)\n", r, c);
                }
            }
        }
        if (matches == 0) System.out.println("No match found.");
        else System.out.println("Total matches: " + matches);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Cube Pattern Analysis Menu =====");
            System.out.println("1. Matrix Representation");
            System.out.println("2. Count Color Blocks");
            System.out.println("3. Detect Shape / Pattern");
            System.out.println("4. Border vs Center Analysis");
            System.out.println("5. Rotation of Pattern");
            System.out.println("6. Submatrix Extraction");
            System.out.println("7. Symmetry Check");
            System.out.println("8. Largest Connected Region");
            System.out.println("9. Replace Pattern Color");
            System.out.println("10. Pattern Matching");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> storeMatrix(sc);
                case 2 -> countColorBlocks();
                case 3 -> detectShapePattern(); 
                case 4 -> borderVsCenter();
                case 5 -> rotatePattern();
                case 6 -> extractSubmatrix();
                case 7 -> symmetryCheck();
                case 8 -> largestConnectedRegion();
                case 9 -> replacePatternColor();
                case 10 -> patternMatchingExtended(sc);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}
