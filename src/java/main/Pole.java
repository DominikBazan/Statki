package java.main;

class Pole
{
    private boolean trafienie = false;
    private boolean wolne;
    private int numerStatku = 0;

    Pole(int WP) {
        wolne = true;
    }

    public void setTrafienie(boolean t) {
        trafienie = t;
    }

    public boolean getTrafienie() {
        return trafienie;
    }

    public void setWolne(boolean t) {
        wolne = t;
    }

    public boolean getWolne() {
        return wolne;
    }

    public void setNumerStatku(int t) {
        numerStatku = t;
    }

    public int getNumerStatku() {
        return numerStatku;
    }
}