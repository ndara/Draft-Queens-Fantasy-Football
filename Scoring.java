import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

public class Scoring {
   
   
	/*public static void main(String[] args) throws Exception {

      //DO ALL DB STUFF HERE
      Connection conn = DBConnection.getConnection();
      
      //DB TESTING
      //getPlayerScore(conn,1 , "DW-4300");
      DBConnection.initTeam(conn, "bitchnigga");
      DBConnection.updateTeam(conn, "QB", "CN-0500", 1);
      DBConnection.updateTeam(conn, "RB1", "DM-0450", 1);
      DBConnection.updateTeam(conn, "RB2", "LB-0250", 1);
      DBConnection.updateTeam(conn, "WR1", "WS-0925", 1);
      DBConnection.updateTeam(conn, "WR2", "BM-0300", 1);
      DBConnection.updateTeam(conn, "WR3", "JJ-4700", 1);
      DBConnection.updateTeam(conn, "TE", "RG-2200", 1);
      // System.out.println("here is the total team score : " + getTeamScore(conn, 3, 1));
      addTeamToLeaderboard(conn, 1);
      addTeamToLeaderboard(conn, 1);
      addTeamToLeaderboard(conn, 1);
      ArrayList<String> tmp = new ArrayList<String>();
      tmp = getLeadeoard(conn);

      for (int i = 0; i < tmp.size(); i++) {
         System.out.println(tmp.get(i));
      }

      resetLeaderboard(conn);
      // RandomAI.swapPlayerRandom(conn, 2);
      // RandomAI.swapPlayerRandom(conn, 2);
      // RandomAI.swapPlayerRandom(conn, 2);
      // RandomAI.swapPlayerRandom(conn, 2);
      //resetTeams(conn);
      
      DBConnection.close(conn);
   }*/
   
   

   public static void editTeamScore(Connection conn, int id, double score) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Team SET score = score + " + score + " WHERE id = " + id;

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

   public static void runWeek(Connection conn, int week, int numPlayers) {
      for (int i = 1; i < numPlayers + 1; i++) {
         getTeamScore(conn, week, i);
      }
   }

   public static String getLoserTeam(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      String str = "";
      String query = "SELECT id, name, score FROM Team WHERE elim = false ORDER BY score LIMIT 1" ;
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String teamID = results.getString(1);
            String teamName = results.getString(2);
            String teamScore = results.getString(3);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            str += String.format("%s %s %s", teamID, teamName, teamScore);
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
      return str;
   }

   public static ArrayList<String> getRoundStats(Connection conn, boolean getALL) {
      Statement statement = null;
      ResultSet results = null;
      ArrayList<String> allTeams = new ArrayList<String>();
      String query = "";
      if (getALL == false) {
         query += "SELECT name, score FROM Team WHERE elim = false ORDER BY score DESC" ;
      }
      else {
         query += "SELECT name, score FROM Team ORDER BY score DESC";
      }
      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String teamName = results.getString(1);
            String teamScore = results.getString(2);
            //String first = results.getString("first");
            //int room = results.getInt(3);

            allTeams.add(String.format("%s %s", teamName, teamScore));
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
      return allTeams;
   }


   public static double getTeamScore(Connection conn, int week, int id) {
      Statement statement = null;
      ResultSet results = null;

      String qbQuery = "SELECT QB FROM Team WHERE id = " + id;
      String rb1Query = "SELECT RB1 FROM Team WHERE id = " + id;
      String rb2Query = "SELECT RB2 FROM Team WHERE id = " + id;
      String wr1Query = "SELECT WR1 FROM Team WHERE id = " + id;
      String wr2Query = "SELECT WR2 FROM Team WHERE id = " + id;
      String wr3Query = "SELECT WR3 FROM Team WHERE id = " + id;
      String teQuery = "SELECT TE FROM Team WHERE id = " + id;

      String qbStr = "";
      String rb1Str = "";
      String rb2Str = "";
      String wr1Str = "";
      String wr2Str = "";
      String wr3Str = "";
      String teStr = "";

      double teamScore = 0;
      try {
         statement = conn.createStatement();
         results = statement.executeQuery(qbQuery);
         if(results.next())
            qbStr = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(rb1Query);
         if(results.next())
            rb1Str = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(rb2Query);
         if(results.next())
            rb2Str = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(wr1Query);
         if(results.next())
            wr1Str = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(wr2Query);
         if(results.next())
            wr2Str = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(wr3Query);
         if(results.next())
            wr3Str = results.getString(1);

         statement = conn.createStatement();
         results = statement.executeQuery(teQuery);
         if(results.next())
            teStr = results.getString(1);


         teamScore = getPlayerScore(conn, week, qbStr) + getPlayerScore(conn, week, rb1Str)
                     + getPlayerScore(conn, week, rb2Str) + getPlayerScore(conn, week, wr1Str)
                     + getPlayerScore(conn, week, wr2Str) + getPlayerScore(conn, week, wr3Str)
                     + getPlayerScore(conn, week, teStr);

         editTeamScore(conn, id, teamScore);

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

      return teamScore;
   }

   public static double getPlayerScore(Connection conn, int week, String player) {
      double score = qbScore(conn, week, player) + rbScore(conn, week, player) + recScore(conn, week, player);
      //System.out.println(player + " on week " + week + " scored " + score + " points.");
      System.out.println("SCORE: " + score + " WEEK: " + week + " PLAYER: " + player);
		return score;      
   }

   public static void eliminateTeam(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Team SET elim = true where id = " + teamId;
      System.out.println(query);
      //System.out.println(query);
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


   public static void resetTeams(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      String query = "UPDATE Team SET score = 0, elim = false ,QB = '', RB1 = '', RB2 = '', WR1 = '', WR2 = '', WR3 = '', TE = ''";
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

   public static double qbScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      double score = 0;
      String scoreStr = "";

      String query = "SELECT COALESCE(SUM(yds) / 25 + X.tds, 0) AS pts FROM Play P JOIN Game G ON G.gid = P.gid JOIN " + 
                     "Pass S ON P.pid = S.pid JOIN Player L ON L.player = S.psr JOIN (SELECT COUNT(*) * " + 
                     "4 AS tds, L.player FROM Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON S.pid = " + 
                     "P.pid JOIN Player L ON S.psr = L.player JOIN Td T ON P.pid = T.pid WHERE wk = " + week + 
                     " AND P.type = 'PASS' AND L.player = '" + player + "') X ON X.player = L.player WHERE wk = " + 
                     week + " AND P.type = 'PASS' AND L.player = '" + player + "'";

      try {
         // Get a statement from the connection
         statement = conn.createStatement();
         // Execute the query
         results = statement.executeQuery(query);
         if(results.next())
            scoreStr = results.getString(1);
         if (scoreStr == null)
            scoreStr = "0";

         score = Double.parseDouble(scoreStr);

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

   public static double rbScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      double score = 0;
      String scoreStr = "";

      String query = "SELECT COALESCE((SUM(R.yds) / 10) + X.tds, 0) AS pts FROM Play P JOIN Game G ON G.gid = P.gid " + 
   						"JOIN Rush R ON R.pid = P.pid JOIN Player L ON R.bc = L.player JOIN (SELECT COUNT(*) * 6 AS tds, " +
         				"L.player AS player FROM Play P JOIN Game G ON G.gid = P.gid JOIN Rush R ON R.pid = P.pid JOIN Player L ON R.bc = L.player " +
      	   			"JOIN Td T ON P.pid = T.pid WHERE wk = " + week + " AND P.type = 'RUSH' AND L.player = '" + player + "') X ON X.player = L.player " +
							"WHERE wk = " + week + " AND P.type = 'RUSH' AND L.player = '" + player + "'";
      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            scoreStr = results.getString(1);
         if (scoreStr == null)
            scoreStr = "0";
         score = Double.parseDouble(scoreStr);
         
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

   public static double recScore(Connection conn, int week, String player) {
   	Statement statement = null;
      ResultSet results = null;
      double score = 0;
      String scoreStr = "";

      String query = "SELECT COALESCE((SUM(S.yds) / 10) + X.tds, 0) AS pts FROM Play P JOIN Game G ON G.gid = P.gid " +
   						"JOIN Pass S ON P.pid = S.pid JOIN Player L ON S.trg = L.player JOIN (SELECT COUNT(*) * 6 AS tds, " +
         				"L.player FROM Play P JOIN Game G ON G.gid = P.gid JOIN Pass S ON S.pid = P.pid JOIN Player L ON S.trg = L.player " +
         				"JOIN Td T ON P.pid = T.pid WHERE wk = " + week + " AND P.type = 'PASS' AND L.player = '" + player + "') X ON X.player = L.player " + 
							"WHERE wk = " + week + " AND P.type = 'PASS' AND L.player = '" + player + "'";
      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            scoreStr = results.getString(1);
         if (scoreStr == null)
            scoreStr = "0";
         score = Double.parseDouble(scoreStr);
         
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

   public static void addTeamToLeaderboard(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String name = getTeamName(conn, teamId);
      Double score = queryTeamScore(conn, teamId);
      String qb = getPostionFromTeam(conn, teamId, "QB");
      String rb1 = getPostionFromTeam(conn, teamId, "RB1");
      String rb2 = getPostionFromTeam(conn, teamId, "RB2");
      String wr1 = getPostionFromTeam(conn, teamId, "WR1");
      String wr2 = getPostionFromTeam(conn, teamId, "WR2");
      String wr3 = getPostionFromTeam(conn, teamId, "WR3");
      String te = getPostionFromTeam(conn, teamId, "TE");
      String query = "INSERT INTO Leaderboard (name, score, QB, RB1, RB2, WR1, WR2, WR3, TE) VALUES (" + "'" + name + "', " + score +
                     ", '" + qb + "', '" + rb1 + "', '" + rb2 + "', '" + wr1 + "', '" + wr2 + "', '" + wr3 + "', '" + te + "')";

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

   private static String getPostionFromTeam(Connection conn, int teamId, String pos) {
      Statement statement = null;
      ResultSet results = null;
      String query = "SELECT " + pos + " FROM Team WHERE id = " + teamId;
      String player = "";

      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            player = results.getString(1);

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
      return player;
   }

   private static String getTeamName(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      String query = "SELECT name FROM Team WHERE id = " + teamId;
      String team = "";

      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            team = results.getString(1);

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
      return team;
   }

   public static double queryTeamScore(Connection conn, int teamId) {
      Statement statement = null;
      ResultSet results = null;
      double score = 0;
      String scoreStr = "";

      String query = "SELECT score FROM Team WHERE id = " + teamId;
      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);
         if(results.next())
            scoreStr = results.getString(1);
         if (scoreStr == null)
            scoreStr = "0";
         score = Double.parseDouble(scoreStr);
         
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

   public static void resetLeaderboard(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      String query = "DELETE FROM Leaderboard";
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

   public static ArrayList<String> getLeadeoard(Connection conn) {
      Statement statement = null;
      ResultSet results = null;
      String res = "";
      ArrayList<String> leaders = new ArrayList<String>();

      String query = "SELECT * FROM Leaderboard";
      try {
         statement = conn.createStatement();
         results = statement.executeQuery(query);

         while (results.next()) {
            leaders.add(results.getString(1) + "|" + results.getString(2) + "|" + results.getString(3) + 
                        "|" + results.getString(4) + "|" + results.getString(5) + "|" + results.getString(6) +
                        "|" + results.getString(7) + "|" + results.getString(8) + "|" + results.getString(9));
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
      return leaders;
   }
}
