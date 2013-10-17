package edu.neu.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yummin
 * Date: 10/12
 */
public class ServerMain {
    private static final int PORT = 8006;
    private static List<Socket> mClientList = new ArrayList<Socket>();
    private static ExecutorService mExecutorService;
    private static ServerSocket mServerSocket;

    public static void main(String[] args){
        try
        {
            mServerSocket = new ServerSocket(PORT);
            // Create thread pool
            mExecutorService = Executors.newCachedThreadPool();
            System.out.println("Server is working...");
            Socket client = null;// Temporary variable
            while (true)
            {
                // Accept a client and add it to the list
                client = mServerSocket.accept();
                mClientList.add(client);
                // Execute a thread for a client
                mExecutorService.execute(new ClientThread(client));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}