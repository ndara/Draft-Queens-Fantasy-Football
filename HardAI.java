import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

public class HardAI {

   public static void draftPlayer(Connection conn, int round, int teamId) {

      switch(round) {
         case 1:
            selectPlayer(conn, "RB1", teamId);
            break;

         case 2:
            selectPlayer(conn, "WR3", teamId);
            break;

         case 3:
            selectPlayer(conn, "WR1", teamId);
            break;

         case 4:
            selectPlayer(conn, "WR2", teamId);
            break;

         case 5:
            selectPlayer(conn, "RB2", teamId);
            break;

         case 6:
            selectPlayer(conn, "QB", teamId);
            break;

         case 7:
            selectPlayer(conn, "TE", teamId);
            break;
      }
   }

   public static void swapPlayerRandom(Connection conn, int teamId) {
      Random randGen = new Random();
      int randNum = randGen.nextInt(7);

      switch(randNum) {
         case 0:
            swapPlayer(conn, "QB", teamId);
            break;

         case 1:
            swapPlayer(conn, "RB1", teamId);
            break;

         case 2:
            swapPlayer(conn, "RB2", teamId);
            break;

         case 3:
            swapPlayer(conn, "WR1", teamId);
            break;

         case 4:
            swapPlayer(conn, "WR2", teamId);
            break;

         case 5:
            swapPlayer(conn, "WR3", teamId);
            break;

         case 6:
            swapPlayer(conn, "TE", teamId);
            break;
      }
   }

   private static void swapPlayer(Connection conn, String pos, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String query = "SELECT " + pos + " FROM Team WHERE id = " + teamId;
      String playerId = "";

      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            playerId = results.getString(1);         
      } catch (SQLException sqlEx) {
         System.err.println("Error doing query: " + sqlEx);
         sqlEx.printStackTrace(System.err);
      } finally {
         try {
            if (results != null) {
               results.close();
               results = null;
            }

            if (statement != null) {
               statement.close();
               statement = null;
            }
         } catch (Exception ex) {
            System.err.println("Error closing query: " + ex);
            ex.printStackTrace(System.err);
         }     
      }

      DBConnection.dropPlayerFromTeam(conn, teamId, pos);
      DBConnection.resetPlayer(conn, playerId);
      selectPlayer(conn, pos, teamId);
   }

   private static void selectPlayer(Connection conn, String pos, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String playerStr = "";
      String query = "";

      if(pos == "WR1" || pos == "WR2" || pos == "WR3")
         query = wrReturn();
      else if(pos == "RB1" || pos == "RB2")
         query = rbReturn();
      else if(pos == "QB")
         query = qbReturn();
      else if(pos == "TE")
         query = teReturn();


      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            playerStr = results.getString(1);
      } catch (SQLException sqlEx) {
         System.err.println("Error doing query: " + sqlEx);
         sqlEx.printStackTrace(System.err);
      } finally {
         try {
            if(results != null) {
               results.close();
               results = null;
            }

            if(statement != null) {
               statement.close();
               statement = null;
            }
         } catch (Exception ex) {
            System.err.println("Error close query: " + ex);
         }
      }
      DBConnection.updatePlayer(conn, playerStr, teamId);
      DBConnection.updateTeam(conn, pos, playerStr, teamId);
   }

   private static String qbReturn() {
      return "SELECT Z.player FROM (SELECT AVG(Y.pts) AS avgPts, Y.player, Y.fname, Y.lname FROM (SELECT G.wk, " +
             "SUM(yds) / 25 + COALESCE(X.tds, 0) AS pts, L.player, L.fname, L.lname, L.pos1, L.teamOn FROM " +
             "Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON P.pid = S.pid JOIN Player L ON L.player = S.psr " +
             "LEFT JOIN (SELECT wk, COUNT(*) * 4 AS tds, L.player FROM Play P JOIN Game G ON G.gid = P.gid " +
             "JOIN Pass S ON S.pid = P.pid JOIN Player L ON S.psr = L.player JOIN Td T ON P.pid = T.pid WHERE " +
             "P.type = 'PASS' AND teamOn = 0 GROUP BY wk, L.player) X ON X.player = L.player AND X.wk = G.wk " +
             "WHERE P.type = 'PASS' AND teamOn = 0 GROUP BY G.wk, L.player ORDER BY L.player, G.wk) Y " +
             "WHERE Y.pos1 = 'QB' AND Y.teamOn = 0 GROUP BY Y.player ORDER BY avgPts DESC LIMIT 5) Z " +
             "ORDER BY RAND() LIMIT 1";
   }

   private static String rbReturn() {
      return "SELECT Z.player FROM (SELECT AVG(Y.pts) AS avgPts, Y.player, Y.fname, Y.lname FROM (SELECT G.wk, " + 
             "(SUM(R.yds) / 10) + COALESCE(X.tds, 0) AS pts, L.player, L.fname, L.lname, L.pos1, L.teamOn FROM " +
             "Play P JOIN Game G ON G.gid = P.gid JOIN Rush R ON P.pid = R.pid JOIN Player L ON R.bc = L.player " +
             "LEFT JOIN (SELECT COUNT(*) * 6 AS tds, L.player, wk FROM Play P JOIN Game G ON G.gid = P.gid " +
             "JOIN Rush R ON R.pid = P.pid JOIN Player L ON R.bc = L.player JOIN Td T ON P.pid = T.pid " +
             "WHERE P.type = 'RUSH' AND teamOn = 0 GROUP BY wk, L.player) X ON X.player = L.player AND X.wk = G.wk " +
             "WHERE P.type = 'RUSH' AND teamOn = 0 GROUP BY G.wk, L.player ORDER BY L.player, G.wk) Y " +
             "WHERE Y.pos1 = 'RB' AND Y.teamOn = 0 GROUP BY Y.player ORDER BY avgPts DESC LIMIT 5) Z " +
             "ORDER BY RAND() LIMIT 1";
   }
   
   private static String wrReturn() {
      return "SELECT Z.player FROM (SELECT AVG(Y.pts) AS avgPts, Y.player, Y.fname, Y.lname FROM (SELECT G.wk, " +
             "(SUM(S.yds) / 10) + COALESCE(X.tds, 0) AS pts, L.player, L.fname, L.lname, L.pos1, L.teamOn FROM Play P " +
             "JOIN Game G ON G.gid = P.gid JOIN Pass S ON P.pid = S.pid JOIN Player L ON S.trg = L.player LEFT JOIN (" +
             "SELECT COUNT(*) * 6 AS tds, L.player, wk FROM Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON S.pid = P.pid " +
             "JOIN Player L ON S.trg = L.player JOIN Td T ON P.pid = T.pid WHERE P.type = 'PASS' AND teamOn = 0 " +
             "GROUP BY wk, L.player) X ON X.player = L.player AND X.wk = G.wk WHERE P.type = 'PASS' AND teamOn = 0 " +
             "GROUP BY G.wk, L.player) Y WHERE Y.pos1 = 'WR' AND Y.teamOn = 0 GROUP BY Y.player ORDER BY avgPts DESC " +
             "LIMIT 5) Z ORDER BY RAND() LIMIT 1";
   }

   private static String teReturn() {
      return "SELECT Z.player FROM (SELECT AVG(Y.pts) AS avgPts, Y.player, Y.fname, Y.lname FROM (SELECT G.wk, " +
             "(SUM(S.yds) / 10) + COALESCE(X.tds, 0) AS pts, L.player, L.fname, L.lname, L.pos1, L.teamOn FROM " +
             "Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON P.pid = S.pid JOIN Player L ON S.trg = L.player " +
             "LEFT JOIN (SELECT COUNT(*) * 6 AS tds, L.player, wk FROM Play P JOIN Game G ON G.gid = P.gid " +
             "JOIN Pass S ON S.pid = P.pid JOIN Player L ON S.trg = L.player JOIN Td T ON P.pid = T.pid WHERE " +
             "P.type = 'PASS' AND teamOn = 0 GROUP BY wk, L.player) X ON X.player = L.player AND X.wk = G.wk " +
             "WHERE P.type = 'PASS' AND teamOn = 0 GROUP BY G.wk, L.player) Y WHERE Y.pos1 = 'TE' AND Y.teamOn = 0 " +
             "GROUP BY Y.player ORDER BY avgPts DESC LIMIT 5) Z ORDER BY RAND() LIMIT 1";
   }
}
