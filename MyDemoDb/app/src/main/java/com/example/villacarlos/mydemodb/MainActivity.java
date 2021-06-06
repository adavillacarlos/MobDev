package com.example.villacarlos.mydemodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, age,id;
    Button viewAll, add,update;
    Switch swActive;
    ListView customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.et_Name);
        age = findViewById(R.id.et_Age);
        swActive = findViewById(R.id.sw_Active);
        add = findViewById(R.id.btn_Add);
        update = findViewById(R.id.btn_Update);
        viewAll = findViewById(R.id.btn_View);
        customerList = findViewById(R.id.ListView1);
        id = findViewById(R.id.et_Id);


        //implement array list for the list


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                List<CustomerModel> list = dbHelper.getList();
                ArrayAdapter ad = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                customerList.setAdapter(ad);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel cm = new CustomerModel(-1,name.getText().toString(),Integer.parseInt(age.getText().toString()),swActive.isChecked());
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                boolean success = db.addCustomer(cm);
                Toast.makeText(MainActivity.this,"success: " + success, Toast.LENGTH_LONG).show();
            }
        });


        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel cm = (CustomerModel) parent.getItemAtPosition(position);
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                db.deleteCustomer(cm);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel cm = new CustomerModel(Integer.parseInt(id.getText().toString()),name.getText().toString(),
                        Integer.parseInt(age.getText().toString()),swActive.isChecked());
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                boolean isUpdated  = db.updateCustomer(cm);
                if(isUpdated){
                    Toast.makeText(MainActivity.this,"success", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this,"failed", Toast.LENGTH_LONG).show();
            }
        });

    }


}