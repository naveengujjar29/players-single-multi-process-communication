#!/bin/bash

mvn clean package

java -jar receiver-socket/target/receiver-player-socket-1.0-SNAPSHOT.jar org.players.ReceiverPlayerSocket &

java -jar initiator-socket/target/initiator-player-socket-1.0-SNAPSHOT.jar org.players.InitiatorPlayerSocket
