package com.example.adam.lab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        Button chat = (Button)findViewById(R.id.button3);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, MessageListActivity.class);
                startActivity(intent);
            }
        });

        Button weather = (Button)findViewById(R.id.button5);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, WeatherForecast.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 5 && responseCode == Activity.RESULT_OK){
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(getApplicationContext(), messagePassed, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume");
    }

    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart");
    }

    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause");
    }

    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy");
    }
}
