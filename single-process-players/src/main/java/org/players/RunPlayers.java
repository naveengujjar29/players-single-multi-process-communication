package org.players;

/*
* This class will initialize the instances and run.
 */
public class RunPlayers {

    public static void main(String[] args) {

        Player initiator = PlayerFactory.getPlayer(PlayerType.INITIATOR);
        Player receiver = PlayerFactory.getPlayer(PlayerType.RECEIVER);
        initiator.start();
        receiver.start();
        try {
            SharedSingletonResource.getInstance().getSharedCountDownLatch().await();
            System.out.println("Closing down the program.");
            initiator.interrupt();
            receiver.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
