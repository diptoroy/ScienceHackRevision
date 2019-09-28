package com.example.sciencehack.Interface;

import com.example.sciencehack.Model.JournalModel;

import java.util.ArrayList;

public interface JournalSyncResponse {

    void processFinish(ArrayList<JournalModel> jList);
}
