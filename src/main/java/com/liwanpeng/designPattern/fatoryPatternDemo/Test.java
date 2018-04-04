package com.liwanpeng.designPattern.fatoryPatternDemo;

/**
 * Created by liwanpeng on 2018/3/20.
 */
public class Test {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape("circle");
        Shape rectangle= shapeFactory.getShape("rectangle");
        Shape square = shapeFactory.getShape("square");
        circle.draw();
        rectangle.draw();
        square.draw();
    }
}
