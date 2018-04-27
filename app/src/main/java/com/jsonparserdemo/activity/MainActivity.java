package com.jsonparserdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jsonparserdemo.DataModel;
import com.jsonparserdemo.R;
import com.jsonparserdemo.database.DemoDatabase;
import com.jsonparserdemo.service.APIService;
import com.jsonparserdemo.service.DataInterface;
import com.jsonparserdemo.service.DemoService;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DataInterface dataInterface;
    Button btnClick;
    DemoDatabase demoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoDatabase = new DemoDatabase(MainActivity.this);


        btnClick = (Button) findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApi();
                demoDatabase.getAllACHCList();
                demoDatabase.updateRecord();
                demoDatabase.deleteRecord();
                //demoDatabase.insertUpdateData();
                //demoDatabase.getAllACHCList();
            }
        });
    }

    private void callApi() {
        DemoService.DemoService(MainActivity.this, new APIService.Success<JSONArray>() {
            @Override
            public void onSuccess(JSONArray response) {
                //dataInterface.sendData(DataModel.dataParse(response));
                DataModel  dataModel = DataModel.dataParse(response,demoDatabase);
                //DataModel.dataParse(response,demoDatabase);
                demoDatabase.addBulckData(dataModel.getChoicesList());
                //Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                //startActivity(intent);
                Log.d("Tag",response.toString());
            }
        });
    }
}
