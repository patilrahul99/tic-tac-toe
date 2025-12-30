package com.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.game.model.GameState;

@Service
public class GameService {

    private static final char HUMAN = 'X';
    private static final char AI = 'O';
    private static final char EMPTY = '-';

    private final Random random = new Random();

    public GameState play(GameState state, int index, String difficulty) {

        // Ignore invalid moves
        if (state.isGameOver() || state.getBoard()[index] != EMPTY) {
            return state;
        }

        // Human move
        state.getBoard()[index] = HUMAN;
        checkResult(state);

        if (!state.isGameOver()) {
            int aiMove = switch (difficulty.toLowerCase()) {
                case "easy" -> easyMove(state);
                case "medium" -> mediumMove(state);
                case "hard" -> hardMove(state);
                default -> mediumMove(state);
            };

            if (aiMove != -1) {
                state.getBoard()[aiMove] = AI;
                checkResult(state);
            }
        }

        return state;
    }

    /* ---------------- EASY ---------------- */
    // Completely random
    private int easyMove(GameState state) {
        List<Integer> empty = getEmptyCells(state);
        return empty.isEmpty() ? -1 : empty.get(random.nextInt(empty.size()));
    }

    /* ---------------- MEDIUM ---------------- */
    // 50% random, 50% smart
    private int mediumMove(GameState state) {
        if (random.nextBoolean()) {
            return easyMove(state);
        }
        return hardMove(state);
    }

    /* ---------------- HARD (UNBEATABLE) ---------------- */
    // Minimax AI
    private int hardMove(GameState state) {
    int bestScore = Integer.MIN_VALUE;
    int bestMove = -1;

    for (int i : getEmptyCells(state)) {
        state.getBoard()[i] = AI;
        int score = minimax(state, 0, false);
        state.getBoard()[i] = EMPTY;

        if (score > bestScore) {
            bestScore = score;
            bestMove = i;
        }
    }
    return bestMove;
}

private int minimax(GameState state, int depth, boolean isMaximizing) {
    Character winner = checkWinner(state);

    if (winner != null) {
        if (winner == AI) return 10 - depth;
        if (winner == HUMAN) return depth - 10;
        return 0;
    }

    if (isMaximizing) {
        int best = Integer.MIN_VALUE;
        for (int i : getEmptyCells(state)) {
            state.getBoard()[i] = AI;
            best = Math.max(best, minimax(state, depth + 1, false));
            state.getBoard()[i] = EMPTY;
        }
        return best;
    } else {
        int best = Integer.MAX_VALUE;
        for (int i : getEmptyCells(state)) {
            state.getBoard()[i] = HUMAN;
            best = Math.min(best, minimax(state, depth + 1, true));
            state.getBoard()[i] = EMPTY;
        }
        return best;
    }
}


    /* ---------------- HELPERS ---------------- */

    private List<Integer> getEmptyCells(GameState state) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (state.getBoard()[i] == EMPTY) {
                list.add(i);
            }
        }
        return list;
    }

    private void checkResult(GameState state) {
        Character winner = checkWinner(state);
        if (winner != null) {
            state.setGameOver(true);
            state.setWinner(winner);
        }
    }

    private Character checkWinner(GameState state) {
        char[] b = state.getBoard();
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : wins) {
            if (b[w[0]] != EMPTY && b[w[0]] == b[w[1]] && b[w[1]] == b[w[2]]) {
                return b[w[0]];
            }
        }

        for (char c : b) {
            if (c == EMPTY) return null;
        }

        return 'D'; // Draw
    }
}
