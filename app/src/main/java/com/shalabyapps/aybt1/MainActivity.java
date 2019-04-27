package com.shalabyapps.aybt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View v)
    {
        Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
        startActivity(intent);
    }
    public void signUp(View v)
    {
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
        startActivity(intent);
    }
}
