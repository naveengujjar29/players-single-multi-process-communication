package org.players;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReceiverDataThread implements Runnable {
    private final Socket clientSocket;

    public ReceiverDataThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            int messageCounter = 0;
            while (true) {
                String message = (String) inputStream.readObject();
                System.out.println("Receiver received message: " + message);
                String acknowledgment =
                        "Acknowledgment for message: " + message + ", Message Counter:" + ++messageCounter;
                System.out.println("Receiver sending acknowledgment: " + acknowledgment);
                outputStream.writeObject(acknowledgment);
                outputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection has been closed.");
        }
    }
}
