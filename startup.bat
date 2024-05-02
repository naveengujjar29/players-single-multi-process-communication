@echo off

rem Run Maven clean package
call mvn clean package

rem Run the JAR with the specified ReceiverPlayerSocket main class
start cmd /c "java -jar receiver-socket\\target\\receiver-player-socket-1.0-SNAPSHOT.jar org.players.ReceiverPlayerSocket"

rem Run the JAR with the specified ReceiverPlayerSocket main class
start cmd /c "java -jar initiator-socket\\target\\initiator-player-socket-1.0-SNAPSHOT.jar org.players.InitiatorPlayerSocket"


