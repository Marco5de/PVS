public class Circle implements GeomCalculation {
    private double radius;

    Circle(double radius){
        this.radius = radius;
    }

    public double getPerimeter(){
        return radius * 2 * Math.PI;
    }

    public double getArea(){
        return Math.pow(radius,2) * Math.PI;
    }
}
