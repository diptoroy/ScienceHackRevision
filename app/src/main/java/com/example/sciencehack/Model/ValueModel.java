package com.example.sciencehack.Model;

public class ValueModel {
    private String valueName;
    private String valueDetails;

    public ValueModel() {

    }

    public ValueModel(String valueName, String valueDetails) {
        this.valueName = valueName;
        this.valueDetails = valueDetails;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueDetails() {
        return valueDetails;
    }

    public void setValueDetails(String valueDetails) {
        this.valueDetails = valueDetails;
    }
}
