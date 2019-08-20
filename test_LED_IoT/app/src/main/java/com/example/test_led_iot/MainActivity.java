package com.example.test_led_iot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;



public class MainActivity extends AppCompatActivity {

    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = findViewById(R.id.switch1);
        sw.setChecked(false);

       // final NetCom com = new NetCom("192.168.1.118",6969);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 String toast;
                 if (isChecked) {
                     // toggle is on

                      toast = "On";
                 } else {
                     // toggle is off

                        toast = "Off";
                 }
                 Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
             }
         });
    }
}
