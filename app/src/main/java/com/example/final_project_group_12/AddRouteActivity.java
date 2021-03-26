package com.example.final_project_group_12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRouteActivity extends AppCompatActivity {

    private FloatingActionButton saveBtn;
    private FloatingActionButton backBtn;
    private TextView routeName;
    private TextView errorMsg;
    public static int STATUS_OK = 1;
    private RouteHelper rHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        saveBtn = findViewById(R.id.btnSave);
        backBtn = findViewById(R.id.btnBack);
        routeName = findViewById(R.id.editTxtRouteName);
        errorMsg = findViewById(R.id.txtError);
        rHelper = new RouteHelper(this);
        rHelper.getWritableDatabase();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route_name = routeName.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date = sdf.format(new Date());

                if (route_name != null && !route_name.isEmpty()){

                    Cursor cur = DBHelper.getRouteRow(rHelper, route_name);
                    if (cur.moveToNext()){
                        errorMsg.setText("The route name already exists.");
                        errorMsg.setTextColor(Color.RED);
                    }else{
                        Intent intent = new Intent();
                        long id = DBHelper.addRoute(rHelper,route_name,null,0,date);
                        intent.putExtra("ROUTE_NAME", route_name);
                        setResult(STATUS_OK , intent);
                        rHelper.close();
                        finish();
                    }
                }
                else{
                   errorMsg.setText("Please provide a route name.");
                   errorMsg.setTextColor(Color.RED);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED , intent);
                finish();
            }
        });


    }
}
