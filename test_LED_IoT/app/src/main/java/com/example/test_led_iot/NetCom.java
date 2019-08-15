package com.example.test_led_iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetCom {

    private Socket socket = null;
    private String ip;
    private int port;

    public NetCom(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public String sendMsg(String Msg)
   {
       try { // out & in
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //writes str in the socket and read
            out.println(Msg);
            String returnStr = in .readLine();
            return returnStr;
           }
       catch (IOException e){
           e.printStackTrace();
       }

       return null;
   }


}
