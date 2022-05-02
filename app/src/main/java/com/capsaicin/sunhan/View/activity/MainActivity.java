package com.capsaicin.sunhan.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.capsaicin.sunhan.R;

public class MainActivity extends AppCompatActivity {

    Button sunhan_store;
    Button card_store;
    Button community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sunhan_store = findViewById(R.id.sunhan_store);
        sunhan_store.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        card_store = findViewById(R.id.card_store);
//        sunhan_store.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        community = findViewById(R.id.community);
//        community.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}