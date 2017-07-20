package com.example.nhom_2_2.music;
/**
 * Created by kimcodev on 3/7/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivityOnline extends AppCompatActivity {
    TextView txtSong;
    ArrayList<Music> arrayList;
    AdapterListOnline adapterListOnline;
    ArrayList array;
    ListView lvMylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmusic_online_fragment_layout);
        AnhXa();
        AddSong();

        AdapterListOnline mayArr = new AdapterListOnline(MainActivityOnline.this, R.layout.item_list_music_online, array);
        lvMylist.setAdapter(mayArr);
        lvMylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivityOnline.this, MainActivity.class);
                Bundle b = new Bundle(new Bundle(position));
                b.putInt("position",position);
                intent.putExtra("position", b);
                startActivity(intent);
            }
        });
    }

    private void  AnhXa()
    {
        txtSong = (TextView) findViewById(R.id.txtitem_song);

    }
    private void AddSong()
    {

        ArrayList arrayList = new ArrayList<>();
        arrayList.add("1 2 3 4");
        arrayList.add("Có Được Không Em");
        arrayList.add("Nơi Này Có Anh");
    }


}
