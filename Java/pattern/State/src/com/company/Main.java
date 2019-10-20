package com.company;

import com.company.state.State;
import com.company.state.StateController;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        System.out.println("hw");

        StateController stateController = new StateController();
        check(State.Type.IDLE, stateController.getState().getType());

        stateController.onEvent(StateController.EVENT.ON_APP_CLICK);
        check(State.Type.APP, stateController.getState().getType());

        stateController.onEvent(StateController.EVENT.ON_CLOSE_CLICK);
        check(State.Type.IDLE, stateController.getState().getType());

        stateController.onEvent(StateController.EVENT.ON_CLOSE_CLICK);
        check(State.Type.IDLE, stateController.getState().getType());

    }

    private static void check(State.Type expected, State.Type actual) {
        if (expected != actual) {
            throw new Error();
        } else {
            System.out.println("pass");
        }
    }
}
