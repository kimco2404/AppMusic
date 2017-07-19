package com.example.nhom_2_2.music;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Calm on 7/16/2017.
 */

public class MyAdapterMusic  extends ArrayAdapter<Song> {

    Activity context = null;
    int layoutId;
    ArrayList<Song> arr = null;

    public MyAdapterMusic(Activity context, int layoutId, ArrayList<Song> list){
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
        this.arr = list;
    }


    private class ViewHolder{
        TextView songName;
        ImageView menu;
    }
    ViewHolder holder = null;
    @NonNull

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        Song song = arr.get(position);
        //Lấy ra những control được định nghĩa trong cấu trúc của mỗi item
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtitem_song);
        //Gán giá trị cho những control đó
        txtTitle.setText(song.getTitle());

        holder = new ViewHolder();
        holder.menu = (ImageView) convertView.findViewById(R.id.menu);
        holder.songName = (TextView) convertView.findViewById(R.id.txtitem_song);
        convertView.setTag(holder);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.menu:

                        PopupMenu popup = new PopupMenu(context, view);
                        popup.getMenuInflater().inflate(R.menu.select_layout_drawer,
                                popup.getMenu());
                        popup.show();
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case R.id.new_play_list: {
                                        break;
                                    }
                                    case R.id.del:
                                        //notifyDataSetChanged();
                                        break;
                                    default:
                                        break;
                                }
                                return true;
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
        return convertView;
    }


}