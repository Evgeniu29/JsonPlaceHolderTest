package com.paad.testtask;


import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.paad.testtask.model.User;
import com.paad.testtask.userlist.UserAdapter;
import com.paad.testtask.userlist.UserContract;
import com.paad.testtask.userlist.UserFragment;
import com.paad.testtask.userlist.UserPresenter;

import static com.paad.testtask.userlist.UserFragment.newInstance;


public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {




    TextView email, address, phone, website, company;

    LatLng newlatlng;


    User user = new User();

    UserFragment userFragment;

    UserAdapter adapter;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        email = (TextView)findViewById(R.id._email);
        address  = (TextView) findViewById(R.id._address);
        phone = (TextView) findViewById(R.id._phone);
        website = (TextView) findViewById(R.id._website);
        company = (TextView) findViewById(R.id._company);

        Intent intent = getIntent();

         user= (User) getIntent().getSerializableExtra("user");

        email.setText(user.getEmail());

        String fullAdress = user.getAddress().getFullAddress();

        address.setText(fullAdress);

        phone.setText(user.getPhone());

        website.setText(user.getWebsite());

        company.setText(user.getCompany().getCompanyDescription());

        newlatlng = new LatLng(Double.parseDouble(user.getAddress().getGeo().getLat()), Double.parseDouble(user.getAddress().getGeo().getLng()));

    }



    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        googleMap.addMarker(new MarkerOptions().position(newlatlng)
                .title(user.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(newlatlng));
    }
}
