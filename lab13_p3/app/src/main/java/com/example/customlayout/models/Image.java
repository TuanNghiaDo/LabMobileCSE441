package com.example.customlayout.models;

public class Image {
    private int img; //lưu id của hình ảnh
    private String name;
     public int getImg(){
         return img;
     }

     public String getName(){
         return name;
     }

    public Image(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Image() {
    }
}
