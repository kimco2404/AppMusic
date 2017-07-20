package com.example.nhom_2_2.music;
/**
 * Created by kimcodev on 3/7/2017.
 */
import com.example.nhom_2_2.music.MyAdapter;
import com.example.nhom_2_2.music.MusicService;
import com.example.nhom_2_2.music.Music;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListsongActivity extends Fragment{

	private ArrayList<Music> listSongs;
	public static MyAdapter adapter;
	private ArrayList<String> arr;
	public ListsongActivity(){

	}
	public ListsongActivity(ArrayList<Music> musics) {
		listSongs = musics;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.listmusic_fragment_layout, container, false);
		arr = new ArrayList<String>();

		for (int i = 0; i < listSongs.size(); i++)
			arr.add(listSongs.get(i).getMusic_name().trim());

		adapter = new MyAdapter(this.getActivity(), R.layout.item_list_music, arr,listSongs);
		ListView lv = (ListView) view.findViewById(R.id.lvSong);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
				Bundle b = new Bundle();
				b.putInt("position",i);
				intent.putExtra("position", b);
				startActivity(intent);

			}
		});

		return view;
	}
	public static void updateListView(){
		adapter.notifyDataSetChanged();

	}
}

