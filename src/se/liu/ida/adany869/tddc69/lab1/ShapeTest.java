package se.liu.ida.adany869.tddc69.lab1;

public class ShapeTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        shapes[0] = new Circle(5, 5, 5);
        shapes[1] = new Circle(10, 10, 5);
        shapes[2] = new Circle(15, 15, 15);
        shapes[3] = new Rectangle(5, 5, 5, 5);
        shapes[4] = new Rectangle(0, 0, 2, 2);

        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];

            shape.draw();
        }
    }
}
