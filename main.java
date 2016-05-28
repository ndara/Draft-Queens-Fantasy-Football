import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
      Connection conn = DBConnection.getConnection();
      Statement statement = null;
      ResultSet results = null;


      //DB TESTING

      //DBConnection.getAllPlayers(conn);
      //DBConnection.updatePlayer(conn, "AA-1300", 42);
      //DBConnection.resetPlayer(conn);
      //DBConnection.getAllPlayers(conn);
      
      //DBConnection.getPosPlayers(conn, false, false, false, true, 1);

      //END DB TESTS

      //DO GUI STUFF HERE
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
               new GUI();
            }}
         );

         //close connection to DB 
         DBConnection.close(conn);
   }
}