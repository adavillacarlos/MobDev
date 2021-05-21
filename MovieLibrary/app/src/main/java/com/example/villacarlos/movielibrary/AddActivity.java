package com.example.villacarlos.movielibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etTitle, etDirector, etYear, etSummary;
    Button btnAdd, btnCancel;
    String title, director, summary;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etDirector = findViewById(R.id.etDirector);
        etSummary = findViewById(R.id.etSummary);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = etTitle.getText().toString();
                year = Integer.parseInt(etYear.getText().toString());
                director = etDirector.getText().toString();
                summary = etSummary.getText().toString();
                Movie movie = new Movie(-1,title,year,director,summary);
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                boolean b = db.addMovie(movie);
                if(b){
                    Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTitle.setText("");
                etYear.setText("");
                etDirector.setText("");
                etSummary.setText("");
            }
        });

    }
}