package com.example.villacarlos.villlacarlos_adapauline_gesturestest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
TextView tv,tv2;
GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);

        gd = new GestureDetector(this, this);
        gd.setOnDoubleTapListener(this);

        registerForContextMenu(tv);
        registerForContextMenu(tv2);

    }

    //Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.textView)
            getMenuInflater().inflate(R.menu.context1,menu);
        if(v.getId() == R.id.textView2)
            getMenuInflater().inflate(R.menu.context2,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.tv1a){
            Toast.makeText(getApplicationContext(),"TV1 A pressed",Toast.LENGTH_LONG).show();
        }

        if(item.getItemId() == R.id.tv1b){
            Toast.makeText(getApplicationContext(),"TV1 B pressed",Toast.LENGTH_LONG).show();
        }

        if(item.getItemId() == R.id.tv2a){
            Toast.makeText(getApplicationContext(),"TV2 A pressed",Toast.LENGTH_LONG).show();
        }

        if(item.getItemId() == R.id.tv2b){
            Toast.makeText(getApplicationContext(),"TV2 B pressed",Toast.LENGTH_LONG).show();
        }


        return true;
        //return super.onContextItemSelected(item);
    }

    //Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.settingsbutton){
            Toast.makeText(getApplicationContext(),"Settings pressed",Toast.LENGTH_LONG).show();
        }

        //Using getTitle() Was overriden because of the toast
        if(item.getItemId()==R.id.searchbutton){
            Toast.makeText(getApplicationContext(),"Search pressed",Toast.LENGTH_LONG).show();
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }

    //Gestures

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        tv.setText("On Single Tap Confirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        tv.setText("On Double Tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        tv.setText("On Double Event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        tv.setText("On Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
//        tv.setText("On Show Press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        tv.setText("On Single Tap Up");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        tv.setText("On Scroll" + distanceX + " " + distanceY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        tv.setText("On Long Press");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        tv.setText("Fling");
        return true;
    }
}