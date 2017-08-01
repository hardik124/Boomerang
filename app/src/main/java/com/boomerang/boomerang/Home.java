package com.boomerang.boomerang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.boomerang.boomerang.base.BaseActivity;


public class Home extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static SharedPreferences.Editor editor;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showProgressDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setToolbar();
        getToolbar().setTitle(getString(R.string.app_name));
        showBackButton();
        setSupportActionBar(toolbar);

        showProgressDialog("Loading");
        initLayout();

        toggle = new ActionBarDrawerToggle(Home.this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initLayout() {
        NavigationView navigationView;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.profile:
                //TODO : user details page
                // hideProgressDialog();

                break;
            case R.id.signOut:
                // hideProgressDialog();

                break;
        }
        return false;
    }

}
