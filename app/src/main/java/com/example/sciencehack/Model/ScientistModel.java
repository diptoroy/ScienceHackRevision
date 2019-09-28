package com.example.sciencehack.Model;

public class ScientistModel {

    private String sProfile;
    private String sName;

    public ScientistModel() {

    }

    public ScientistModel(String sProfile, String sName) {
        this.sProfile = sProfile;
        this.sName = sName;
    }

    public String getsProfile() {
        return sProfile;
    }

    public void setsProfile(String sProfile) {
        this.sProfile = sProfile;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
