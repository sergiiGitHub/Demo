package com.example.myapprecycler;

/**
 * Created by Sergii Muzychuk (sergii.muzychuk@globallogic.com) on 21.05.19.
 */
public class MyData {

    private String text;

    public MyData(String name) {
        text = name;
    }

    public void setText(String str) {
        text = str;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
