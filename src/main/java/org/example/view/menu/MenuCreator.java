package org.example.view.menu;


import org.example.controller.MenuState;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator extends MenuState {
    private static MenuCreator instance;
    private JMenuBar menuBar;
    private ActionDraw actionDraw;

    private Model model;

    private MenuState state;

    private JRadioButtonMenuItem rgbButton;
    private MenuCreator(){
        menuBar = createMenuBar();
    }
    public static MenuCreator getInstance(){
        if (instance == null){
            instance = new MenuCreator();
        }
        return instance;
    }
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        JMenu actionMenu = createActionMenu();
        JMenu fillMenu = createFillMenu();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(actionMenu);
        menuBar.add(fillMenu);

        return menuBar;
    }


    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> {
            state.setShapeType(ShapeType.RECTANGLE);
        });
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            state.setShapeType(ShapeType.ELLIPSE);
        });
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        JRadioButtonMenuItem orange = new JRadioButtonMenuItem("Оранжевый");
        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зелёный");
        JRadioButtonMenuItem cyan = new JRadioButtonMenuItem("Бирюзовый");
        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Чёрный");
        JRadioButtonMenuItem white = new JRadioButtonMenuItem("Белый");
        rgbButton = new JRadioButtonMenuItem("RGB");


        blue.addActionListener(e -> {
            state.setColor(Color.BLUE);
        });
        colorMenu.add(blue);
        group.add(blue);

        red.addActionListener(e -> {
            state.setColor(Color.RED);
        });
        colorMenu.add(red);
        group.add(red);

        orange.addActionListener(e -> {
            state.setColor(Color.ORANGE);
        });
        colorMenu.add(orange);
        group.add(orange);

        green.addActionListener(e -> {
            state.setColor(Color.GREEN);
        });
        colorMenu.add(green);
        group.add(green);

        cyan.addActionListener(e -> {
            state.setColor(Color.CYAN);
        });
        colorMenu.add(cyan);
        group.add(cyan);

        black.addActionListener(e -> {
            state.setColor(Color.BLACK);
        });
        colorMenu.add(black);
        group.add(black);

        white.addActionListener(e -> {
            state.setColor(Color.WHITE);
        });
        colorMenu.add(white);
        group.add(white);


        return colorMenu;
    }

    private JMenu createFillMenu(){
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Есть");
        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Нет");

        fill.addActionListener(e -> {
            state.setFill(true);

        });
        fillMenu.add(fill);
        group.add(fill);

        noFill.addActionListener(e -> {
            state.setFill(false);

        });
        fillMenu.add(noFill);
        group.add(noFill);


        return fillMenu;
    }

    private JMenu createActionMenu(){
        JMenu actionMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисовать");

        move.addActionListener(e -> {
            state.setAction(new ActionMove(model));

        });
        actionMenu.add(move);
        group.add(move);

        draw.addActionListener(e -> {
            state.setAction(new ActionDraw(model));

        });
        actionMenu.add(draw);
        group.add(draw);

        return actionMenu;
    }

    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();

        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(rgbButton, false,  state, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(true, state);
        menuItems.add(new CommandActionListener("Заливка", fillIco, fillCommand));

        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(false, state);
        menuItems.add(new CommandActionListener("Заливка",noFillIco,noFillCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(ShapeType.RECTANGLE, state);
        menuItems.add(new CommandActionListener("Фигура", rectangularIco, rectangularCommand));

        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(ShapeType.ELLIPSE, state);
        menuItems.add(new CommandActionListener("Фигура", ellipseIco, ellipseCommand));

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(state, new ActionDraw(model));
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(state, new ActionMove(model));
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));

        return menuItems;
    }


    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
