package com.example.listcity;

import static com.example.listcity.R.*;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView cityList;

    ArrayAdapter<String> cityAdapter;

    ArrayList<String> dataList;

    Button btnConfirm;

    Button btnDelete;

    EditText editText;

    int deleteCity; //stores the index of the item selected in the list

    public void addingCity(View view){
        editText.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        cityList =findViewById(R.id.city_list);
        btnConfirm=(Button)findViewById(R.id.confirm_button);
        btnDelete=(Button)findViewById(R.id.delete_button);
        editText=(EditText)findViewById(R.id.city_name_add);

        String []cities = {"Edmonton","Calgary"};
        editText.setVisibility(View.INVISIBLE);
        btnConfirm.setVisibility(View.INVISIBLE);
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String names=editText.getText().toString(); //name is the variable that stores the city_name entered by the user
                dataList.add(names);
                cityAdapter.notifyDataSetChanged();
                editText.setVisibility(View.INVISIBLE);
                btnConfirm.setVisibility(View.INVISIBLE);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteCity = position;

            }
        });



        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataList.remove(deleteCity);
                cityAdapter.notifyDataSetChanged();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }




}