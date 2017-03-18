package com.company;

import java.io.IOException;
import java.net.Socket;
 
public class NetCpy {
    private DirectoryTxr transmitter = null;
    Socket clientSocket = null;
    private boolean connectedStatus = false;
    private String ipAddress;
    private int port;
    String srcPath = null;
    String dstPath = "";

 
    public void setIpAddress(String ip) {
        this.ipAddress = ip;
    }
 
    public void setSrcPath(String path) {
        this.srcPath = path;
    }
 
    public void setDstPath(String path) {
        this.dstPath = path;
    }

    public void setPort(int port){ this.port = port;}

    private void createConnection() {
        Runnable connectRunnable = new Runnable() {
            public void run() {
                while (!connectedStatus) {
                    try {
                        // TODO: 3/18/17 Ip Address correction
                        clientSocket = new Socket(ipAddress, port);
                        connectedStatus = true;
                        transmitter = new DirectoryTxr(clientSocket, srcPath, dstPath);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }
        };

        Thread connectionThread = new Thread(connectRunnable);
        connectionThread.start();
    }

    public static void main(String[] args) {
        NetCpy main = new NetCpy();
        main.setIpAddress("localHost");
        main.setPort(3329);
        main.setSrcPath("/home/sushil/Videos/Movies/Braveheart (1995)");
        main.setDstPath("/home/sushil/Desktop/test");
        main.createConnection();
    }
}