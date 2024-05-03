package org.players;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/*
Player class will be responsible for sending, receiving and acknowledging the message
 */
class Player extends Thread {
    private String name;
    private BlockingQueue<Message> messageQueue;

    private BlockingQueue<Message> ackMessageQueue;
    private boolean isInitiator;

    private AtomicInteger atomicCounter = new AtomicInteger(1);


    private AtomicBoolean shouldContinueProcess;

    public Player(String name, boolean isInitiator, BlockingQueue<Message> messageQueue,
                  BlockingQueue<Message> ackMessageQueue, AtomicBoolean shouldContinueProcess) {
        this.name = name;
        this.messageQueue = messageQueue;
        this.ackMessageQueue = ackMessageQueue;
        this.isInitiator = isInitiator;
        this.shouldContinueProcess = shouldContinueProcess;
    }

    @Override
    public void run() {
        try {
            while (shouldContinueProcess.get()) {
                if (isInitiator) {
                    Message message = new Message("Hello,  on the other side");
                    sendMessage(message);
                    message = ackMessageQueue.take();
                    System.out.println(name + " received acknowledgment:::::" + message.getContent());
                    int counterValue = Utilities.getAcknowledgementCounterFromMessage(message.getContent());
                    if (counterValue == 10) {
                        System.out.println("Required acknowledgment has been received, latching down the countdown " +
                                "latch" +
                                ".");
                        shouldContinueProcess.getAndSet(false);
                        SharedSingletonResource.getInstance().getSharedCountDownLatch().countDown();
                        break;
                    }
                } else {
                    Message message = messageQueue.take();
                    System.out.println(name + " received message::::::" + message.getContent() );
                    String response = message.getContent() + ":" + atomicCounter.get();
                    Message responseMessage = new Message(response);
                    sendAckMessage(responseMessage);
                    atomicCounter.getAndIncrement();
                }
            }
        } catch (InterruptedException e) {

        }
    }

    public void sendMessage(Message message) throws InterruptedException {
        System.out.println(this.name+ " sent::::::" + message);
        messageQueue.put(message);
    }

    public void sendAckMessage(Message message) throws InterruptedException {
        System.out.println(this.name+ " sent acknowledgment:::::" + message);
        ackMessageQueue.put(message);
    }
}
