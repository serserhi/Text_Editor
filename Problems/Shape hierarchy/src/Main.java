abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;
    public Triangle (double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double getPerimeter () {
        return a + b + c;
    }
    @Override
    public double getArea () {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class Rectangle extends Shape {
    double a;
    double b;
    public Rectangle (double a, double b){
        this.a = a;
        this.b = b;
    }
    @Override
    public double getPerimeter () {
        return 2 * a + 2 * b;
    }
    @Override
    public double getArea () {
        return a * b;
    }
}

class Circle extends Shape {
    double r;
    public Circle (double r){
        this.r = r;
    }
    @Override
    public double getPerimeter () {
        return 2 * r * Math.PI;
    }
    @Override
    public double getArea () {
        return Math.PI * r * r;
    }
}