package com.company;

public class NetRcv {
    public static void main(String[] args) {
        DirectoryRcr dirRcr = new DirectoryRcr();
        dirRcr.acceptConnection(3329);
    }
}