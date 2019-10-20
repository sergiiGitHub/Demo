package com.company.state;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by sergii on 20.10.19.
 */
public final class StateProvider {

    private StateProvider(){}

    private static Map<State.Type, State> states = new EnumMap<>(State.Type.class);

    static State getStateInstance(State.Type type) {
        State instance = states.get(type);
        if (instance == null) {
            instance = create(type);
            states.put(type, instance);
        }
        return instance;
    }

    private static State create(State.Type type) {
        switch (type) {
            case IDLE:
                return new StateIdle();
            case VOICE:
                return new StateVoice();
            case APP:
                return new StateVoice();
        }
        throw new IllegalArgumentException("State class not found");
    }

}
