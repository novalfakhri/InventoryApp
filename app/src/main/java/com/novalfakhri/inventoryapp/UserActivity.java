package com.novalfakhri.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.novalfakhri.inventoryapp.database.DaoSession;

public class UserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String user;
    private Toolbar toolbar;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbar = findViewById(R.id.toolbar);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        final String[] users= {"Admin 1", "Admin 2", "Admin 3", "Admin 4", "Admin 5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        spinner.setAdapter(adapter);

        Button next = findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                intent.putExtra("admin", user);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                user = "Admin 1";
                break;
            case 1:
                user = "Admin 2";
                break;
            case 2:
                user = "Admin 3";
                break;
            case 3:
                user = "Admin 4";
                break;
            case 4:
                user = "Admin 5";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
