package com.jsonparserdemo;

import android.util.Log;

import com.jsonparserdemo.database.DemoDatabase;
import com.jsonparserdemo.service.DataInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rid's Patel on 19-04-2018.
 */

public class DataModel {

    String question;
    String publishedAt;
    String url;
    List<Choices> choicesList;

    public List<Choices> getChoicesList() {
        return choicesList;
    }

    public void setChoicesList(List<Choices> choicesList) {
        this.choicesList = choicesList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class Choices {
        String choice;
        String votes;
        String url;

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public static DataModel  dataParse(JSONArray jsonArray, DemoDatabase database) {
        if(jsonArray != null && jsonArray.length() > 0) {
            List<DataModel> dataModelList = new ArrayList<>();
            List<DataModel.Choices> choicesList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i ++ ) {
                try {
                    DataModel dataModel = new DataModel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray choices = jsonObject.getJSONArray("choices");
                    for (int j = 0 ; j < choices.length(); j++) {
                        DataModel.Choices choices1 = dataModel.new Choices();
                        JSONObject object = choices.getJSONObject(j);
                        choices1.setUrl(object.getString("url"));
                        choicesList.add(choices1);
                        Log.d("TAG","choice-->" + object.getString("choice"));
                    }
                    dataModel.setChoicesList(choicesList);
                    String question = jsonObject.getString("question");
                    dataModel.setQuestion(question);
                    dataModelList.add(dataModel);
                    Log.d("TAG","question-->" + question);

                    Log.d("TAG","Size of the data model list -->" +  dataModelList.get(0).getQuestion());
                    Log.d("TAG","Size of the choices List  -->" +  dataModel.getChoicesList().get(0).getChoice());

                    return dataModel;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

}
