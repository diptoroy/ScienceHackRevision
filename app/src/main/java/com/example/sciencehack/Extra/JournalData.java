package com.example.sciencehack.Extra;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sciencehack.Interface.JournalSyncResponse;
import com.example.sciencehack.Model.JournalModel;
import com.example.sciencehack.Singleton.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JournalData {

    ArrayList<JournalModel> jList = new ArrayList<>();

    public void getJournal(final JournalSyncResponse callBack){

        String url = "https://newsapi.org/v1/articles?source=new-scientist&sortBy=latest&apiKey=44c8bbad59834d47ba7dd84f759e9086";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray journalArray = response.getJSONArray("articles");
                    for (int i = 0; i < journalArray.length(); i++){

                        JSONObject journalObject = journalArray.getJSONObject(i);
                        JournalModel j = new JournalModel();
                        j.setTitle(journalObject.getString("title"));
                        j.setDescription(journalObject.getString("description"));
                        j.setPublishedAt(journalObject.getString("publishedAt"));
                        j.setUrl(journalObject.getString("url"));
                        j.setUrlToImage(journalObject.getString("urlToImage"));

                        jList.add(j);
                    }

                    if (null != callBack) callBack.processFinish(jList);

                    Log.v("Article : ",jList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);
    }
}
