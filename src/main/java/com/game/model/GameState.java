package com.game.model;

public class GameState {

    private char[] board;
    private boolean gameOver;
    private char winner; // X, O, D (draw)

    public GameState() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = '-';
        }
        gameOver = false;
        winner = '-';
    }

    public char[] getBoard() {
        return board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }
}
