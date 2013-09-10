package se.liu.ida.adany869.tddc69.lab1;

public class Pointer {
    private int xCoord;
    private int yCoord;

    public Pointer(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pointer)) return false;

        Pointer pointer = (Pointer) o;

        if (xCoord != pointer.xCoord) return false;
        if (yCoord != pointer.yCoord) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xCoord;
        result = 31 * result + yCoord;
        return result;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }
}
