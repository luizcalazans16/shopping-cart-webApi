package br.com.calazans.shoppingcart.app;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectorTest {

    Detector detector = new Detector();

    @Test
    public void testFalse() {
        assertEquals(detector.isSevenAndFour(2, 3), false);
    }

    @Test
    public void testTrue() {
        assertEquals(detector.isSevenAndFour(7, 4), true);
    }
}
