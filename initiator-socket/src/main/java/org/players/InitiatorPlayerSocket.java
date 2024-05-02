package org.players;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class InitiatorPlayerSocket {

    public static final int PORT = 8089;

    private static Socket connectToWebSocket() throws InterruptedException {
        boolean isSocketConnected = false;
        Socket socket = null;
        while (!isSocketConnected) {
            try {
                socket = new Socket("localhost", PORT);
                isSocketConnected = true;
            } catch (ConnectException ex) {
                System.out.println("Waiting to connect the Receiver on port::" + PORT + " receiver might not be " +
                        "running.");
                Thread.sleep(1800);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return socket;
    }


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Socket socket = connectToWebSocket();
        Thread initiatorThread = new Thread(new InitiatorDataThread(socket));
        initiatorThread.start();
    }

}
