package com.example.flavorfull.flavorfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
        //input = (EditText) findViewById(R.id.add_spice);




        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);




        /*
        spinner = (Spinner) findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.Spices,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }

                                          });
*/




        addSpice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean addSpice = person.addSpice(spinner.getSelectedItem().toString());
                if (addSpice) {
                    Toast.makeText(AddSpice.this, "Spice added!",
                            Toast.LENGTH_SHORT).show();
                    Intent return_main = new Intent(AddSpice.this, MainActivity.class);
                    startActivity(return_main);
                }

                else {
                    Toast.makeText(AddSpice.this, "That spice is not in the inventory!",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
