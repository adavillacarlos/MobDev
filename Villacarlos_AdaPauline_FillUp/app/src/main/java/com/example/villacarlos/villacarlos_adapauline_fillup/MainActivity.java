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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    EditText etFName,etLName, etAge, etEmail, etContact,etAddress;
    TextView welcome;
    View.OnClickListener ocl;
    Button next;
    Bundle bundleFromNext = new Bundle();
    Bundle b;
    GestureDetector gestureDetector;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action Bar Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Form");

        //Edit Text
        etFName = (EditText)findViewById(R.id.editText1);
        etLName = (EditText)findViewById(R.id.editText2);
        etAge = (EditText)findViewById(R.id.editText3);
        etEmail = (EditText)findViewById(R.id.editText4);
        etContact = (EditText)findViewById(R.id.editText5);
        etAddress = (EditText)findViewById(R.id.editText6);


        ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedInformation();
            }
        };

        //Button
        next = findViewById(R.id.button1);
        next.setOnClickListener(ocl);


        //Text View
        welcome = (TextView)findViewById(R.id.textView1);
        welcome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etFName.setText("Ada Pauline");
                etLName.setText("Villacarlos");
                etAge.setText("20");
                etEmail.setText("adapauline.villacarlos@cit.edu");
                etContact.setText("09276508612");
                etAddress.setText("Cebu City");
                return true;
            }
        });

        //Gestures
        gestureDetector = new GestureDetector(this, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
                bundleFromNext = data.getBundleExtra("result");
        }
    }


    //Next Button
    private void savedInformation(){
        Intent i = new Intent(getApplicationContext(), Activity2.class);
        String fName = etFName.getText().toString();
        String lName = etLName.getText().toString();
        String age = etAge.getText().toString();
        String email = etEmail.getText().toString();
        String contact = etContact.getText().toString();
        String address = etAddress.getText().toString();

        if(fName.isEmpty() || lName.isEmpty() || age.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please complete all the fields",Toast.LENGTH_LONG).show();
        } else {
            //Bundle
            b = new Bundle();
            b.putString("keyFName",fName);
            b.putString("keyLName",lName);
            b.putString("keyAge",age);
            b.putString("keyEmail",email);
            b.putString("keyContact",contact);
            b.putString("keyAddress",address);
            b.putAll(bundleFromNext);
            i.putExtras(b);

            startActivityForResult(i,0);
        }
    }

    private void clearText(){
        etFName.getText().clear();
        etLName.getText().clear();
        etAge.getText().clear();
        etEmail.getText().clear();
        etContact.getText().clear();
        etAddress.getText().clear();
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
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
        float valueX, valueY;
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
                    Toast.makeText(getApplicationContext(),"Swiped Left",Toast.LENGTH_LONG).show();

                //Right
                } else if (x1>x2){
                    savedInformation();
                }
                break;

        }
        return super.onTouchEvent(event);
    }

    //Gestures
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

    //Double Tap
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
}