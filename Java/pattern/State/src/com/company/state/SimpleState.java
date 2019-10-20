package com.company.state;

/**
 * Created by sergii on 20.10.19.
 */
public abstract class SimpleState extends State {

    @Override
    public State onEvent(StateController.EVENT event) {
        if (event == StateController.EVENT.ON_CLOSE_CLICK) {
            return StateProvider.getStateInstance(State.Type.IDLE);
        }

        return null;
    }
}
