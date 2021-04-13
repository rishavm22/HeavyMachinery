package com.example.heavymachinery.model;

public class MyList {
    private String id;
    private String name;
    private String modelNo;
    private String stock;
    private String price;

    public MyList() {
    }

    public MyList(String name, String modelNo, String stock, String price) {
        this.name = name;
        this.modelNo = modelNo;
        this.stock = stock;
        this.price = price;
    }

    public MyList(String id, String name, String modelNo, String stock, String price) {
        this.id = id;
        this.name = name;
        this.modelNo = modelNo;
        this.stock = stock;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
