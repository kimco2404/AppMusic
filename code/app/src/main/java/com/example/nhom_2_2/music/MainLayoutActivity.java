package com.example.nhom_2_2.music;
/**
 * Created by kimcodev on 3/7/2017.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainLayoutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    Button btnSearch;
    //mai mot khong sai cai arrlist file nay nua sai tu database het
    private ArrayList<File> listSongs = null;


    private Database db;

    ArrayList<Music> dataMusics;

    ArrayList<Music> usedMusics;
    ArrayList<Music> usedMusicsNewsong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if (ActivityCompat.checkSelfPermission(MainLayoutActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainLayoutActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
            }

        db = new Database(getApplicationContext());

        dataMusics = new ArrayList<Music>();
        usedMusics = new ArrayList<Music>();
        usedMusicsNewsong = new ArrayList<Music>();
        //listSongs = new ArrayList<File>();
        // get data from new songs or the first run1
        Log.d("test1", System.getenv("SECONDARY_STORAGE") + "");
        try {
            File files = new File(System.getenv("EXTERNAL_STORAGE"));
            listSongs = findSongs(files);
            // Toast.makeText(this,listSongs.size(),Toast.LENGTH_LONG).show();

//                      // read musics from database to use
            db.writeMusics(dataMusics);

            db.readMusics(usedMusics);
            // read db new song
            Log.d("test1", usedMusicsNewsong.toString());
            db.readMusicsNewsong(usedMusicsNewsong);
        }catch (Exception e){
            e.printStackTrace();
        }

        //thanh cong

        //Database db = new Database(MainLayoutActivity.this);
        // db.writeMusic(patches);
        //main fargment

        // Create new fragment and transaction
        ListsongActivity fragment = new ListsongActivity(usedMusics);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        // Commit the transaction
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLayoutActivity.this, SearchActivity.class);
                startActivity(intent);
                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(50);
                btnSearch.startAnimation(animation);
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            ListsongActivity fragment = new ListsongActivity(usedMusics);
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_gallery) {
            AddplaylistActivity fragment = new AddplaylistActivity(usedMusics);
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_slideshow) { // new song fragment
            NewsongActivity fragment = new NewsongActivity(usedMusicsNewsong);
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<File> findSongs(File file) {
        ArrayList<File> songs = new ArrayList<File>();
        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                songs.addAll(findSongs(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    songs.add(singleFile);
                    Log.d("test", singleFile.getPath());

                    Music music = new Music();
                    music.setPatch(singleFile.getPath());
                    music.setMusic_name(singleFile.getName());

                    dataMusics.add(music);
                }
            }
        }
        return songs;
    }
}

