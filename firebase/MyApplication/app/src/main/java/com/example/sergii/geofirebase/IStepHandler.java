package com.example.sergii.geofirebase;


/**
 * Created by sergii on 09.07.17.
 */

public interface IStepHandler {
    void onStepFinish(Step step);

    public enum Step{
        Setup,
        SignIn,
    }
}

