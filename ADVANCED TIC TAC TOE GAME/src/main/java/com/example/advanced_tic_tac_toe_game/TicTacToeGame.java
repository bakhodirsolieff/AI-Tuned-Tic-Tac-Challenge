package com.example.advanced_tic_tac_toe_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private JLabel playerXScoreLabel;
    private JLabel playerOScoreLabel;

    private char currentPlayer;
    private int lastClickedRow = -1;
    private int lastClickedCol = -1;
    private int playerXScore = 0;
    private int playerOScore = 0;

    public TicTacToeGame() {
        setTitle("Advanced Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();
    }

    private void initializeButtons() {
        JPanel panel = new JPanel(new GridLayout(4, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                panel.add(buttons[row][col]);
            }
        }


        playerXScoreLabel = new JLabel("Player X: " + playerXScore);
        playerOScoreLabel = new JLabel("Player O: " + playerOScore);

        panel.add(playerXScoreLabel);
        panel.add(playerOScoreLabel);

        add(panel);
    }

    private boolean checkWin(char player) {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(player)) &&
                    buttons[row][1].getText().equals(String.valueOf(player)) &&
                    buttons[row][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(player)) &&
                    buttons[1][col].getText().equals(String.valueOf(player)) &&
                    buttons[2][col].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(Character.toString(currentPlayer));
                lastClickedRow = row;
                lastClickedCol = col;

                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    highlightWinningLine();
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    opponentMove();
                }
            }
        }
    }

    private void highlightWinningLine() {
        if (lastClickedRow != -1 && lastClickedCol != -1) {
            for (int i = 0; i < 3; i++) {
                buttons[lastClickedRow][i].setBackground(Color.GREEN);
                buttons[i][lastClickedCol].setBackground(Color.GREEN);
            }
        }
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(null);
            }
        }
        lastClickedRow = -1;
        lastClickedCol = -1;
        currentPlayer = 'X';

        if (checkWin('X')) {
            playerXScore++;
            playerXScoreLabel.setText("Player X: " + playerXScore);
        } else if (checkWin('O')) {
            playerOScore++;
            playerOScoreLabel.setText("Player O: " + playerOScore);
        }
    }

    private void opponentMove() {
        if (currentPlayer == 'O') {
            int[] bestMove = minimax(2, 'O'); // Adjust the depth level as desired
            int row = bestMove[0];
            int col = bestMove[1];

            buttons[row][col].setText(Character.toString(currentPlayer));
            lastClickedRow = row;
            lastClickedCol = col;

            if (checkWin(currentPlayer)) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                highlightWinningLine();
                resetGame();
            } else {
                currentPlayer = 'X';
            }
        }
    }

    private int[] minimax(int depth, char player) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    buttons[row][col].setText(Character.toString(player));
                    int score = minimaxAlgorithm(depth, player == 'O' ? 'X' : 'O');
                    buttons[row][col].setText("");

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }

        return bestMove;
    }
    private int minimaxAlgorithm(int depth, char player) {
        if (checkWin('O')) return 1;
        if (checkWin('X')) return -1;
        if (depth == 0) return 0;

        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    buttons[row][col].setText(Character.toString(player));
                    int score = minimaxAlgorithm(depth - 1, player == 'O' ? 'X' : 'O');
                    buttons[row][col].setText("");

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                    }
                }
            }
        }

        return bestScore;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}