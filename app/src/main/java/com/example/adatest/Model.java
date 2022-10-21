package com.example.adatest;

public class Model {
    String id,name,info, store;
    String image;

    public Model(){

    }

    public Model(String id, String image, String name, String info, String store) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.info = info;
        this.store = store;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String id) {
        this.store = store;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
