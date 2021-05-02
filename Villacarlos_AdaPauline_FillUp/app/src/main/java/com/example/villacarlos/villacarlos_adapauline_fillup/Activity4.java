package com.example.villacarlos.villacarlos_adapauline_fillup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
EditText etAddress, etGender, etBirthPlace, etCitizen, etReligion;
Button exit, back3;
View.OnClickListener ocl;
GestureDetector gestureDetector;
float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button6){
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }

                if(v.getId() == R.id.button7){
                    finish();
                }
            }
        };

        //Action Bar Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Form");

        //Edit Text
        etAddress = (EditText)findViewById(R.id.editText18);
        etGender = (EditText)findViewById(R.id.editText19);
        etBirthPlace = (EditText)findViewById(R.id.editText20);
        etCitizen = (EditText)findViewById(R.id.editText21);
        etReligion = (EditText)findViewById(R.id.editText22);

        //Button
        exit = findViewById(R.id.button6);
        back3 = findViewById(R.id.button7);

        exit.setOnClickListener(ocl);
        back3.setOnClickListener(ocl);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String address = b.getString("keyAddress").toString();
        String gender = b.getString("keyGender").toString();
        String birthPlace = b.getString("keyBirthPlace").toString();
        String citizen = b.getString("keyCitizen").toString();
        String religion = b.getString("keyReligion").toString();

        etAddress.setText(address);
        etGender.setText(gender);
        etBirthPlace.setText(birthPlace);
        etCitizen.setText(citizen);
        etReligion.setText(religion);

        //Gestures
        gestureDetector = new GestureDetector(this, this);
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }


    private void backToMain(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.goBackBtn:
                backToMain();
                break;
            case R.id.aboutBtn:
                Toast.makeText(getApplicationContext(),"Fill Up Form Created by Ada Pauline Villacarlos",Toast.LENGTH_LONG).show();
                break;
            case R.id.exitBtn:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }



    //On Touch Event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                //Left
                if(x1 < x2) {
                    finish();
                }


                break;

        }
        return super.onTouchEvent(event);
    }


    //Gestures
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(velocityY>velocityX){
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }

            return true;
    }
}