package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;

public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private AppAction actionDraw;

    public MenuState(){
        shapeType = ShapeType.RECTANGLE;
        color = Color.BLUE;
        fill = false;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public AppAction getAction() {
        return actionDraw;
    }

    public void setAction(AppAction actionDraw) {
        this.actionDraw = actionDraw;
    }
}
