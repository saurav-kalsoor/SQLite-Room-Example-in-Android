package com.example.internalfilehandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText mText1,mText2;
    private Button mInsertBtn,mSaveBtn;
    private ArrayList<ExampleItem> mExamplelist;
    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInsertBtn = (Button)findViewById(R.id.insertButton);
        mSaveBtn = (Button)findViewById(R.id.saveButton);
        mText1 = (EditText)findViewById(R.id.editText1);
        mText2 = (EditText)findViewById(R.id.editText2);


        loadData();
        buildRecyclerView();

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInsertButton();
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButton();
            }
        });


    }

    private void setSaveButton() {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared Preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExamplelist);
        editor.putString("Task List",json);
        editor.apply();
        Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Shared Preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Task List",null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExamplelist = gson.fromJson(json,type);

        if(mExamplelist == null){
            Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();
            mExamplelist = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ExampleAdapter(this,mExamplelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);


    }

    private void setInsertButton() {
        String text1,text2;
        text1 = mText1.getText().toString();
        text2 = mText2.getText().toString();

        if(TextUtils.isEmpty(text1) || TextUtils.isEmpty(text2)){
            Toast.makeText(MainActivity.this,"Fields Are Empty",Toast.LENGTH_SHORT).show();
        } else {
            mExamplelist.add(new ExampleItem(text1,text2));
            Toast.makeText(MainActivity.this,"Item Added",Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
        }
    }
}
