import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import DBConnection.java;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.awt.*;
import javax.swing.*;


public class main {
	public static void main(String[] args) throws Exception {

      //DO ALL DB STUFF HERE
      Connection conn = getConnection();
      Statement statement = null;
      ResultSet results = null;

      String query = "SELECT * FROM Teachers";

      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String last = results.getString(1);
            String first = results.getString("first");
            int room = results.getInt(3);

            System.out.println(String.format("%s, %s -- %d", last, first, room));
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
         //close(conn);


         
      }
      //DO GUI STUFF HERE
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
               new GUI();
            }}
         );

         //close connection to DB ---CURRENTLY BROKEN
         //close(conn);
   }
}