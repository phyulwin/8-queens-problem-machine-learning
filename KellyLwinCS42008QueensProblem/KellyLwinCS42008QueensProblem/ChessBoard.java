/***************************************************************
 * file: ChessBoard.java
 * author: Kelly L.
 * class: CS 4200.01 Artificial Intelligence
 *
 * assignment: Assignment 1
 * date last modified: 3/3/2025
 *
 * purpose: This program creates a simple GUI that shows all
 * possible solutions to the 8-Queens Problem.
 * note: The code contains content assisted by ChatGPT and Gemini.
 ****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessBoard extends JPanel {
    // Instance Variables
    private List<int[]> solutions = new ArrayList<>();
    private int[] queens = new int[8]; // Stores column positions of queens for each row
    private boolean solved = false;
    private Random random = new Random();

    // Initializes the panel size and generates all possible solutions to the 8-Queens problem.
    public ChessBoard() {
        setPreferredSize(new Dimension(480, 480)); // Ensure it's divisible by 8
        findAllSolutions();
    }

    // Selects a random valid 8-Queens solution from the precomputed list and updates the board.
    public void solve() {
        if (!solutions.isEmpty()) {
            int[] newSolution = solutions.get(random.nextInt(solutions.size()));
            System.arraycopy(newSolution, 0, queens, 0, 8);
            solved = true;
            repaint();
        }
    }

    // Uses backtracking to generate all 92 valid solutions and stores them in a list.
    private void findAllSolutions() {
        int[] board = new int[8];
        placeQueens(board, 0);
    }

    // Recursively tries to place queens row by row, ensuring no conflicts.
    private void placeQueens(int[] board, int row) {
        if (row == 8) {
            solutions.add(board.clone());
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (isValid(board, row, col)) {
                board[row] = col;
                placeQueens(board, row + 1);
            }
        }
    }

    // Checks if placing a queen at (row, col) is safe (no same-column or diagonal conflicts).
    private boolean isValid(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // Draws the chessboard and queens dynamically based on the selected solution.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(getWidth(), getHeight()); // Ensure a square board
        int tileSize = size / 8;

        // Adjust panel size to prevent clipping
        setPreferredSize(new Dimension(tileSize * 8, tileSize * 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                g.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }

        if (solved) {
            g.setColor(Color.RED);
            for (int row = 0; row < 8; row++) {
                int col = queens[row];
                g.fillOval(col * tileSize + tileSize / 4, row * tileSize + tileSize / 4, tileSize / 2, tileSize / 2);
            }
        }
    }
}
