package com.example.yolov5_objdet;

public class PlantInfo {
    private String name;
    private String description;
    private boolean isEdible;

    public PlantInfo(String name, String description, boolean isEdible) {
        this.name = name;
        this.description = description;
        this.isEdible = isEdible;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEdible() {
        return isEdible;
    }
}
