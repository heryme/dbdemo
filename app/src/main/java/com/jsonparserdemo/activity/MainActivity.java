package com.jsonparserdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsonparserdemo.R;
import com.jsonparserdemo.database.DemoDatabase;
import com.jsonparserdemo.model_robo_pojo.CategoriesItem;
import com.jsonparserdemo.model_robo_pojo.VenuesItem;
import com.jsonparserdemo.service.APIService;
import com.jsonparserdemo.service.DataInterface;
import com.jsonparserdemo.service.DemoGsonDataService;
import com.jsonparserdemo.service.DemoService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataInterface dataInterface;
    Button btnClick;
    DemoDatabase demoDatabase;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoDatabase = new DemoDatabase(MainActivity.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnClick = (Button) findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiGsonDemo();
                callApi();
                demoDatabase.getAllACHCList();
                demoDatabase.updateRecord();
                demoDatabase.deleteRecord();
                demoDatabase.insertUpdateData();
                demoDatabase.getAllACHCList();
                //Robo Pogo Example Parse Data In Pojo
                callApiGsonDemo();
            }
        });
    }

    private void callApi() {
        DemoService.DemoService(MainActivity.this, new APIService.Success<JSONArray>() {
            @Override
            public void onSuccess(JSONArray response) {
                Log.d("Tag",response.toString());
            }
        });
    }


    private void callApiGsonDemo() {
        DemoGsonDataService.DemoGsonDataService(MainActivity.this, new APIService.Success<JSONObject>() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.d("Tag",response.toString());
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();


                if(response != null) {
                    try {
                        JSONObject res = response.getJSONObject("response");
                        JSONArray venues = res.getJSONArray("venues");
                        List<VenuesItem> venuesItems = new ArrayList<>(Arrays.asList(mGson.fromJson(venues.toString(),VenuesItem[].class)));
                        Log.d("TAG","Size-->" + venuesItems.size());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == 1){
            //((MainActivity)getContext()).loadFragment(new AddProfileFragment(),false,"");
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        menu.add(0,1, 0,
                getResources().getString(R.string.test))
                .setIcon(R.mipmap.ic_launcher)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    /**
     * Note---This is used for fragment time
     * When other fragmenr open then clear menu item
     */
    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }*/


}
