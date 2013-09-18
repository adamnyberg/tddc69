package se.liu.ida.adany869.tddc69.lab2;

public class TetrominoMaker {

    public int getNumberOfTypes() {
        return SquareType.getNumberOfTypes();
    }

    public Poly getPoly(int n) {
        SquareType square = SquareType.getSquareType(n);
        SquareType[][] polyArray;
        switch (square) {
            case I:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY},
                                {SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY},
                                {SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY},
                                {SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY}
                        }
                );

            case J:

                return new Poly(
                    new SquareType[][]{
                        {SquareType.EMPTY,  SquareType.J, SquareType.EMPTY},
                        {SquareType.EMPTY,  SquareType.J, SquareType.EMPTY},
                        {SquareType.J,      SquareType.J, SquareType.EMPTY}
                    }
                );

            case L:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.EMPTY, SquareType.L, SquareType.EMPTY},
                                {SquareType.EMPTY, SquareType.L, SquareType.EMPTY},
                                {SquareType.EMPTY, SquareType.L, SquareType.L}
                        }
                );

            case O:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.O, SquareType.O},
                                {SquareType.O, SquareType.O},
                                {SquareType.O, SquareType.O}
                        }
                );

            case S:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.EMPTY,  SquareType.S, SquareType.S},
                                {SquareType.EMPTY,  SquareType.S, SquareType.EMPTY},
                                {SquareType.S,      SquareType.S, SquareType.EMPTY}
                        }
                );

            case T:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.T,      SquareType.T, SquareType.T},
                                {SquareType.EMPTY,  SquareType.T, SquareType.EMPTY},
                                {SquareType.EMPTY,  SquareType.T, SquareType.EMPTY}
                        }
                );

            case Z:
                return new Poly(
                        new SquareType[][]{
                                {SquareType.Z,      SquareType.Z, SquareType.EMPTY},
                                {SquareType.EMPTY,  SquareType.Z, SquareType.EMPTY},
                                {SquareType.EMPTY,  SquareType.Z, SquareType.Z}
                        }
                );

            default:
                break;
        }
        return null;
    }
}
