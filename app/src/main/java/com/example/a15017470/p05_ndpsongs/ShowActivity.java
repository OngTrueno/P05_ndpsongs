package com.example.a15017470.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.data;

public class ShowActivity extends AppCompatActivity {

    SongsArrayAdapter aa;
    Button btn5stars;
    ListView lv;
    ArrayList<Song> notes;
    int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        setTitle(getTitle().toString());

        lv = (ListView) findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        this.notes = dbh.getAllNotes();
        dbh.close();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("song", (Serializable) ShowActivity.this.notes.get(position));
                ShowActivity.this.startActivityForResult(i, ShowActivity.this.requestCode);
            }
        });

        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                ShowActivity.this.notes.clear();
                ShowActivity.this.notes.addAll(dbh.getAllNotesbyStars(5));
                ShowActivity.this.aa.notifyDataSetChanged();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode && resultCode == -1) {
            DBHelper dbh = new DBHelper(this);
            this.notes.clear();
            this.notes.addAll(dbh.getAllNotes());
            dbh.close();
            this.aa.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    };
}





