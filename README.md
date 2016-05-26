# DatabasesFinalProject
This repository will be used to store our source code for our CPE365 final project. The project will revolve around the idea of fantasy football.


CLASSPATH info (make sure .jar is in same directory as java files)

export CLASSPATH=$CLASSPATH:mysql-connector-java-5.1.15-bin.jar

NOTE2: Rush table bc col should reference player(player)??


CLASSES 
MAIN.java
GUI.java


- GUI()
DBConnection.java
-getQuery(String Query)
-getCount(String table)

Player.java // ID is always 1
-getTeamName
-getTeam


Draft.java
-getDraftOrder(numPlayers)
-draftPlayer(INT PlayerId,String player)
-draft(Players)


AI.java // Id starts at 2 and up


Fantasysim.java
-getWeekPlayerScore
-getWeekScoreTeam(Array players scores)
-eliminateLowest Player() // search team scores
-swapPlayer
