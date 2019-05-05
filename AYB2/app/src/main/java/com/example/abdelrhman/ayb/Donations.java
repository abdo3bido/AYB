package com.example.abdelrhman.ayb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Donations extends AppCompatActivity  {

    private Toolbar mToolbar;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
        mDatabase = FirebaseDatabase.getInstance().getReference("/cases");
        mToolbar = (Toolbar) findViewById(R.id.toolbar_Donations);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading");
        progressDialog.show();
        initToolbar();

        ValueEventListener listener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot caseSnapshot: dataSnapshot.getChildren()) {

                    cases _case=new cases();
                    HashMap<String, String> rawData = (HashMap) caseSnapshot.getValue();
                    HashMap<String, List<String>> rawData2 = (HashMap) caseSnapshot.getValue();
                    Log.d("rawData", rawData.toString());
                    Log.d("rawData2", rawData2.toString());
                    Log.d("Title",rawData.get("Title").toString());
                    _case.setTitle(rawData.get("Title"));
                    _case.setCaseType(rawData.get("CaseType"));
                    _case.setDescription(rawData.get("Description"));
                    ArrayList<String> urls = new ArrayList<>();
                    int i =0;
                    for(String url: (List<String>)rawData2.get("urls")){
                        urls.add(url);
                        i++;
                    }
                    _case.setURLs(urls);
                    _cases.add(_case);
                }
                RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
                listAdapter adapter = new listAdapter(_cases);
                rv.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(manager);
                rv.setItemAnimator(new DefaultItemAnimator());
                Log.d("Cases",_cases.get(0).getURLs().get(0).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };

        mDatabase.addValueEventListener(listener);


    }
    public void initToolbar(){
        mToolbar.setTitle("Donations");
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backbutton_01));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });


    }
    final List<cases> _cases = new ArrayList<>();






    void getPics(List<cases> _cases){
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        List<cases> finalCases = new ArrayList<>();
        for(cases __case: _cases){
            final ArrayList<String> realURLS = new ArrayList<>();
            for(String name: __case.getURLs()){
                mStorageRef.child(name).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        realURLS.add(uri.toString());
                        Log.d("URI",uri.toString());
                        Log.d("URI2",realURLS.toString());
                    }
                });
            }
            cases finalCase = new cases();
            finalCase.setTitle(__case.getTitle());
            finalCase.setDescription(__case.getDescription());
            finalCase.setCaseType(__case.getCaseType());
            finalCase.setURLs(realURLS);
            finalCases.add(finalCase);
            Log.d("urIs", realURLS.toString());

        }
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        listAdapter adapter = new listAdapter(finalCases);
        rv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        Log.d("Finalcases",finalCases.toString());
        progressDialog.dismiss();
    }



}

