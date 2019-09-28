package com.example.sciencehack.Model;

public class QuotesModel {

    private String quotes;
    private String quotesAuthor;

    public QuotesModel() {

    }

    public QuotesModel(String quotes, String quotesAuthor) {
        this.quotes = quotes;
        this.quotesAuthor = quotesAuthor;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getQuotesAuthor() {
        return quotesAuthor;
    }

    public void setQuotesAuthor(String quotesAuthor) {
        this.quotesAuthor = quotesAuthor;
    }
}
