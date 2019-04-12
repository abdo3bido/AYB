package com.shalabyapps.aybt1;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();
        email1 = (EditText)findViewById(R.id.email1);
        pass1 = (EditText)findViewById(R.id.pass1);
        confirm1 = (EditText)findViewById(R.id.confirm1);
        signUp = (Button)findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email1.getText().toString(), pass1.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast toast;
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    toast =Toast.makeText(getApplicationContext(),"signUp successfull",Toast.LENGTH_SHORT);
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {

                                    toast =Toast.makeText(getApplicationContext(),"signUp failed",Toast.LENGTH_SHORT);



                                }

                            }
                        });
            }
        });

    }

}
