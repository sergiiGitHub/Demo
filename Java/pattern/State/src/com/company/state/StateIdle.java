package com.company.state;

import java.util.EnumMap;

/**
 * Created by sergii on 20.10.19.
 */
public final class StateIdle extends State {

    public EnumMap<StateController.EVENT, State> transition;

    public StateIdle(){
        transition = new EnumMap<>(StateController.EVENT.class);
        transition.put(StateController.EVENT.ON_VOICE_CLICK, new StateVoice());
        transition.put(StateController.EVENT.ON_APP_CLICK, new StateApp());
    }

    @Override
    public State onEvent(StateController.EVENT event) {
        return transition.get(event);
    }

    @Override
    public State.Type getType() {
        return State.Type.IDLE;
    }
}
