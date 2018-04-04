package com.liwanpeng.designPattern.betterDemo;

import com.liwanpeng.designPattern.fatoryPatternDemo.Circle;
import com.liwanpeng.designPattern.fatoryPatternDemo.Rectangle;
import com.liwanpeng.designPattern.fatoryPatternDemo.Shape;
import com.liwanpeng.designPattern.fatoryPatternDemo.Square;

/**
 * 使用泛型和java的反射机制实现工厂模式
 * Created by liwanpeng on 2018/3/20.
 */
public class Test {
    public static void main(String[] args) {
        Shape circle =ShapeFactory.getClass(Circle.class);
        Shape rectangle = ShapeFactory.getClass(Rectangle.class);
        Shape square = ShapeFactory.getClass(Square.class);
        circle.draw();
        rectangle.draw();
        square.draw();
    }
}
