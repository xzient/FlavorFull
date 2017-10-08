package com.example.flavorfull.flavorfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSpice extends AppCompatActivity {

    User person;
    Button addSpice;

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spice);

        addSpice = (Button) findViewById(R.id.add_spice_button);
        person = User.getInstance();
        input = (EditText) findViewById(R.id.add_spice);



        addSpice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.addSpice(input.getText().toString().trim());

                Toast.makeText(AddSpice.this, "Spice added!",
                        Toast.LENGTH_SHORT).show();
                Intent return_main = new Intent(AddSpice.this, MainActivity.class);
                startActivity(return_main);


            }
        });


    }
}
