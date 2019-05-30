package main.java;

class Pole
{
    private boolean trafienie = false;
    private boolean wolne;
    private int numerStatku = 0;

    Pole(int WP) {
        wolne = true;
    }

    void setTrafienie(boolean t) {
        trafienie = t;
    }

    boolean getTrafienie() {
        return trafienie;
    }

    void setWolne(boolean t) {
        wolne = t;
    }

    boolean getWolne() {
        return wolne;
    }

    void setNumerStatku(int t) {
        numerStatku = t;
    }

    int getNumerStatku() {
        return numerStatku;
    }
}