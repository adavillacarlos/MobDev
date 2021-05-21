package com.example.villacarlos.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle, etDirector, etYear, etSummary;
    Button btnUpdate, btnCancel;
    String id, title, year, director, summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etTitle = findViewById(R.id.etTitle2);
        etYear = findViewById(R.id.etYear2);
        etDirector = findViewById(R.id.etDirector2);
        etSummary = findViewById(R.id.etSummary2);
        btnUpdate = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel2);

        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                title = etTitle.getText().toString();
                year = etYear.getText().toString();
                director = etDirector.getText().toString();
                summary = etSummary.getText().toString();
                boolean b = db.updateData(id,title,year,director,summary);
                if(b){
                    Toast.makeText(getApplicationContext(),"Saved successfully",Toast.LENGTH_SHORT).show();
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

    void getAndSetIntentData(){
        Intent i = getIntent();
        if(i.hasExtra("id") && i.hasExtra("title") && i.hasExtra("year") && i.hasExtra("director")
        && i.hasExtra("summary")){
            //Getting data from the intent
            id = i.getStringExtra("id");
            title = i.getStringExtra("title");
            year = i.getStringExtra("year");
            director = i.getStringExtra("director");
            summary = i.getStringExtra("summary");

            //Setting intent data
            etTitle.setText(title);
            etYear.setText(year);
            etDirector.setText(director);
            etSummary.setText(summary);
        } else {
            Toast.makeText(getApplicationContext(),"No movie found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btnDelete){
            confirmDialog();
        }

        if(item.getItemId()==R.id.btnExit){
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                DatabaseHelper  db = new DatabaseHelper(UpdateActivity.this);
                boolean b = db.deleteRow(id);
                if(b){
                    Toast.makeText(getApplicationContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Failed to delete",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }

}