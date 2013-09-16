package se.liu.ida.adany869.tddc69.lab2;

public class Poly {
    private SquareType[][] PolyArray;


    public Poly(SquareType[][] polyArray) {
        PolyArray = polyArray;
    }

    public void rotate(boolean rotateRight){
        int dimension = PolyArray.length;
        int maxIndex = dimension-1;
        SquareType[][] newArray = new SquareType[dimension][dimension];
        if (rotateRight) {
            for (int i=0; i<dimension; i++) {
                for (int j=0; j<dimension; j++){
                    newArray[i][j] = PolyArray[maxIndex-j][i];
                }
            }

        }
        else {
            for (int i=0; i<dimension; i++) {
                for (int j=0; j<dimension; j++){
                    newArray[maxIndex-j][i] = PolyArray[i][j];
                }
            }
        }
    }
}
