package com.example.phong.music;
/**
 * Created by phong on 12/5/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsongActivity extends Fragment {
	ArrayList<Music> listSongs;
	public static MyAdapterNewsong adapter;
	private ArrayList<String> arr;
	public NewsongActivity(){

	}
	public NewsongActivity(ArrayList<Music> musics) {
		//Log.d("test","minh thich thi minh log choi thoi");
		listSongs = musics;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.newsong_frag3_layout, container, false);
		arr = new ArrayList<String>();

		for (int k = 0; k < listSongs.size(); k++)
			arr.add(listSongs.get(k).getMusic_name().trim());

		adapter = new MyAdapterNewsong(this.getActivity(), R.layout.item_list_music, arr,listSongs);
		ListView lv = (ListView) view.findViewById(R.id.lvNewSong);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
				String name = listSongs.get(k).getMusic_name();
				Intent intent = new Intent(getActivity().getApplication().getApplicationContext(),MainActivity.class);
				Bundle b = new Bundle();
				b.putString("name",name);
				intent.putExtra("search",b);
				startActivity(intent);
			}
		});
		return view;
	}
	public static void updateListView(){
		adapter.notifyDataSetChanged();

	}
}
