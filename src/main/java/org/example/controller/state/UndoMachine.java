package org.example.controller.state;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    @Setter
    private CommandActionListener undoActionListener;

    @Setter
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }
    public void updateButtons(){
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnableUndo());
        }
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnableRedo());
        }
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        //TODO: Определить переход по состоянию
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }
}
