package se.liu.ida.adany869.tddc69.lab2;

public class Poly {
    private SquareType[][] PolyArray;
    private int dimension;

    public Poly(SquareType[][] polyArray) {
        this.PolyArray = polyArray;
        dimension = this.PolyArray.length;
    }

    public int getDimension() {
        return dimension;
    }

    public void rotate(boolean rotateRight){
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
        PolyArray = newArray;
    }

    public SquareType getSquare(int height, int width){
        return PolyArray[height][width];
    }
}
