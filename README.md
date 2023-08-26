# AI-Tuned-Tic-Tac-Challenge
Elevated Tic Tac Toe: Elevate your gaming experience with an AI-powered opponent. Immerse yourself in an advanced take on the classic game, where victory demands both strategy and adaptability!
# Advanced Tic Tac Toe Game

This is an implementation of the Advanced Tic Tac Toe game using Java's Swing GUI library.

## Overview

This project provides a graphical user interface (GUI) for playing the Advanced Tic Tac Toe game. It features a player versus computer mode where you can play against an AI opponent that uses the minimax algorithm for decision making.
## Features

- Interactive game board with buttons for making moves.
- Real-time score tracking for both Player X and Player O.
- AI opponent with adjustable difficulty level.
- Highlighting of winning line when a player wins.
## How to Play

1. Clone the repository to your local machine.
2. Compile and run the `TicTacToeGame.java` file.
3. Use your mouse to click on the empty cells to make moves.
4. The game will automatically detect wins and update the scores accordingly.
## AI Opponent

The AI opponent in this game uses the minimax algorithm to make decisions. The `minimax` function calculates the best move for the AI, and the `minimaxAlgorithm` function recursively evaluates the game states
## Adjusting AI Difficulty

The difficulty of the AI opponent can be adjusted by changing the depth parameter in the `opponentMove` method of the `TicTacToeGame` class.
