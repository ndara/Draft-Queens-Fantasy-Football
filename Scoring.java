import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

public class Scoring {

	public static void main(String[] args) throws Exception {

      //DO ALL DB STUFF HERE
      Connection conn = DBConnection.getConnection();

      //DB TESTING
      getPlayerScore(conn, 1, "CN-0500", "QB");
      //DBConnection.getAllPlayers(conn);
      //DBConnection.updatePlayer(conn, "AA-1300", 42);
      //DBConnection.resetPlayer(conn);
      //DBConnection.getAllPlayers(conn);
      //DBConnection.getAllTeamPlayers(conn, 42);
      //DBConnection.getPosPlayers(conn, false, false, false, true, 1);
      //DBConnection.initTeam(conn, "TESTING");
      //DBConnection.updateTeam(conn, "RB1", "AA-1300", 3);

      DBConnection.close(conn);
   }

   public static int getPlayerScore(Connection conn, int week, String player, String pos) {
   	Statement statement = null;
      ResultSet results = null;
      int score = 0;

      if (pos == "QB")
         score = qbScore(conn, week, player);
      //else if (pos == "RB")
      	//score = rbScore(conn, week, player);
      //else if (pos == "WR" || pos == "TE")
      	//score = recScore(conn, week, player);
      else
      	System.out.println("Invalid Position type: " + pos);

      System.out.println(player + " on week " + week + " scored " + score + " points.");
		return score;      
   }

   public static int qbScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      int score = 0;

      String query = "SELECT SUM(yds) / 25 + X.tds AS pts FROM Play P JOIN Game G ON G.gid = P.gid \n" +
   							"JOIN Pass S ON P.pid = S.pid JOIN Player L ON L.player = S.psr JOIN (SELECT " +
        						"COUNT(*) * 4 AS tds, L.player FROM Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON S.pid = P.pid " +
         					"JOIN Player L ON S.psr = L.player JOIN Td T ON P.pid = T.pid WHERE wk = " + week + " 	AND P.type = 'PASS'" +
         					"AND L.player = '" + player + "' ) X ON X.player = L.player WHERE wk = " + week + " AND P.type = 'PASS'" +
   							"AND L.player = '" + player + "'";

      try {
         // Get a statement from the connection
         statement = conn.createStatement();
 			System.out.println("statements\n"+query);
         // Execute the query
         results = statement.executeQuery(query);
 			System.out.println("results\n");
         String scoreStr = results.getString(1);
         score = Integer.parseInt(scoreStr);

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
      return score;
   }

   /*public static int rbScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      int score;

      String query = "SELECT
   							(SUM(R.yds) / 10) + X.tds AS pts
							FROM
   							Play P 
   							JOIN Game G ON G.gid = P.gid
   							JOIN Rush R ON R.pid = P.pid
   							JOIN Player L ON R.bc = L.player
   						JOIN (
      						SELECT	
         						COUNT(*) * 6 AS tds,
         						L.player AS player
      						FROM
         						Play P
         						JOIN Game G ON G.gid = P.gid
	         					JOIN Rush R ON R.pid = P.pid
   	      					JOIN Player L ON R.bc = L.player
      	   					JOIN Td T ON P.pid = T.pid
      						WHERE
         						wk = " + week + " 
         						AND P.type = 'RUSH'
         						AND L.player = '" + player + "' 
   						) X ON X.player = L.player
							WHERE
   							wk = " + week + " 
   							AND P.type = 'RUSH'
   							AND L.player = '" + player + "'";
      try {
         // Get a statement from the connection
         statement = conn.createStatement();
         // Execute the query
         results = statement.executeQuery(query);
         String scoreStr = results.getString(1);
         score = Integer.parseInt(scoreStr);
         
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
      return score;
   }

   public static int recScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      int score;

      String query = "SELECT 
   							(SUM(S.yds) / 10) + X.tds AS pts
							FROM
   							Play P
   							JOIN Game G ON G.gid = P.gid
   							JOIN Pass S ON P.pid = S.pid
   							JOIN Player L ON S.trg = L.player
   							JOIN (
      							SELECT
         							COUNT(*) * 6 AS tds,
         							L.player
      							FROM
         							Play P
         							JOIN Game G ON G.gid = P.gid
         							JOIN Pass S ON S.pid = P.pid
         							JOIN Player L ON S.trg = L.player
         							JOIN Td T ON P.pid = T.pid
      							WHERE
         							wk = " + week + " 
         							AND P.type = 'PASS'
         							AND L.player = '" + player + "' 
   							) X ON X.player = L.player 
							WHERE
   							wk = " + week + " 
   							AND P.type = 'PASS'
   							AND L.player = '" + player + "'";
      try {
         // Get a statement from the connection
         statement = conn.createStatement();
         // Execute the query
         results = statement.executeQuery(query);
         String scoreStr = results.getString(1);
         score = Integer.parseInt(scoreStr);
         
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
      return score;
   }*/
}
