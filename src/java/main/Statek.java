package java.main;

class Statek {

    private boolean zatopiony;
    boolean orientacja = true;
    int dlogosc = 1;
    int uszkodzenia = 1;
    private int WPStatek;

    Statek(int WP) {
        WPStatek = WP;
    }

    void zatopienie(Pole[][] plansza, Statek[] flota, int sX, int sY) {

        int nr = plansza[sX][sY].getNumerStatku();
        flota[nr - 1].zatopiony = true;
        for (int i = 0; i < WPStatek; i++) {
            for (int j = 0; j < WPStatek; j++) {
                if (plansza[i][j].getNumerStatku() == nr) {
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int l = j - 1; l < j + 2; l++) {
                            try {
                                plansza[k][l].setTrafienie(true);
                            } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
                                /* empty */
                            }
                        }
                    }
                }
            }
        }
    }

}