import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

public class MediumAI {
   public static void draftPlayer(Connection conn, int round, int teamId) {

      switch(round) {
         case 1:
            selectPlayer(conn, "WR2", teamId);
            break;

         case 2:
            selectPlayer(conn, "RB1", teamId);
            break;

         case 3:
            selectPlayer(conn, "RB2", teamId);
            break;

         case 4:
            selectPlayer(conn, "WR1", teamId);
            break;

         case 5:
            selectPlayer(conn, "WR3", teamId);
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
      String playerPos = "";
      String playerStr = "";

      if(pos == "WR1" || pos == "WR2" || pos == "WR3")
         playerPos = "WR";
      else if(pos == "RB1" || pos == "RB2")
         playerPos = "RB";
      else if(pos == "QB")
         playerPos = "QB";
      else if(pos == "TE")
         playerPos = "TE";
      
      String query = "SELECT player FROM Player WHERE dcp = 1 AND pos1 = '" + playerPos + "' AND teamOn = 0 ORDER BY RAND() LIMIT 1";
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

   private static String bestSelection(Connection conn, String pos, int teamId) {

   }
}

