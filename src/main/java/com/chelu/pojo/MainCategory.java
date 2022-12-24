package com.chelu.pojo;

import java.util.List;
import java.util.Map;

public class MainCategory {
    private int id;
    private String name;
    private Number main_count;
    public MainCategory() {}

    public MainCategory(int id, String name, Number main_count) {
        this.id = id;
        this.name = name;
        this.main_count = main_count;
    }

    public MainCategory(Map<String, Object> map){
        this.id = (int)map.get("id");
        this.name = (String)map.get("name");
        this.main_count = (Number)map.get("main_count");
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

    public Number getMain_count() {
        return main_count;
    }

    public void setMain_count(int main_count) {
        this.main_count = main_count;
    }


    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", main_count=" + main_count +
                '}';
    }
}
