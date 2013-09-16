package se.liu.ida.adany869.tddc69.lab2;

public class Poly {
    private SquareType[][] PolyArray;


    public Poly(SquareType[][] polyArray) {
        PolyArray = polyArray;
    }

    public void rotate(boolean rotateRight){
        SquareType[][] newArray = new SquareType[PolyArray.length][PolyArray[0].length];
        if (rotateRight) {
            if (PolyArray.length = 4){
            newArray[0][0] = PolyArray[3][0];
            newArray[0][1] = PolyArray[2][0];
            newArray[0][2] = PolyArray[1][0];
            newArray[0][3] = PolyArray[0][0];
            newArray[1][0] = PolyArray[3][1];
            newArray[1][1] = PolyArray[2][1];
            newArray[1][2] = PolyArray[2][2];
            newArray[1][3] = PolyArray[0][1];
            newArray[2][0] = PolyArray[0][4];
            newArray[2][1] = PolyArray[0][3];
            newArray[2][2] = PolyArray[][];
            newArray[2][3] = PolyArray[][];
            newArray[3][0] = PolyArray[0][4];
            newArray[3][1] = PolyArray[0][3];
            newArray[3][2] = PolyArray[][];
            newArray[3][3] = PolyArray[][];
            }
        }
    }
}
