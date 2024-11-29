package org.example.controller.state;

import org.example.controller.action.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        //TODO: Определить
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }
        if(undoActivityList.size() > 0){
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        }
        else {
            return new StateDisableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        }
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}
