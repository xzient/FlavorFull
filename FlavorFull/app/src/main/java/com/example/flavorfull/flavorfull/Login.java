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

public class Login extends AppCompatActivity {

    //Define Buttons
    Button mLoginButton;
    Button mSignUpButton;
    EditText mEmail;
    EditText mPassword;


    private static final String TAG = "LoginActivity";
    //Firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Buttons
        mLoginButton = (Button) findViewById(R.id.login_button);
        mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        //EditTexts
        mEmail = (EditText) findViewById(R.id.email_login);
        mPassword = (EditText) findViewById(R.id.password_login);
        //AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();

        /**
         * This is SignUpButton
         */
        Button mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up = new Intent(Login.this, SignUp.class);
                startActivity(sign_up);
            }
        });

        /**
         * This is the LoginButton - This should check if the right username is provided.
         */
        mLoginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final String login = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                Intent logIn = new Intent(Login.this, MainActivity.class);
                startActivity(logIn);

                Toast.makeText(Login.this, "Welcome",
                        Toast.LENGTH_SHORT).show();

                /*
                mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Logged in!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent home = new Intent(Login.this, MainActivity.class);
                                    startActivity(home);
                                }
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                else if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(Login.this, "Login Fail",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }


                });
                */
            }
        });//Login Button

    }
}
