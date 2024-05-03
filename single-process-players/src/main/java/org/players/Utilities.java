package org.players;

public class Utilities {

    public static int getAcknowledgementCounterFromMessage(String acknowledgmentMessage) {
        int lastIndex = acknowledgmentMessage.lastIndexOf(':');

        if (lastIndex != -1) {
            String countString = acknowledgmentMessage.substring(lastIndex + 1).trim();

            try {
                return Integer.parseInt(countString);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
