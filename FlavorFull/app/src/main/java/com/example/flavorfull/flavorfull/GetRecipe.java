package com.example.flavorfull.flavorfull;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRecipe extends AppCompatActivity {



    TextView display;
    String recipeID;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recipe);

        display = (TextView) findViewById(R.id.txt_title);
        button = (Button) findViewById(R.id.btn_newPage);

        recipeID = "2c554ee192f549aab6e84b4ed75eeebb";

        TestAsyncTask testAsyncTask = new TestAsyncTask(GetRecipe.this, "https://flavorfull.mybluemix.net/recipes?page=2", recipeID);
        testAsyncTask.execute();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUGGING!!! ", "button pressed ");
                TestAsyncTask testAsyncTask = new TestAsyncTask(GetRecipe.this, "https://flavorfull.mybluemix.net/recipes?page=2", recipeID);
                testAsyncTask.execute();
            }
        });



    }



}
