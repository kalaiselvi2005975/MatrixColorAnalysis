package src;

import java.util.Scanner;

/**
 * MatrixColorAnalysis Project
 * Main class that provides the menu-driven interface.
 * Each option calls a specific function from MatrixModules.
 * 
 * Done by: Leena Shivana S (2403917720522127)
 * Team: Balasathana M.K, Ranjani S, Sabari Mathesh M, Kalaiselvi M,
 *       Haripriya I, Eniyavarshini K, Ujitha I, Bharini B, Srinija CSG
 */
public class MatrixMain {
    static final int SIZE = 7;            // Matrix size (7x7)
    static final int ORANGE = 1;          // Orange color = 1
    static final int BLUE = 0;            // Blue color = 0
    static int[][] MATRIX = new int[SIZE][SIZE];
    static boolean isMatrixFilled = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MATRIX COLOR ANALYSIS MENU ===");
            System.out.println("1. Store Matrix");
            System.out.println("2. Count Color Blocks");
            System.out.println("3. Detect Shape / Pattern");
            System.out.println("4. Border vs Center");
            System.out.println("5. Rotate Pattern");
            System.out.println("6. Submatrix Extraction");
            System.out.println("7. Symmetry Check");
            System.out.println("8. Largest Connected Region");
            System.out.println("9. Replace Pattern Color");
            System.out.println("10. Pattern Matching");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> MatrixModules.storeMatrix(sc);
                case 2 -> MatrixModules.countColorBlocks();
                case 3 -> MatrixModules.detectShapePattern();
                case 4 -> MatrixModules.borderVsCenter();
                case 5 -> MatrixModules.rotatePattern();
                case 6 -> MatrixModules.extractSubmatrix();
                case 7 -> MatrixModules.symmetryCheck();
                case 8 -> MatrixModules.largestConnectedRegion();
                case 9 -> MatrixModules.replacePatternColor();
                case 10 -> MatrixModules.patternMatchingExtended(sc);
                case 0 -> System.out.println("✅ Exiting program... Thank you!");
                default -> System.out.println("⚠️ Invalid choice. Please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
