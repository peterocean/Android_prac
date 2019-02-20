package com.example.administrator.sqlitetest;

import org.litepal.crud.LitePalSupport;

public class Category extends LitePalSupport {
    private  int id;
    private String categoryName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategoryName() {
        return  categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
