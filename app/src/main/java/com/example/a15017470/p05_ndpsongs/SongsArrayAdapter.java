package com.example.a15017470.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 15017470 on 23/5/2017.
 */

public class SongsArrayAdapter extends ArrayAdapter<Song> {
    Context context;
    int resource;
    ArrayList<Song> songList;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView tvSingers, tvYear, tvTitle;

    public SongsArrayAdapter(Context context, int resource, ArrayList<Song> songList) {
        super(context, resource, songList);
        this.context = context;
        this.songList = songList;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        tvSingers=(TextView)rowView.findViewById(R.id.textView5);
        tvYear=(TextView)rowView.findViewById(R.id.textView7);
        iv1=(ImageView)rowView.findViewById(R.id.imageView1star);
        iv2=(ImageView)rowView.findViewById(R.id.imageView2star);
        iv3=(ImageView)rowView.findViewById(R.id.imageView3star);
        iv4=(ImageView)rowView.findViewById(R.id.imageView4star);
        iv5=(ImageView)rowView.findViewById(R.id.imageView5star);
        Song song = (Song) this.songList.get(position);
        tvTitle=(TextView)rowView.findViewById(R.id.textView6);
        tvTitle.setText(song.getSingers());
        tvSingers.setText(song.getSingers());
        tvYear.setText(song.getYearReleased());
        if (song.getStars() >= 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (song.getStars() >= 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (song.getStars() >= 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (song.getStars() >= 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (song.getStars() >= 1) {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        return rowView;
    }
}
