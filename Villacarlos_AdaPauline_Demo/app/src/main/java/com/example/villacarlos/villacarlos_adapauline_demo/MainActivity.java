package com.example.villacarlos.villacarlos_adapauline_demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.editText1_main);
    }

    public void implicittest(View v){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_CALL_BUTTON);
        startActivity(i);
    }

    public void explicit_demo(View v){
        Intent i = new Intent(getApplicationContext(), SecondPage.class);
        String str = et.getText().toString();
//        i.putExtra("key1",str);
        //for the bundle
        Bundle b = new Bundle();
        b.putString("key1",str);
        b.putInt("key2",10);
        i.putExtras(b);

//        startActivity(i);
        startActivityForResult(i,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            String str = data.getStringExtra("reply");
            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        }
    }
}