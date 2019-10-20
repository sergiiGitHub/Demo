package com.company.state;

/**
 * Created by sergii on 20.10.19.
 */
public class StateController {

    private State currentState;

    public StateController() {
        currentState = StateProvider.getStateInstance(State.Type.IDLE);
    }

    public State getState() {
        return currentState;
    }

    public void onEvent(EVENT event) {
        currentState = currentState.onEvent(event);
    }

    public enum EVENT {
        ON_VOICE_CLICK,
        ON_APP_CLICK,
        ON_CLOSE_CLICK
    }
}
