// You should be able to substitute a subclass for a base class
// If there is a base class you should be able to stick a subclass there not breaking anything
// Solution: instead of additional setter method its better to use Factory Design Pattern
// or simple boolean function accessing weather the rectangle is square or not
package patterns.solid.LiskovSubstitutionPronciple;

public class Demo {
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width *10
        System.out.println("Expected area of  " + (width * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);
        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq);
    }




}

class RectangleFactory{ // better solution
    public static Rectangle newRectangle(int width, int height)
    {
        return new Rectangle(width, height);
    }
    public static Rectangle newSquare(int side){
        return new Rectangle(side,side);
    }
}

class Rectangle {
    protected int height, width;

    public Rectangle() {

    }

    public Rectangle(int width, int height) {
    this.height = height;
    this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }

    public boolean isSquare(){ // better solution
        return width == height;
    }
}

class Square extends Rectangle {
    public Square() {

    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width); // wrong
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height); // wrong
    }
}
