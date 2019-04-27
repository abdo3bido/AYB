package com.shalabyapps.aybt1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;



public class Main3Activity extends Activity {
    private FirebaseAuth mAuth;
    EditText email1;
    EditText pass1;
    EditText confirm1;
    Button signUp;
    TextView loginInstead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();
        email1 = (EditText)findViewById(R.id.email1);
        pass1 = (EditText)findViewById(R.id.pass1);
        confirm1 = (EditText)findViewById(R.id.confirm1);
        signUp = (Button)findViewById(R.id.signUp);
        loginInstead = (TextView) findViewById(R.id.textView);
        final String passText = pass1.getText().toString();
        final String confirmText = confirm1.getText().toString();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passText.equals(confirmText)){
                    createUser(email1.getText().toString(),pass1.getText().toString());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Passwords Don't Match, Check Again",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        loginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);

            }
        });
    }

    public void createUser(String email, String pass)
    {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast toast;
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            toast =Toast.makeText(getApplicationContext(),"signUp successfull",Toast.LENGTH_SHORT);
                            toast.show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("User", user.getEmail());

                        } else {

                            toast =Toast.makeText(getApplicationContext(),"signUp failed "+task.getException().getMessage(),Toast.LENGTH_SHORT);
                            toast.show();


                        }

                    }
                });
    }
    public void signIn(String email, String pass)
    {
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
