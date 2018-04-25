package com.jsonparserdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jsonparserdemo.DataModel;
import com.jsonparserdemo.R;
import com.jsonparserdemo.service.DataInterface;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements DataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    @Override
    public void sendData(List<DataModel> dataModelList) {
        Log.d("Tag","Activity 2-->" + dataModelList.get(0).getQuestion());
    }
}
