package org.players;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InitiatorDataThread implements Runnable {

    private final Socket initiatorSocket;

    public InitiatorDataThread(Socket clientSocket) {
        this.initiatorSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(initiatorSocket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(initiatorSocket.getInputStream());

            int acknowledgementCounter = 0;

            while (acknowledgementCounter < 10) {
                String message = "Hello, on the other side";
                System.out.println("Initiator sending message: " + message);
                outputStream.writeObject(message);
                outputStream.flush();
                Thread.sleep(1000);
                String acknowledgment = (String) inputStream.readObject();
                System.out.println("Initiator received acknowledgment: " + acknowledgment);
                acknowledgementCounter = Utilities.getAcknowledgementCounterFromMessage(acknowledgment);
            }
            System.out.println("Required acknowledgment has been received, closing the socket");
            initiatorSocket.close();
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
