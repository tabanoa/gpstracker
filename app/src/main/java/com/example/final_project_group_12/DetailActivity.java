package com.example.final_project_group_12;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    FloatingActionButton fabBack;

    private PointHelper pHelper = null;
    private RouteHelper rHelper = null;
    private RouteHelper rHelperEdit = null;
    private FloatingActionButton fabSave;
    private TextView route_details;
    private RatingBar ratingBar;
    private double bLat;
    private double bLong;
    private double lat;
    private double lon;
    private double pLat;
    private double pLong;
    private int routeId;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;

    ImageButton btnShare;

    Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        TextView routeT = findViewById(R.id.txtV_route);
        routeT.setEnabled(false);

        btnShare = findViewById(R.id.btnShare);

        String routeInfo = getIntent().getExtras().getString(RouteHistoryActivity.KEY);
        fabSave = findViewById(R.id.fabSave);
        route_details = findViewById(R.id.txt_routeDetails);
        ratingBar = findViewById(R.id.ratingBar);
        routeT.setText(routeInfo);

        rHelper = new RouteHelper(this);
        rHelper.getReadableDatabase();

        rHelperEdit = new RouteHelper(this);
        rHelperEdit.getWritableDatabase();

        Cursor cur = DBHelper.getRouteRow(rHelper, routeInfo);
        cur.moveToNext();
        routeId = cur.getInt(cur.getColumnIndexOrThrow(RouteContract.RouteEntity._ID));

        route_details.setText(cur.getString(cur.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_DESCRIPTION)));

        ratingBar.setRating(cur.getFloat(cur.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_RATING)));

        rHelper.close();

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CaptureMapScreen();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String route_str = routeT.getText().toString();
                String detail_str = route_details.getText().toString();
                double rating_double = ratingBar.getRating();
                Log.d("RATING ::"," this is :" + rating_double);
                if(route_str.length()>0 && detail_str.length()>0 && rating_double>=0 && rating_double<=5){

                     DBHelper.editRouteRating(rHelperEdit,route_str,detail_str,rating_double);
                     RouteHistoryActivity.routeArrayAdapter.notifyDataSetChanged();
                    Intent i = new Intent(v.getContext(),RouteHistoryActivity.class);
                    rHelperEdit.close();
                    startActivity(i);
                }


            }
        });

        fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        pHelper = new PointHelper(this);
        pHelper.getReadableDatabase();

        Cursor cur = DBHelper.getPointsAtSpecificRouteId(pHelper, routeId);
        cur.moveToNext();

        bLat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
        bLong = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));

        LatLng latLng = new LatLng(bLat, bLong);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Starting Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));

        int i = 0;

        while(cur.moveToNext()){
            if (i == 0){
                lat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
                lon = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));
                Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(bLat, bLong), new LatLng(lat, lon))
                        .width(5)
                        .color(Color.RED));
                pLat = lat;
                pLong = lon;
                i++;
            }else{
                lat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
                lon = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));
                Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(pLat, pLong), new LatLng(lat, lon))
                        .width(5)
                        .color(Color.RED));
                pLat = lat;
                pLong = lon;
            }
        }

        LatLng latLngEnd = new LatLng(lat, lon);
        markerOptions.position(latLngEnd);
        markerOptions.title("Ending Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mGoogleMap.addMarker(markerOptions);

        pHelper.close();

    }

    public void CaptureMapScreen()
    {
        SnapshotReadyCallback callback = new SnapshotReadyCallback() {
            Bitmap bitmap;

            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                // TODO Auto-generated method stub
                bitmap = snapshot;

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        };

        mGoogleMap.snapshot(callback);


    }

}
