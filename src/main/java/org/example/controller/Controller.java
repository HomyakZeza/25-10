package org.example.controller;

import org.example.controller.action.ActionDelete;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller extends MenuState {
    private Model model;
    private MyFrame frame;

    private MenuState menuState;

    private MyShape sampleShape;

    public static Controller instance;
    private MyPanel panel;

    private UndoMachine undoMachine;
    private ActionDraw actionDraw;

    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Controller() {
        menuState = new MenuState();
        ShapeCreator shapeCreator = ShapeCreator.getInstance();
        shapeCreator.configure(menuState);

        model = new Model();
        menuState.setAction(new ActionDraw(model));

        panel = new MyPanel(this);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        undoMachine = new UndoMachine();

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menuState);
        menuCreator.setModel(model);

        undoMachine.setUndoActionListener(menuCreator.getUndoButton());
        undoMachine.setRedoActionListener(menuCreator.getRedoButton());

        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.WEST);
    }

    public void getPointOne(Point2D p) {
        AppAction currentAction = menuState.getAction();
        currentAction.mousePressed(p);
        currentAction.execute();
        undoMachine.add(currentAction);
        undoMachine.updateButtons();
    }

    public void getPointTwo(Point2D p) {
        AppAction currentAction = menuState.getAction();
        currentAction.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
