#!/bin/bash

mvn clean package

gnome-terminal -- java -jar receiver-socket/target/receiver-player-socket-1.0-SNAPSHOT.jar org.players.ReceiverPlayerSocket

gnome-terminal -- java -jar initiator-socket/target/initiator-player-socket-1.0-SNAPSHOT.jar org.players.InitiatorPlayerSocket


