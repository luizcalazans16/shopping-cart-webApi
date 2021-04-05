package br.com.calazans.shoppingcart.app;

public class Detector {
    public boolean isSevenAndFour(int n, int m) {
        if ((n == 7 && m == 4) || (n == 4 && m == 7)) {
            return true;
        } else {
            return false;
        }
    }
}