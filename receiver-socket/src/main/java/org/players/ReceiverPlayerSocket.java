package org.players;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverPlayerSocket {


    public static final int PORT = 8089;

    public static ServerSocket getServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            return serverSocket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = getServerSocket();
        System.out.println("Receiver waiting for messages...");
        Socket clientSocket = serverSocket.accept();
        Thread receiverThread = new Thread(new ReceiverDataThread(clientSocket));
        receiverThread.start();
    }
}
