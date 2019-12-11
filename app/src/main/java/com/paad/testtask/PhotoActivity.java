package com.paad.testtask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.paad.testtask.photolist.PhotoFragment;

public class PhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        long albumId = getIntent().getLongExtra("albumId",
                0);

        PhotoFragment photoFragment = new PhotoFragment(albumId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container, photoFragment).commit();

    }

}
