package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class SocketClientExample3 {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9876);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        Thread oisThread = new Thread(new OISClientThread(socket));
        oisThread.start();
        Random random = new Random();
        int countI = 1;
        while (true){
            Thread.sleep(random.nextInt(5000)+1000);
            oos.writeObject("Numer wiadomości: " + countI++);
            if(countI == 400){
                oos.close();
                oisThread.join();
                socket.close();
            }
        }
    }
}