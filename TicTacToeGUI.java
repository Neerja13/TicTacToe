import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameOver = false;
    private JLabel statusLabel;
    private int scoreX = 0;
    private int scoreO = 0;
    private JLabel scoreLabel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setBackground(new Color(240, 240, 240));

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Create score panel
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        scorePanel.setBackground(new Color(240, 240, 240));
        scoreLabel = new JLabel("Score - X: 0 | O: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabel);
        
        // Create game board panel with GridLayout
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        boardPanel.setBackground(new Color(240, 240, 240));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create status label
        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        statusLabel.setBackground(new Color(240, 240, 240));

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
                boardPanel.add(buttons[i][j]);
            }
        }

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scorePanel, BorderLayout.CENTER);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    private void handleButtonClick(int row, int col) {
        if (gameOver || buttons[row][col].getText().length() > 0) {
            return;
        }

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setForeground(currentPlayer == 'X' ? new Color(180, 50, 50) : new Color(0, 128, 0));
        
        if (checkWin(row, col)) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            if (currentPlayer == 'X') {
                scoreX++;
            } else {
                scoreO++;
            }
            updateScore();
            gameOver = true;
            return;
        }

        if (checkDraw()) {
            statusLabel.setText("It's a draw!");
            gameOver = true;
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    private void updateScore() {
        scoreLabel.setText("Score - X: " + scoreX + " | O: " + scoreO);
    }

    private boolean checkWin(int row, int col) {
        // Check row
        if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
            highlightWinningLine(row, 0, row, 1, row, 2);
            return true;
        }

        // Check column
        if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
            highlightWinningLine(0, col, 1, col, 2, col);
            return true;
        }

        // Check diagonal
        if (row == col) {
            if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
                highlightWinningLine(0, 0, 1, 1, 2, 2);
                return true;
            }
        }

        // Check anti-diagonal
        if (row + col == 2) {
            if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                highlightWinningLine(0, 2, 1, 1, 2, 0);
                return true;
            }
        }

        return false;
    }

    private void highlightWinningLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        buttons[r1][c1].setBackground(new Color(255, 255, 200));
        buttons[r2][c2].setBackground(new Color(255, 255, 200));
        buttons[r3][c3].setBackground(new Color(255, 255, 200));
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
} 