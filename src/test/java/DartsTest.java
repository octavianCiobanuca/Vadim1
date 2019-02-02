package com.sda.darts;

import org.junit.Before;
import org.junit.Test;

import static com.sda.darts.Darts.Multiplier.DOUBLE;
import static com.sda.darts.Darts.Multiplier.TRIPLE;
import static org.junit.Assert.*;

public class DartsTest {

    private com.sda.darts.Darts dartBoard;

    @Before
    public void setUp(){
        dartBoard = new com.sda.darts.Darts();
    }

    @Test
    public void testANewGameStart301() {
        assertEquals(301, dartBoard.score());
        assertFalse(dartBoard.isFinished());
    }

    @Test
    public void testANewGameHasCorrectNumberOfDartsAndTurn(){
        assertEquals(3, dartBoard.dartsLeft());
        assertEquals(1, dartBoard.turn());
    }

    @Test
    public void testScoreAfterSimpleThrow(){
        dartBoard.darts(20);
        assertEquals(281, dartBoard.score());
    }

    @Test
    public void testScoreAfterDoubleThrow(){
        dartBoard.darts(20, Darts.Multiplier.DOUBLE);
        assertEquals(261, dartBoard.score());
    }

    @Test
    public void testScoreAfterTripleThrow(){
        dartBoard.darts(20, TRIPLE);
        assertEquals(241, dartBoard.score());
    }

    @Test
    public void testScoreAfterUnknownThrow(){
        dartBoard.darts(20, Darts.Multiplier.XX);
        assertEquals(281, dartBoard.score());
    }

    @Test
    public void testDartsLeftAndTurnAreUpdatedCorrectly(){
        dartBoard.darts(1);
        assertEquals(2, dartBoard.dartsLeft());
        assertEquals(1, dartBoard.turn());

        dartBoard.darts(1);
        assertEquals(1, dartBoard.dartsLeft());
        assertEquals(1, dartBoard.turn());

        dartBoard.darts(1);
        assertEquals(3, dartBoard.dartsLeft());
        assertEquals(2, dartBoard.turn());
    }

    @Test
    public void testGameIsFinished(){
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        assertEquals(121, dartBoard.score());
        assertEquals(2, dartBoard.turn());
        dartBoard.darts(17, TRIPLE);  // 70
        dartBoard.darts(50);            // 20
        dartBoard.darts(10, DOUBLE);
        assertEquals(0, dartBoard.score());
        assertTrue(dartBoard.isFinished());
    }

    @Test
    public void testIfBustBellowZero(){
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        assertEquals(121, dartBoard.score());
        assertEquals(2, dartBoard.turn());

        dartBoard.darts(17, TRIPLE);  // 70
        dartBoard.darts(17, TRIPLE);  // 19
        dartBoard.darts(10, DOUBLE);  // -1
        assertEquals(121, dartBoard.score());
        assertEquals(3, dartBoard.turn());
        assertEquals(3, dartBoard.dartsLeft());
    }

    @Test
    public void testIfBustScoringOne(){
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        assertEquals(121, dartBoard.score());
        assertEquals(2, dartBoard.turn());

        dartBoard.darts(17, TRIPLE);  // 70
        dartBoard.darts(17, TRIPLE);  // 19
        dartBoard.darts(9, DOUBLE);  // 1
        assertEquals(121, dartBoard.score());
        assertEquals(3, dartBoard.turn());
        assertEquals(3, dartBoard.dartsLeft());
    }

    @Test
    public void testIfBustScoringZeroWithoutDouble(){
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        dartBoard.darts(20, TRIPLE);
        assertEquals(121, dartBoard.score());
        assertEquals(2, dartBoard.turn());
        dartBoard.darts(17, TRIPLE);  // 70
        dartBoard.darts(50);            // 20
        dartBoard.darts(20);        // 0
        assertEquals(121, dartBoard.score());
    }


}