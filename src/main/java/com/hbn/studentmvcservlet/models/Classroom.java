package com.hbn.studentmvcservlet.models;

public class Classroom {
    private Long id_class;
    private String name_class;

    public Classroom() {
    }

    public Classroom(Long id_class, String name_class) {
        this.id_class = id_class;
        this.name_class = name_class;
    }

    public Long getId_class() {
        return id_class;
    }

    public void setId_class(Long id_class) {
        this.id_class = id_class;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }
}
