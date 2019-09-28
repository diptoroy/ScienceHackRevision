package com.example.sciencehack.Model;

public class ScientistViewModel {

    public String scientist_name;
    public String scientist_image;
    public String scientist_works;
    public String scientist_field;
    public String scientist_details;
    public String scientist_death;
    public String scientist_country;
    public String scientist_birth;
    public String scientist_award;
    public String scientist_book;

    public ScientistViewModel() {

    }

    public ScientistViewModel(String scientist_name, String scientist_image, String scientist_works, String scientist_field, String scientist_details, String scientist_death, String scientist_country, String scientist_birth, String scientist_award, String scientist_book) {
        this.scientist_name = scientist_name;
        this.scientist_image = scientist_image;
        this.scientist_works = scientist_works;
        this.scientist_field = scientist_field;
        this.scientist_details = scientist_details;
        this.scientist_death = scientist_death;
        this.scientist_country = scientist_country;
        this.scientist_birth = scientist_birth;
        this.scientist_award = scientist_award;
        this.scientist_book = scientist_book;
    }

    public String getScientist_name() {
        return scientist_name;
    }

    public void setScientist_name(String scientist_name) {
        this.scientist_name = scientist_name;
    }

    public String getScientist_image() {
        return scientist_image;
    }

    public void setScientist_image(String scientist_image) {
        this.scientist_image = scientist_image;
    }

    public String getScientist_works() {
        return scientist_works;
    }

    public void setScientist_works(String scientist_works) {
        this.scientist_works = scientist_works;
    }

    public String getScientist_field() {
        return scientist_field;
    }

    public void setScientist_field(String scientist_field) {
        this.scientist_field = scientist_field;
    }

    public String getScientist_details() {
        return scientist_details;
    }

    public void setScientist_details(String scientist_details) {
        this.scientist_details = scientist_details;
    }

    public String getScientist_death() {
        return scientist_death;
    }

    public void setScientist_death(String scientist_death) {
        this.scientist_death = scientist_death;
    }

    public String getScientist_country() {
        return scientist_country;
    }

    public void setScientist_country(String scientist_country) {
        this.scientist_country = scientist_country;
    }

    public String getScientist_birth() {
        return scientist_birth;
    }

    public void setScientist_birth(String scientist_birth) {
        this.scientist_birth = scientist_birth;
    }

    public String getScientist_award() {
        return scientist_award;
    }

    public void setScientist_award(String scientist_award) {
        this.scientist_award = scientist_award;
    }

    public String getScientist_book() {
        return scientist_book;
    }

    public void setScientist_book(String scientist_book) {
        this.scientist_book = scientist_book;
    }
}
