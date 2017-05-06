package com.example.wiseman.saps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    String name;
    int image;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    homeViewAdapter homeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mViewSocail = getLayoutInflater().inflate(R.layout.facebook_pop_up, null);

                ImageView facebook = (ImageView)mViewSocail.findViewById(R.id.imgfacebook);

                mBuilder.setView(mViewSocail);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/www.iteammobile.co.za/?pnref=lhc"));
                        startActivity(browserIntent);
                    }
                });

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycleviewList);
        gridLayoutManager = new GridLayoutManager(this.getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<homeItems> items = new ArrayList<homeItems>();
        items.add(new homeItems(R.drawable.camera,"Camera"));
        items.add(new homeItems(R.drawable.video,"Video"));
        items.add(new homeItems(R.drawable.phone,"Dial 10111"));
        items.add(new homeItems(R.drawable.voice,"Voice Record"));
        homeViewAdapter = new homeViewAdapter(this.getApplicationContext(),items);
        recyclerView.setAdapter(homeViewAdapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(homeViewAdapter != null){
            homeViewAdapter.onActivityResult(requestCode,resultCode,data);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_policeStation) {
            startActivity(new Intent(MainActivity.this,GooglePlacesActivity.class));
        } else if (id == R.id.nav_news) {
            startActivity(new Intent(MainActivity.this,HomePage.class));

        } else if (id == R.id.nav_admin) {

            startActivity(new Intent(MainActivity.this,Login.class));

        } else if (id == R.id.nav_about) {

            startActivity(new Intent(MainActivity.this,AboutUs.class));

        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"THIS IS MY TEXT TO SEND");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_exit) {

            System.exit(1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
