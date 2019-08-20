package com.example.test_led_iot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {

    private Switch sw;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.115:5000");
        } catch (URISyntaxException e) {}


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mSocket.on
        mSocket.connect();
        if(mSocket.connected()){Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();}
        else{Toast.makeText(getApplicationContext(), "Not connected", Toast.LENGTH_SHORT).show();}

        sw = findViewById(R.id.switch1);
        sw.setChecked(false);




        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 String toast;
                 if (isChecked) {
                     // toggle to on
                      toast = "On";
                     mSocket.emit("message", "On");
                 } else {
                     // toggle to off
                        toast = "Off";
                     mSocket.emit("message", "Off");
                 }
                 Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
             }
         });
    }
}
