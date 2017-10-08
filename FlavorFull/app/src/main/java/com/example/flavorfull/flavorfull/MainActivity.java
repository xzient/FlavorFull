package com.example.flavorfull.flavorfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button recipe;
    Button spice;
    Button display_spices;

    TextView display;

    //Database
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    User person = User.getInstance();

    String data;
    StringBuffer buffer;
    ArrayList inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = User.getInstance();

        display = (TextView) findViewById(R.id.display_textview);
        display.setMovementMethod(new ScrollingMovementMethod());
        spice = (Button) findViewById(R.id.button2);
        recipe = (Button) findViewById(R.id.button3);
        display_spices = (Button) findViewById(R.id.button4);;


        //Add all spices to inventory
        if(!person.read) {
            data = "";
            buffer = new StringBuffer();
            inventory = new ArrayList();
            InputStream is = this.getResources().openRawResource(R.raw.spices);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            if(is != null) {



                try {


                    while((data=reader.readLine()) != null) {
                        inventory.add(data);
                        person.setInventory(inventory);
                        person.read = true;

                    }
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



        display.setText(person.display());


        spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_spice_intent = new Intent(MainActivity.this, AddSpice.class);
                startActivity(add_spice_intent);
            }
        });

        display_spices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rev_spice_intent = new Intent(MainActivity.this, RemoveSpice.class);
                startActivity(rev_spice_intent);
            }
        });

        //Recipe
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent get_recipe_intent = new Intent(MainActivity.this, GetRecipe.class);
                startActivity(get_recipe_intent);
            }
        });



    }
}
