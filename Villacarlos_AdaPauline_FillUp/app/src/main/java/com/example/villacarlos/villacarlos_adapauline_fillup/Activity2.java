package com.example.villacarlos.villacarlos_adapauline_fillup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Activity2 extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    EditText etGender,etBirthDate,etPlaceBirth,etCitizen,etReligion;
    Button saveBtn,backBtn;
    View.OnClickListener ocl;
    GestureDetector gestureDetector;
    float x1,x2,y1,y2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        //Action Bar Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Form");

        //Edit Text
        etGender = (EditText)findViewById(R.id.editText7);
        etBirthDate = (EditText)findViewById(R.id.editText8);
        etPlaceBirth = (EditText)findViewById(R.id.editText9);
        etCitizen = (EditText)findViewById(R.id.editText10);
        etReligion = (EditText)findViewById(R.id.editText11);

        //For Previous
        Intent prev = getIntent();
        Bundle bundle = prev.getExtras();
        etGender.setText(bundle.getString("keyGender"));
        etBirthDate.setText(bundle.getString("keyBirthDate"));
        etPlaceBirth.setText(bundle.getString("keyBirthPlace"));
        etCitizen.setText(bundle.getString("keyCitizen"));
        etReligion.setText(bundle.getString("keyReligion"));


        ocl = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button2){
                    next();
                }
                if(v.getId()==R.id.button3){
                    back();
                }
            }
        };

        //Button
        saveBtn = findViewById(R.id.button2);
        backBtn = findViewById(R.id.button3);

        saveBtn.setOnClickListener(ocl);
        backBtn.setOnClickListener(ocl);

        //Gestures
        gestureDetector = new GestureDetector(this, this);

    }



    private void next(){
        //Strings
        String gender = etGender.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String birthPlace = etPlaceBirth.getText().toString();
        String citizen = etCitizen.getText().toString();
        String religion = etReligion.getText().toString();

        if(gender.isEmpty() || birthDate.isEmpty() || birthPlace.isEmpty() || citizen.isEmpty() || religion.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please complete all the fields",Toast.LENGTH_LONG).show();
        } else {
            Intent i = getIntent();
            Bundle b = i.getExtras();

            Intent intent = new Intent(getApplicationContext(),Activity3.class);

            b.putString("keyGender",gender);
            b.putString("keyBirthDate",birthDate);
            b.putString("keyBirthPlace",birthPlace);
            b.putString("keyCitizen",citizen);
            b.putString("keyReligion",religion);

            intent.putExtras(b);
            startActivityForResult(intent,1);
            Toast.makeText(getApplicationContext(),"Information saved",Toast.LENGTH_LONG).show();
        }
    }

    private  void back(){
        //Strings
        String gender = etGender.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String birthPlace = etPlaceBirth.getText().toString();
        String citizen = etCitizen.getText().toString();
        String religion = etReligion.getText().toString();

        Bundle bForm = new Bundle();
        bForm.putString("keyGender",gender);
        bForm.putString("keyBirthDate",birthDate);
        bForm.putString("keyBirthPlace",birthPlace);
        bForm.putString("keyCitizen",citizen);
        bForm.putString("keyReligion",religion);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result",bForm);
        setResult(RESULT_OK,resultIntent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK){
            Bundle result = data.getBundleExtra("result");
        }
    }




    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }


    private void clearText(){
        etGender.getText().clear();
        etBirthDate.getText().clear();
        etPlaceBirth.getText().clear();
        etCitizen.getText().clear();
        etReligion.getText().clear();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.clearBtn:
                clearText();
                Toast.makeText(getApplicationContext(),"Cleared successfully",Toast.LENGTH_LONG).show();
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
                if(x1 < x2){
                    back();
                    //Right
                } else if (x1>x2){
                    next();
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
        etGender.setText("Female");
        etBirthDate.setText("12/31/01");
        etPlaceBirth.setText("Cebu City");
        etCitizen.setText("Filipino");
        etReligion.setText("Catholic");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
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
        return false;
    }
}