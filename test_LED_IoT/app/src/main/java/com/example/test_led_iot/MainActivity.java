package com.example.test_led_iot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

//import com.github.nkzawa.emitter.Emitter;
//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

//import static com.github.nkzawa.socketio.client.IO.socket;


public class MainActivity extends AppCompatActivity {

    private Switch sw;
    private Socket mSocket;
//    {
//        try {//192.168.1.115:5000
//            mSocket = socket("192.168.1.115:5000");
//        } catch (URISyntaxException e) {
//            System.out.println(e.getMessage());
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = findViewById(R.id.switch1);
        sw.setChecked(false);

        try {//192.168.1.115:5000
            mSocket = IO.socket("https://192.168.1.115:5000");
            mSocket.connect();
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                mSocket.emit("message", "hi");
               // mSocket.disconnect();
            }

        }).on("event", new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });

//        mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener(){
//            @Override
//            public void call(Object... args) {
//                Exception err = (Exception)args[0];
//                Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        } );


        if(mSocket.connected()){Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();}
        else{Toast.makeText(getApplicationContext(), "Not connected", Toast.LENGTH_SHORT).show();}






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
