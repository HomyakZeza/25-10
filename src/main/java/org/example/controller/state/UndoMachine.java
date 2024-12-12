package org.example.controller.state;

import lombok.Setter;
import org.example.controller.action.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    @Setter
    private CommandActionListener undo;

    @Setter
    private CommandActionListener redo;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        if (isEnableRedo()) {
            undoRedoState = undoRedoState.redo();
            updateButtons();
        }
    }

    public void executeUndo() {
        if (isEnableUndo()) {
            undoRedoState = undoRedoState.undo();
            updateButtons();
        }

    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }
    public void updateButtons(){
        undo.setEnabled(isEnableUndo());
        redo.setEnabled(isEnableRedo());

    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
        updateButtons();
    }
}
