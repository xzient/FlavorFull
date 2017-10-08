package com.example.flavorfull.flavorfull;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    Button mCancelButton;
    Button mSubmitButton;
    EditText mEmail;
    EditText mPassword;
    EditText mUsername;

    //Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //TAG
    private static final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Buttons
        mCancelButton = (Button) findViewById(R.id.cancel_button_sign_up);
        mSubmitButton = (Button) findViewById(R.id.submit_button_sign_up);
        //EditText
        mPassword = (EditText) findViewById(R.id.newPassword_sign_up);
        mEmail = (EditText) findViewById(R.id.newEmail_sign_up);
        mUsername = (EditText) findViewById(R.id.newUsername_sign_up);
        //Authentication
        mAuth = FirebaseAuth.getInstance();


        /**
         * CANCEL BUTTON
         */
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will take to Login (activity)
                Intent cancel = new Intent(SignUp.this, Login.class);

                startActivity(cancel);
            }
        });

        /**
         * SUBMIT BUTTON
         */
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * 8/11/2017 For now this method uses the local singleton UserList
             *
             * @param view
             */
            @Override
            public void onClick(View view) {

                //Get values editText
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                //final String username = mUsername.getText().toString().trim(); // This is not implemented now.


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Invalid input. Try Again!",
                                            Toast.LENGTH_SHORT).show();
                                    Toast.makeText(SignUp.this, password,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(SignUp.this, "New user added!", Toast.LENGTH_SHORT).show();



                                    //Sign out user so they enter their data
                                    mAuth.signOut();
                                    Intent goToLogin = new Intent(SignUp.this, Login.class);
                                    startActivity(goToLogin);
                                }
                            }
                        });

            }//OnClick
        });



    }//End OnCreate


}
