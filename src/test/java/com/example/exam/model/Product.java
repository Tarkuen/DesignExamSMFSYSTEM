package com.example.exam.model;

import java.util.Arrays;

public class Product {

    private String productLine = "";

    private String beskrivelse;

    private int antal = 0;

    private String[] image;

//    TODO: SÆT
    private String thumbLoc = "www.bilirip.dk/thumb/dfgdfgfdg/";

    private String facLoc ="facLoc/";

//  TODO: SÆT
    private String servLoc = "www.bilirip.dk/moduler/asdsadas/"+facLoc;

    public Product() {
    }

    public Product(String beskrivelse, int antal, String[] image) {
        this.beskrivelse = beskrivelse;
        this.antal = antal;
        this.image = image;
    }

    public Product(String[] image) {
        this.image = image;
        this.beskrivelse = image[0];
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public String getLocation() {
        return thumbLoc;
    }

    public void setLocation(String location) {
        this.thumbLoc= location;
    }

    public String getThumbLoc() {
        return thumbLoc;
    }

    public String getFacLoc() {
        return facLoc;
    }

    public String getServLoc() {
        return servLoc;
    }

    public void setThumbLoc(String thumbLoc) {
        this.thumbLoc = thumbLoc;
    }

    public void setServLoc(String servLoc) {
        this.servLoc = servLoc;
    }

    public void setFacLoc(String facLoc) {
        this.facLoc = facLoc;
    }


    @Override
    public String toString() {
        return "Product{" +
                "beskrivelse='" + beskrivelse + '\'' +
                '}';
    }
}
