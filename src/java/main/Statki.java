package java.main;

import java.util.Random;
import java.util.Scanner;

class Statki
{
    boolean wygrana = false;

    Statki() {
    }

    private static int przygotowanieGry() {

        System.out.println(">>>>>> G R A   W   S T A T K I <<<<<<");
        int WP;
        do {
            System.out.println("Podaj wielkosc planszy...");
            WP = readInt();
            if (WP < 5)
                System.out.println("Za mala ta plansza :P");
        } while (WP < 5);
        return WP;
    }

    private static void rozmieszczenie
            (Pole[][] plansza, Statek[] flota, int WP) throws ArithmeticException {

        try {
            Random r = new Random();
            int id = 1;
            int i = flota.length;
            for (int i_0_ = 0; i_0_ < i; i_0_++) {
                Statek i_1_ = flota[i_0_];
                int licznik = 0;
                boolean set;
                int x;
                int y;
                do {
                    if (++licznik == 100)
                        throw new ArithmeticException();
                    if (r.nextInt(2) == 1)
                        i_1_.orientacja = true;
                    else
                        i_1_.orientacja = false;
                    i_1_.dlogosc = r.nextInt(4) + 1;
                    i_1_.uszkodzenia = i_1_.dlogosc;
                    x = r.nextInt(WP);
                    y = r.nextInt(WP);
                    set = true;
                    if (i_1_.orientacja && x <= WP - i_1_.dlogosc) {
                        for (int j = 0; j < i_1_.dlogosc; j++) {
                            if (!plansza[x + j][y].getWolne()) {
                                set = false;
                                break;
                            }
                        }
                    } else if (!i_1_.orientacja && y <= WP - i_1_.dlogosc) {
                        for (int j = 0; j < i_1_.dlogosc; j++) {
                            if (!plansza[x][y + j].getWolne()) {
                                set = false;
                                break;
                            }
                        }
                    } else
                        set = false;
                } while (!set);
                if (i_1_.orientacja) {
                    for (int j = 0; j < i_1_.dlogosc; j++) {
                        plansza[x + j][y].setNumerStatku(id);
                        plansza[x + j][y].setWolne(false);
                    }
                } else {
                    for (int j = 0; j < i_1_.dlogosc; j++) {
                        plansza[x][y + j].setNumerStatku(id);
                        plansza[x][y + j].setWolne(false);
                    }
                }
                if (i_1_.orientacja) {
                    for (int a = x - 1; a < x + i_1_.dlogosc + 1; a++) {
                        for (int b = y - 1; b < y + 2; b++) {
                            try {
                                plansza[a][b].setWolne(false);
                            } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
                                /* empty */
                            }
                        }
                    }
                } else {
                    for (int a = x - 1; a < x + 2; a++) {
                        for (int b = y - 1; b < y + i_1_.dlogosc + 1; b++) {
                            try {
                                plansza[a][b].setWolne(false);
                            } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
                                /* empty */
                            }
                        }
                    }
                }
                id++;
            }
        } catch (ArithmeticException blad) {
            for (int i = 0; i < WP; i++) {
                for (int j = 0; j < WP; j++) {
                    plansza[i][j].setTrafienie(false);
                    plansza[i][j].setNumerStatku(0);
                    plansza[i][j].setWolne(true);
                }
            }
            rozmieszczenie(plansza, flota, WP);
        }
    }

    private static void wyswietlenie(Pole[][] plansza, int WP, int licznik,
                                     int liczebnoscFloty, boolean podpowiedz) {

        System.out.println(">>>>>> G R A   W   S T A T K I <<<<<<");
        System.out.print("  ");
        if (WP > 10)
            System.out.print(" ");
        for (int i = 0; i < WP; i++)
            System.out.print(""+i+" ");
        System.out.println();
        for (int i = 0; i < WP; i++) {
            System.out.print(""+i+" ");
            if (i < 10 && WP > 10)
                System.out.print(" ");
            for (int j = 0; j < WP; j++) {
                if (plansza[i][j].getTrafienie()) {
                    if (plansza[i][j].getNumerStatku() != 0)
                        System.out.print("H");
                    else
                        System.out.print("x");
                } else if (plansza[i][j].getNumerStatku() != 0 && podpowiedz)
                    System.out.print("O");
                else
                    System.out.print("-");
                System.out.print(" ");
                if (j >= 10)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.print("  ");
        if (WP > 10)
            System.out.print(" ");
        for (int i = 0; i < WP; i++)
            System.out.print(""+i+" ");
        System.out.println();
        System.out.println("Wykonano: "+licznik+" strzalow.");
        System.out.println("Do zestrzelenie pozostalo "+liczebnoscFloty+" statkow.");
    }

    private static int readInt() {

        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static void main(String[] args) {

        int WP = przygotowanieGry();
        int IS = WP * WP / 8;
        int liczebnoscFloty = IS;
        Pole[][] plansza = new Pole[WP][WP];
        Statek[] flota = new Statek[IS];
        for (int i = 0; i < IS; i++)
            flota[i] = new Statek(WP);
        for (int i = 0; i < WP; i++) {
            for (int j = 0; j < WP; j++)
                plansza[i][j] = new Pole(WP);
        }
        rozmieszczenie(plansza, flota, WP);
        int licznik = 0;
        for (;;) {
            wyswietlenie(plansza, WP, licznik, liczebnoscFloty, false);
            System.out.println("Podaj koordynaty strzalu:");
            System.out.print("Podaj X:  ");
            int sY = readInt();
            if (sY == 777)
                wyswietlenie(plansza, WP, licznik, liczebnoscFloty, true);
            if (sY >= 0 && sY <= WP - 1) {
                System.out.print("Podaj Y:  ");
                int sX = readInt();
                if (sX >= 0 && sX <= WP - 1 && !plansza[sX][sY].getTrafienie()) {
                    licznik++;
                    plansza[sX][sY].setTrafienie(true);
                    if (plansza[sX][sY].getNumerStatku() != 0) {
                        int nrStatku = plansza[sX][sY].getNumerStatku();
                        flota[nrStatku - 1].uszkodzenia--;
                        if (flota[nrStatku - 1].uszkodzenia == 0) {
                            liczebnoscFloty--;
                            flota[nrStatku - 1].zatopienie(plansza, flota, sX,
                                    sY);
                        }
                    }
                    if (liczebnoscFloty == 0) {
                        wyswietlenie(plansza, WP, licznik, liczebnoscFloty,
                                false);
                        break;
                    }
                }
            }
        }
        System.out.println("Brawo wygra\u0142e\u015b!");
    }
}