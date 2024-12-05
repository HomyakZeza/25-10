package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionDelete implements AppAction {
    private Model model;
    private MyShape deletedShape;
    private int deletedIndex;

    public ActionDelete(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point2D point) {

        for (int i = model.getShapeList().size() - 1; i >= 0; i--) {
            MyShape shape = model.getShapeList().get(i);
            if (shape.getShape().contains(point)) {
                deletedShape = shape;
                deletedIndex = i;
                break;
            }
        }
        model.update();
    }

    @Override
    public void mouseDragged(Point2D point) {

    }

    @Override
    public void execute() {
        if (deletedShape != null) {
            model.getShapeList().remove(deletedShape);
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (deletedShape != null) {
            model.getShapeList().add(deletedIndex, deletedShape);
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        return new ActionDelete(model);
    }
}
