package com.example.a15017470.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShowList = (Button) findViewById(R.id.btnShow);
        ArrayList<String> al;



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                RadioGroup rg = (RadioGroup) findViewById(R.id.rgStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                String strAmountStars = rb.getText() + "";
                int amountStars = Integer.parseInt(strAmountStars);


                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title, singer, year, amountStars);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ShowActivity.class);
                startActivity(i);
            }
        });
    }
}
