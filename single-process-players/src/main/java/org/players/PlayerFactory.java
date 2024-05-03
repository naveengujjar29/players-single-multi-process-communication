package org.players;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/*
Return the player type instance based on the input type.
 */
public class PlayerFactory {

    private static final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>(1);

    private static final BlockingQueue<Message> ackMessageQueue = new LinkedBlockingQueue<>(1);

    private static AtomicBoolean shouldProcessMessage = new  AtomicBoolean(true);

    public static Player getPlayer(PlayerType playerType) {
        Player player;
        if(playerType == PlayerType.INITIATOR) {
            player = new Player("Initiator", true, messageQueue, ackMessageQueue, shouldProcessMessage);
        } else if(playerType == PlayerType.RECEIVER) {
            player = new Player("Receiver", false, messageQueue, ackMessageQueue, shouldProcessMessage);
        } else {
            throw new RuntimeException("PlayerType" + playerType + " is not supported");
        }
        return player;
    }


}
