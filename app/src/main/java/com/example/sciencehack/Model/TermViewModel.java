package com.example.sciencehack.Model;

public class TermViewModel {

    public String title;
    public String formula;

    public TermViewModel() {

    }

    public TermViewModel(String title, String formula) {
        this.title = title;
        this.formula = formula;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
