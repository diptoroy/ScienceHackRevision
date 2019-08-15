package com.example.sciencehack.Model;

public class TermModel {

    private String subjectImage;
    private String subjectName;

    public TermModel() {

    }

    public TermModel(String subjectImage, String subjectName) {
        this.subjectImage = subjectImage;
        this.subjectName = subjectName;
    }

    public String getSubjectImage() {
        return subjectImage;
    }

    public void setSubjectImage(String subjectImage) {
        this.subjectImage = subjectImage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
