package com.sergii.Entity;

/**
 * Created by sergii on 23.10.16.
 */
public class Student {

    private int id;
    private String Name;
    private String Cource;

    public Student(int id, String name, String cource) {
        this.id = id;
        Name = name;
        Cource = cource;
    }

    public Student(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCource() {
        return Cource;
    }

    public void setCource(String cource) {
        Cource = cource;
    }
}
