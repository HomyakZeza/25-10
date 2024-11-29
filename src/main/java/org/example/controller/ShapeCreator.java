package org.example.controller;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.geom.RectangularShape;

public class ShapeCreator {

    private MenuState state;
    public static ShapeCreator instance;
    public static synchronized ShapeCreator getInstance(){

        if (instance == null){
            instance = new ShapeCreator();
        }
        return instance;
    }

    public ShapeCreator() {

    }

    public void configure(MenuState state){
        this.state = state;
    }

    public MyShape createShape(){
        MyShape newShape = new MyShape();
        RectangularShape shape = state.getShapeType().createShape();

        FillBehavior fillBehavior = state.isFill() ? new Fill() : new NoFill();
        fillBehavior.setShape(shape);
        fillBehavior.setColor(state.getColor());

        newShape.setFb(fillBehavior);
        newShape.setShape(shape);

        return newShape;
    }
}
