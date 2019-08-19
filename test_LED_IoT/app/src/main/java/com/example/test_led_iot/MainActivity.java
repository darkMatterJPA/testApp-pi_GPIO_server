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

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

public class MainActivity extends AppCompatActivity {

    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = findViewById(R.id.switch1);
        sw.setTextOff("OFF");
        sw.setTextOn("ON");
        sw.setChecked(false);

       // final NetCom com = new NetCom("192.168.1.118",6969);
        SocketIO socket = null;
        try {
            socket = new SocketIO("http://192.168.1.115:5000/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        socket.connect(new IOCallback() {
            @Override
            public void onMessage(JSONObject json, IOAcknowledge ack) {
//                try {
//                    System.out.println("Server said:" + json.toString(2));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onMessage(String data, IOAcknowledge ack) {
                Log.i("INFO","Server said: " + data);
                Toast.makeText(getApplicationContext(), "Server said: " + data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SocketIOException socketIOException) {
               Log.e("ERROR","an Error occurred: "+socketIOException.getMessage());
               Toast.makeText(getApplicationContext(), "an Error occurred: "+socketIOException.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDisconnect() {
                Log.i("INFO","Connection terminated.");
            }

            @Override
            public void onConnect() {
                Log.i("INFO","Connection established");
                Toast.makeText(getApplicationContext(), "Connection established", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void on(String event, IOAcknowledge ack, Object... args) {
                Log.i("INFO","Server triggered event '" + event + "'");
            }
        });

        final SocketIO finalSocket = socket;
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 String toast;
                 if (isChecked) {
                     // The toggle is enabled
                     finalSocket.send("On");
                      toast = "On"; //com.sendMsg("On");
                 } else {
                     // The toggle is disabled
                     finalSocket.send("Off");
                        toast = "Off"; //com.sendMsg("Off");
                 }
                 Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
             }
         });
    }
}
