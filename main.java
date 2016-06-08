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

      Statement statement = null;
      ResultSet results = null;

     
      Thread music = new Thread() {
         public void run() {
            try {
               Clip clip = AudioSystem.getClip();
               clip.open(AudioSystem.getAudioInputStream(new File("song.wav")));
	       clip.loop(Clip.LOOP_CONTINUOUSLY);
               Thread.sleep(10000); // looping as long as this thread is alive
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
			// Set cross-platform 
                        //changing this value will change the look and feel commente onen will get default for the machine
                        //"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
                        //"com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
                        //UIManager.getSystemLookAndFeelClassName()
                	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			new GUI();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	         }}
		);
   }

}
