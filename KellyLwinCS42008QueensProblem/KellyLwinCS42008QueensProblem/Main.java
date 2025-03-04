/***************************************************************
 * file: Main.java
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

public class Main {
    public static void main(String[] args) {
        // Start the GUI on the event dispatch thread
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    static void createAndShowGUI() {
        // Create the main application window
        JFrame frame = new JFrame("Kelly Lwin: 8-Queens Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Create and add the chessboard panel
        ChessBoard board = new ChessBoard();
        frame.add(board, BorderLayout.CENTER);

        // Create the "Solve" button
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> board.solve());

        // Add the button to the bottom of the frame
        frame.add(solveButton, BorderLayout.SOUTH);
        // Center the window on the screen and display it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
