package org.example.model;

import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable{
    private Color color;
    private RectangularShape shape;
    private FillBehavior fb;

    public Color getColor() {
        return color;
    }

    public RectangularShape getShape() {
        return shape;
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D x, Point2D y) {

        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);

    }

    @Override
    public MyShape clone() {

        MyShape clone = new MyShape();
        clone.fb = fb.clone();
        RectangularShape anotherShape = (RectangularShape) shape.clone();
        clone.setShape(anotherShape);
        clone.fb.setShape(anotherShape);
        return clone;

    }
}
