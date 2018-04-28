package com.jsonparserdemo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.imagestoredatabase.SQLiteDemoActivity;
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
                Intent intent = new Intent(MainActivity.this,SQLiteDemoActivity.class);
                startActivity(intent);
                Log.d("Tag",response.toString());
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
