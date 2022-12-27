package com.chelu.pojo;

import java.util.List;
import java.util.Map;

public class MainCategory {
    private int id;
    private String name;
    public MainCategory() {}

    public MainCategory(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public MainCategory(Map<String, Object> map){
        this.id = (int)map.get("ID");
        this.name = (String)map.get("NAME");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
