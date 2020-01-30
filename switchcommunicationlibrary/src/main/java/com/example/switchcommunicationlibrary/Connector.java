package com.example.switchcommunicationlibrary;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Connector {
    public String sendMessageToSwitch(String ISOMessage, String ip, String port){
        try {
            Socket socket = new Socket(ip, Integer.parseInt(port));
            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            socket.setSoTimeout(25000);
            printWriter.write(ISOMessage);
            printWriter.flush();
            int receiveMsgSize;//Size of received message
            byte[] receiveBuf= new byte[8000];
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            InputStream in = socket.getInputStream();
            receiveMsgSize=in.read(receiveBuf);
            return new String(receiveBuf,0,receiveMsgSize);
        }
        catch (SocketTimeoutException ste){
            return "Timeout";
        }
        catch (UnknownHostException uhe){
            return "Host Error";
        }

        catch (Exception e){
            return "Host Error";
        }

    }
}