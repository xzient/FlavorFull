package com.example.flavorfull.flavorfull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API_Test extends AppCompatActivity {



    Button testAPI;
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__test);

        testAPI = (Button) findViewById(R.id.buttonAPI);
        display = (TextView) findViewById(R.id.text_main);




        testAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TestAsyncTask testAsyncTask = new TestAsyncTask(API_Test.this, "my url here", display);
                testAsyncTask.execute();
            //getJSON("https://flavorfull.mybluemix.net/recipes")

                //String message1 = testAsyncTask.getJSON("https://flavorfull.mybluemix.net/recipes");
                //display.setText(message1);
                //display.setText("test");


            }



        });


    }

}
