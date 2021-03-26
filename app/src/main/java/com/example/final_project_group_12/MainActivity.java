package com.example.final_project_group_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    protected Button btnNav;
    protected Button btnHis;
    protected Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNav = findViewById(R.id.btnNavigation);
        btnHis = findViewById(R.id.btnHistory);
        btnAbout = findViewById(R.id.btnAbout);

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        btnHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        /*
        btnNav.setOnClickListener(this);
        btnHis.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
         */
    }

    /*
    @Override
    public void onClick(View v) {
        openActivity();
    }
    */

    // ===Activities go here===

    public void openActivity1(){
        Intent i = new Intent(this, Nav1Activity.class);
        startActivity(i);
    }

    public void openActivity2(){
        Intent i = new Intent(this, RouteHistoryActivity.class);
        startActivity(i);
    }

    public void openActivity3(){
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

}
