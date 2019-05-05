package com.example.abdelrhman.ayb;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class Toolbar_navigation extends Fragment {


    public interface Fragment_communication{
        public String getTitle();

    }
    public Fragment_communication mFragment_communitcator;
    private Toolbar mToolbar;
    private  DrawerLayout mDrawerLayout;
    public Toolbar_navigation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toolbar_navigation, container, false);
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout2);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar3);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.main_drawer2);
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mFragment_communitcator = (Fragment_communication) activity ;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initNavigation();
    }


    public void initToolbar(){
        mToolbar.setTitle(mFragment_communitcator.getTitle());

    }
    private void initNavigation(){
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

    }
}
