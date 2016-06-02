import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class to manage a connection to the database.
 */
public class DBConnection {
   /**
    * Get a connection to the database.
    */
    
   public static Connection getConnection() throws Exception {
      // Instantiate Driver
      Class.forName("com.mysql.jdbc.Driver");

      /*
      String dbUser = "eaugusti";
      String dbPass = "funfun";
      String host = "csc-db0.csc.calpoly.edu";
      String dbName = "eaugusti";
      */

      String dbUser = "";
      String dbPass = "";
      String host = "";
      String dbName = "";

      System.out.println("Is this Charnpreet?  (y/n)");
      Scanner input = new Scanner(System.in);
      if ((input.next()).equals("y")) {
         dbUser += "root";
         dbPass += "pass";
         host += "localhost";
         dbName += "NFL";
      }
      else {
         System.out.println("Please enter your username:");

         dbUser = input.next();

         System.out.println("Please enter your password:");
         dbPass = input.next();

         System.out.println("Is the the database on a Cal Poly UNIX Server? (y/n) If n, host will default to 'localhost' ");
         String test = input.next();

         if (test.equals("y")) {
            host += "csc-db0.csc.calpoly.edu";
         }
         else {
            host += "localhost";
         }
         System.out.println("Please enter the database name:");
         dbName = input.next();
      }

      String dbUrl = String.format("jdbc:mysql://%s/%s?autoReconnect=true", host, dbName);

      return DriverManager.getConnection(dbUrl, dbUser, dbPass);
   }

   /**
    * Close the connection.
    */
   public static void close(Connection conn) {
      try {
         if (conn != null) {
            conn.close();
            conn = null;
         }
      } catch (Exception ex) {
         System.err.println("Unable to close DBConnection.");
         System.exit(1);
      }
   }


   public static ArrayList<String> getAllPlayers(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      String query = "SELECT player, fname, lname FROM Player";
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String first = results.getString(2);
            String last = results.getString(3);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s", playerID, first, last));
         }
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
      return allPlayers;
   }

   public static ArrayList<String> getAllTeamPlayers(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      String query = "SELECT player, fname, lname FROM Player WHERE teamOn = " + teamId;
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String first = results.getString(2);
            String last = results.getString(3);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s", playerID, first, last));
         }
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
      return allPlayers;
   }
public static ArrayList<String> getAllTeamPosPlayers(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      String query = "SELECT player, pos1,fname, lname FROM Player WHERE teamOn = " + teamId;
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String pos = results.getString(2);
            String first = results.getString(3);
            String last = results.getString(4);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s %s", playerID, pos,first, last));
         }
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
      return allPlayers;
   }
   public static void updatePlayer(Connection conn, String playerId, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Player SET teamOn = "+ teamId + " WHERE player =  " + "'" + playerId+ "'";
         try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         statement.executeUpdate(query);

         
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
   }

   public static void updateTeam(Connection conn, String position, String playerId, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      
      String query = "UPDATE Team SET " + position + " = " + "'" + playerId + "'" + " WHERE id = " + teamId;
         try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         statement.executeUpdate(query);

         
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
   }

   public static void initTeam(Connection conn, String playerName) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Team SET name = " + "'" + playerName + "'" + " WHERE id = 1";

         try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         statement.executeUpdate(query);

         
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
   }
  
   public static int getTeamId(Connection conn, String playerId, int numPlayers) {
      int num = 0;
      for (num = 1; num < numPlayers + 1; num++) {
         ArrayList<String> teamMembers = getAllTeamPlayers(conn, num);
         for (int i = 0; i< teamMembers.size(); i++) {
            String temp = teamMembers.get(i);
            String compare = temp.substring(1, 8);
            if (compare.equals(playerId)) {
               System.out.println("Deleting player from team " + num);
               return num;
            }
         }
      }
      //bad if it reaches here
      return -1; 
   }

   public static String getPosColName(Connection conn, String playerId, String rawPos, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String realPos = "";
      String query = "";
      boolean wr = false;
      boolean rb = false;
      if (rawPos.equals("QB") || rawPos.equals("TE")) {
         System.out.println(rawPos);
         return rawPos;
      }
      else if (rawPos.equals("WR")) {
         query += "Select WR1, WR2, WR3 from Team where id = " + teamId;
         wr = true;
      }
      else {
         query += "Select RB1, RB2 from Team where id = " + teamId;
         rb = true;
      }
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            if (wr) {
               realPos += "WR";
               ArrayList<String> cols = new ArrayList<String>();
               cols.add(results.getString(1));
               cols.add(results.getString(2));
               cols.add(results.getString(3));
               for (int i = 1; i < 4; i++) {
                  if (cols.get(i - 1).equals(playerId)) {
                     realPos += i;
                     System.out.println(realPos);
                     return realPos;
                  }
               }

            }
            else if (rb) {
               realPos += "RB";
               ArrayList<String> cols = new ArrayList<String>();
               cols.add(results.getString(1));
               cols.add(results.getString(2));
               for (int i = 1; i < 3; i++) {
                  if (cols.get(i - 1).equals(playerId)) {
                     realPos += i;
                     System.out.println(realPos);
                     return realPos;
                  }
               }
            }
           

         }
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
      System.out.println("Empty string....this is BAD");
      return realPos;
   }

   public static void dropPlayerFromTeam(Connection conn, int teamId, String position) {
      Statement statement = null;
      ResultSet results = null;
      System.out.println("CURRENT POSITION PASSED:" + position);
      String query = "UPDATE Team SET " + position + " = '' WHERE id = " + teamId;
        try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         statement.executeUpdate(query);

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
   }

   public static void resetPlayer(Connection conn, String playerId) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Player SET teamOn = 0 WHERE player = '" + playerId + "'";
        try {
         statement = conn.createStatement();
         statement.executeUpdate(query);

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
   }


  public static void resetPlayers(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Player SET teamOn = 0";
        try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         statement.executeUpdate(query);

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
   }

   public static ArrayList<String> getPosPlayers(Connection conn, boolean QB, boolean RB, boolean WR, boolean TE, int numTrue) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      int count = 0;
      if (numTrue == 0) {
         allPlayers.add("EMPTY");
         return allPlayers;
      }


      String query = "SELECT player, pos1, fname, lname FROM Player";
      if (numTrue > 0) {
         query += " WHERE ";
         if (QB) {
            query += " pos1 = 'QB'";
            count++;

         }
         if (RB) {
            if (count > 0) {
               query += " OR pos1 = 'RB'";
            }
            else {
               query += " pos1 = 'RB'";
            }
            count++;

         }
         if (WR) {
            if (count > 0) {
               query += " OR pos1 = 'WR'";
            }
            else {
               query += " pos1 = 'WR'";
            }
            count++;

         }
         if (TE) {
            if (count > 0) {
               query += " OR pos1 = 'TE'";
            }
            else {
               query += " pos1 = 'TE'";
            }
            count++;

         }

      }
      //System.out.println("QUERY MADE:");
      //System.out.println(query);
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String pos1 = results.getString(2);
            String first = results.getString(3);
            String last = results.getString(4);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s %s", playerID, pos1, first, last));
         }
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
      return allPlayers;
   }


   public static ArrayList<String> getAllAvailablePlayers(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      String query = "SELECT player, fname, lname FROM Player WHERE teamOn = 0";
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String first = results.getString(2);
            String last = results.getString(3);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s", playerID, first, last));
         }
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
      return allPlayers;
   }

   public static ArrayList<String> getAvailablePosPlayers(Connection conn, boolean QB, boolean RB, boolean WR, boolean TE, int numTrue) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allPlayers = new ArrayList<String>();
      int count = 0;
      if (numTrue == 0) {
         allPlayers.add("EMPTY");
         return allPlayers;
      }


      String query = "SELECT player, pos1, fname, lname FROM Player";
      query += " WHERE teamOn = 0 ";
      if (numTrue > 0) {
         query += " AND ( ";
         if (QB) {
            query += " pos1 = 'QB'";
            count++;

         }
         if (RB) {
            if (count > 0) {
               query += " OR pos1 = 'RB'";
            }
            else {
               query += " pos1 = 'RB'";
            }
            count++;

         }
         if (WR) {
            if (count > 0) {
               query += " OR pos1 = 'WR'";
            }
            else {
               query += " pos1 = 'WR'";
            }
            count++;

         }
         if (TE) {
            if (count > 0) {
               query += " OR pos1 = 'TE'";
            }
            else {
               query += " pos1 = 'TE'";
            }
            count++;

         }
         query += ")";

      }
      //System.out.println("QUERY MADE:");
      //System.out.println(query);
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String playerID = results.getString(1);
            String pos1 = results.getString(2);
            String first = results.getString(3);
            String last = results.getString(4);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allPlayers.add(String.format("%s %s %s %s", playerID, pos1, first, last));
         }
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
      return allPlayers;
   }


}
