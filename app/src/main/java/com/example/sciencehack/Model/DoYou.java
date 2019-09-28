package com.example.sciencehack.Model;

public class DoYou {

    private String do_question;
    private String do_answer;

    public DoYou() {

    }

    public DoYou(String do_question, String do_answer) {
        this.do_question = do_question;
        this.do_answer = do_answer;
    }

    public String getDo_question() {
        return do_question;
    }

    public void setDo_question(String do_question) {
        this.do_question = do_question;
    }

    public String getDo_answer() {
        return do_answer;
    }

    public void setDo_answer(String do_answer) {
        this.do_answer = do_answer;
    }
}
