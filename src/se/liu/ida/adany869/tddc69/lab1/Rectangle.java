package se.liu.ida.adany869.tddc69.lab1;

public class Rectangle extends Shape{

    private Pointer pointer;
    private int width;
    private int height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;

        Rectangle rectangle = (Rectangle) o;

        if (height != rectangle.height) return false;
        if (width != rectangle.width) return false;
        if (!pointer.equals(rectangle.pointer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pointer.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.pointer = new Pointer(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("A rectangle is drawn");
    }

    public Pointer getPointer() {
        return pointer;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
