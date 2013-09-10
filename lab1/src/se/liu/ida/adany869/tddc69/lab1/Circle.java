package se.liu.ida.adany869.tddc69.lab1;

public class Circle extends Shape {

    private Pointer pointer;
    private int radius;

    public Circle(int x, int y, int radius) {
        this.pointer = new Pointer(x, y);
        this.radius = radius;

        System.out.println( "A circle is constructed");
    }

    @Override
    public void draw() {
        System.out.println("A circle is drawn");
    }

    public Pointer getPointer() {
        return pointer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;

        Circle circle = (Circle) o;

        if (radius != circle.radius) return false;
        if (!pointer.equals(circle.pointer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pointer.hashCode();
        result = 31 * result + radius;
        return result;
    }

    public int getRadius() {
        return radius;
    }
}
