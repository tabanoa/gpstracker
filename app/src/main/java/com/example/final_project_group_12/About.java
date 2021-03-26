package com.example.final_project_group_12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class About extends AppCompatActivity {

    protected FloatingActionButton fabBack;
    private Button btnDeleteAll;
    private RouteHelper rHelperAll= null;
    private PointHelper pHelperAll = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        fabBack = findViewById(R.id.fabBack);
        btnDeleteAll = findViewById(R.id.btnDelAll);

        rHelperAll = new RouteHelper(this);
        pHelperAll = new PointHelper(this);

        rHelperAll.getWritableDatabase();
        pHelperAll.getWritableDatabase();

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper.DeleteAll(rHelperAll,pHelperAll);
                finish();

            }
        });

    }
}
