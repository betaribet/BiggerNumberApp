package com.example.hwk01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.startbtn);


    }


    public void onClickStart(View view) {
        Intent intent = new Intent(getApplicationContext(), Level01Activity.class);
        startActivity(intent);
        finish();
    }

    public void onClickQuit(View view) {
        Intent intent = new Intent(getApplicationContext(), result.class);
        startActivity(intent);
        finish();
    }
}

