package com.example.villacarlos.villacarlos_adapauline_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondPage extends AppCompatActivity {
EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        et2 = (EditText)findViewById(R.id.editTextSecondPage);
        Intent i = getIntent();
//      String str = i.getStringExtra("key1").toString();
        Bundle bi = i.getExtras();
        String str = bi.getString("key1").toString();

        et2.setText(str);
    }

    public void backbutton(View v){
        Intent tuyo = new Intent();
        String str = et2.getText().toString();
        tuyo.putExtra("reply",str);
        setResult(RESULT_OK,tuyo);
        finish();
    }
}