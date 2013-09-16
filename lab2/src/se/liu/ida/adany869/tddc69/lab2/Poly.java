package se.liu.ida.adany869.tddc69.lab2;

public class Poly {
    private SquareType[][] PolyArray;

    public Poly getPoly(int n) {
        switch (n) {
            case 0:
                return poly0();

            case 1:
                return poly0();
        }
        return poly0();
    }

    public Poly poly0() {
        return new Poly();
    }
}
