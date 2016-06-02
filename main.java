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

import java.io.*;
import javax.sound.sampled.*;

public class main {
	public static void main(String[] args) throws Exception {

      //DO ALL DB STUFF HERE
      //Connection conn = DBConnection.getConnection();
      Statement statement = null;
      ResultSet results = null;


      //DB TESTING

      //DBConnection.getAllPlayers(conn);
      //DBConnection.updatePlayer(conn, "AA-1300", 42);
      //DBConnection.resetPlayer(conn);
      //DBConnection.getAllPlayers(conn);
      //DBConnection.getAllTeamPlayers(conn, 42);
      //DBConnection.getPosPlayers(conn, false, false, false, true, 1);
      //DBConnection.initTeam(conn, "TESTING");
      //DBConnection.updateTeam(conn, "RB1", "AA-1300", 3);

      //END DB TESTS

      //DO GUI STUFF HERE
     
      Thread music = new Thread() {
         public void run() {
            try {
               Clip clip = AudioSystem.getClip();
               clip.open(AudioSystem.getAudioInputStream(new File("song.wav")));
               clip.start();
            }

            catch (Exception ex) {
               System.exit(0);
            }
         }
      };

      music.start();

		SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() 
	         {
					try {
						new GUI();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	         }}
		);

         //close connection to DB 
         //DBConnection.close(conn);
   }

}
