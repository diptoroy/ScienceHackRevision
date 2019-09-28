package com.example.sciencehack.Model;

public class FeaturesModel {

    private String fe_name;
    private String fe_img;

    public FeaturesModel() {

    }

    public FeaturesModel(String fe_name, String fe_img) {
        this.fe_name = fe_name;
        this.fe_img = fe_img;
    }

    public String getFe_name() {
        return fe_name;
    }

    public void setFe_name(String fe_name) {
        this.fe_name = fe_name;
    }

    public String getFe_img() {
        return fe_img;
    }

    public void setFe_img(String fe_img) {
        this.fe_img = fe_img;
    }
}
