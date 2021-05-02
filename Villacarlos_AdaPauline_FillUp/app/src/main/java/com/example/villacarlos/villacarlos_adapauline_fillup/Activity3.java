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

public class Activity3 extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
EditText etFName, etLName, etBirthday, etAge, etEmail, etContact;
Button next2, back2;
View.OnClickListener ocl;
GestureDetector gestureDetector;
float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button4){
                    next();
                }

                if(v.getId() == R.id.button5){
                  back();
                }
            }
        };

        //Action Bar Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Form");

        //Edit Text
        etFName = (EditText)findViewById(R.id.editText12);
        etLName = (EditText)findViewById(R.id.editText13);
        etBirthday = (EditText)findViewById(R.id.editText14);
        etAge = (EditText)findViewById(R.id.editText15);
        etEmail = (EditText)findViewById(R.id.editText16);
        etContact = (EditText)findViewById(R.id.editText17);


        //Button
        next2 = findViewById(R.id.button4);
        back2 = findViewById(R.id.button5);

        next2.setOnClickListener(ocl);
        back2.setOnClickListener(ocl);

        //Intents
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String fname = b.getString("keyFName").toString();
        String lname = b.getString("keyLName").toString();
        String birthday = b.getString("keyBirthDate").toString();
        String age = b.getString("keyAge").toString();
        String email = b.getString("keyEmail").toString();
        String contact = b.getString("keyContact").toString();

        etFName.setText(fname);
        etLName.setText(lname);
        etBirthday.setText(birthday);
        etAge.setText(age);
        etEmail.setText(email);
        etContact.setText(contact);

        //Gestures
        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == RESULT_OK){
            Bundle result = data.getBundleExtra("result");
        }
    }

    private void next(){
        Intent i = getIntent();
        Bundle bForm = i.getExtras();
        Intent intent = new Intent(getApplicationContext(),Activity4.class);
        intent.putExtras(bForm);
        startActivityForResult(intent,0);
    }

    private void back(){
        Intent i = getIntent();
        Bundle bForm = i.getExtras();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result",bForm);
        setResult(RESULT_OK,i);
        finish();
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



    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    //Just go back to main but does not retain the information
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