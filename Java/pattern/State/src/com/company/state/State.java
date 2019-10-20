package com.company.state;

/**
 * Created by sergii on 20.10.19.
 */
public abstract class State {
    public abstract State onEvent(StateController.EVENT event);

    public abstract Type getType();

    public enum Type {
        IDLE,
        VOICE,
        APP
    }
}
