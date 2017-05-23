package com.example.a15017470.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

        EditText etSong, etTitle, etYear;
        Button btnUpdate, btnDelete, btnCancel;
        TextView tvID;
        Song data;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit2);

            etSong = (EditText) findViewById(R.id.etTitle);
            etTitle = (EditText) findViewById(R.id.etSingers);
            etYear = (EditText) findViewById(R.id.etYear);
            btnUpdate = (Button) findViewById(R.id.btnUpdate);
            btnDelete = (Button) findViewById(R.id.btnDelete);
            btnCancel = (Button) findViewById(R.id.btnCancel);
            tvID = (TextView) findViewById(R.id.tvID);

            Intent i = getIntent();
            data = (Song) i.getSerializableExtra("data");


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // Get the RadioGroup object
                    RadioGroup rg = (RadioGroup) findViewById(R.id.rgStars);
                    // Get the Id of the selected radio button in the RadioGroup
                    int selectedButtonId = rg.getCheckedRadioButtonId();
                    // Get the radio button object from the Id we had gotten above
                    RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                    String stars = rb.getText().toString();
                    int star = Integer.parseInt(stars);

                    String years = etYear.getText().toString();
                    int year = Integer.parseInt(years);

                    data.setStars(star);
                    data.setYearReleased(year);

                    DBHelper dbh = new DBHelper(EditActivity.this);
                    data.setSingers(etSong.getText().toString());
                    data.setTitle(etTitle.getText().toString());
                    dbh.updateNote(data);
                    dbh.close();

                    Intent i = new Intent();
                    setResult(RESULT_OK, i);
                    finish();


                    Toast.makeText(EditActivity.this, "UPDATE SUCCESSFUL",
                            Toast.LENGTH_SHORT).show();
                }
            });


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbh = new DBHelper(EditActivity.this);
                    dbh.deleteNote(data.getId());
                    dbh.close();

                    Toast.makeText(EditActivity.this, "DELETE SUCCESSFUL",
                            Toast.LENGTH_SHORT).show();
                }
            });


            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        }
    }
