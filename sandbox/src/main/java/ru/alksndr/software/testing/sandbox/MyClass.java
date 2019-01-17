package ru.alksndr.software.testing.sandbox;

public class MyClass {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(6,8);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и "+ r.b + " = " + r.area());

        Square s = new Square(65);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
    }
}