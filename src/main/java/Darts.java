package com.sda.darts;

public class Darts {


    enum Multiplier {
        DOUBLE, TRIPLE, XX;
    }

    private int score = 301;
    private int dartsLeft = 3;
    private boolean isFinished = false;
    private int turn = 1;
    private int lastTurnScore = score;

    public int score() {
        return score;
    }

    public int dartsLeft() {
        return dartsLeft;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int turn() {
        return turn;
    }

    public int lastTurnScore() {
        return lastTurnScore;
    }

    public void darts(int throwScore) {
        updateDartBoard(throwScore, null);
    }

    public void darts(int i, Multiplier m) {
        int multiplier;
        int throwScore;
        switch (m) {
            case DOUBLE:
                multiplier = 2;
                break;
            case TRIPLE:
                multiplier = 3;
                break;
            default:
                multiplier = 1;
                break;
        }
        throwScore = multiplier * i;
        updateDartBoard(throwScore, m);
    }

    private void updateDartBoard(int throwScore, Multiplier m) {

        int currentScore = score - throwScore;

        if (dartsLeft == 1) {
            turn++;
            dartsLeft = 3;
        } else {
            dartsLeft--;
        }

        if (currentScore == 0 && m == Multiplier.DOUBLE) {
            isFinished = true;
        } else {
            if (currentScore > 1 && dartsLeft == 3) {
                lastTurnScore = currentScore;
            }
            if (currentScore <= 1) {
                currentScore = lastTurnScore;

            }
        }
        score = currentScore;
    }

}