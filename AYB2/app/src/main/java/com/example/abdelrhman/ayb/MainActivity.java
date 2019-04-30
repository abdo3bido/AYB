package com.example.abdelrhman.ayb;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar mToolbar;
    private  DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initNavigation();
    }


 private void initToolbar(){

     mToolbar = (Toolbar) findViewById(R.id.toolbar);

 }


    private void initNavigation(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView  navigationView = (NavigationView) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
       switch (item.getItemId()){
           case R.id.Donations:
               Intent i = new Intent(MainActivity.this,Donations.class);
               startActivity(i);
               break;
           case R.id.Events:
               Intent e = new Intent(MainActivity.this,Events.class);
               startActivity(e);
               break;

       }
        return false;
    }
    private void closeDrawer(){
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}



