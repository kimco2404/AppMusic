package com.example.nhom_2_2.music;

/**
 * Created by Calm on 7/16/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyListMusic extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Song> arrayListSong ;
    ListView lvMylist;
    MyAdapterMusic adapter;
    int position = 0;
    Toolbar toolbar = null;
    NavigationView navigation = null;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist_fragment_layout);
        AddControl();
        AddSong();

        //setSupportActionBar(toolbar);
        // navigation code  ver1


       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        registerForContextMenu(lvSong);*/
        //
        MyAdapterMusic mayArr = new MyAdapterMusic(MyListMusic.this, R.layout.item_list_music, arrayListSong);
        lvMylist.setAdapter(mayArr);

        lvMylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyListMusic.this, MainActivity.class);
                Bundle b = new Bundle(new Bundle(position));
                b.putInt("position",position);
                intent.putExtra("position", b);
                startActivity(intent);
            }
        });
    }
    private void AddControl()
    {
        lvMylist = (ListView) findViewById(R.id.lvMylist);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
    private void AddSong()
    {
        arrayListSong = new ArrayList<>();
        arrayListSong.add(new Song("Anh Dang Noi Dau", R.raw.anh_dang_noi_dau));
        arrayListSong.add(new Song("Anh Không Sao Đâu", R.raw.anh_khong_sao_dau));
        arrayListSong.add(new Song("Yêu Khac Việt", R.raw.yeu_khacviet));
        arrayListSong.add(new Song("Sau Tất Cả", R.raw.sau_tat_ca));
        arrayListSong.add(new Song("Anh Cơ :))", R.raw.anh_khong_sao_dau));
        arrayListSong.add(new Song("Yêu Khac Việt", R.raw.yeu_khacviet));
        arrayListSong.add(new Song("Anh Dang Noi Dau", R.raw.anh_dang_noi_dau));
        arrayListSong.add(new Song("Anh Không Sao Đâu", R.raw.anh_khong_sao_dau));
        arrayListSong.add(new Song("Yêu Khac Việt", R.raw.yeu_khacviet));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) { // new song fragment

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.select_layout_drawer, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }
}
