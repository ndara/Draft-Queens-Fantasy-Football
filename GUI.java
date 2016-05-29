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

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class GUI extends JFrame implements ActionListener, WindowListener, MouseListener {
	private JTextField teamName =new JTextField("", 20);// 20 is variable
	private JTextField playerAdd=new JTextField("", 20);
	private Container cp;
	private Container cpid=new Container();
	private JPanel title;
	private JPanel initialDraft;
	public Connection conn;
	public Statement statement = null;
	public ResultSet results = null;
	
	

	public GUI() throws Exception
	{
	
	conn = DBConnection.getConnection();
	 cp = getContentPane();
	 cp.setLayout(new GridLayout(1,1,1,1));
	 initDraft();
	 
	 
	 //Title screen
	 title =new JPanel();
	 title.setLayout(new GridLayout(3,1));
	 //first item in grid
	 title.add(new JLabel("Draft Queens"));
	 //second item in grid
	 JPanel teamNamer=new JPanel();
	 teamNamer.setLayout(new FlowLayout());
	 teamNamer.add(new JLabel("Team Name:"));
	 teamName.setEditable(true);
	 teamNamer.add(teamName);
	 JButton teamConfirm=new JButton("Confirm");
	 teamNamer.add(teamConfirm);
	 teamConfirm.addActionListener(this);
	 //teamName.addActionListener(this);
	 title.add(teamNamer);
	 //third item in the grid
	 JPanel leader=new JPanel();
	 leader.setLayout(new FlowLayout());
	 JButton leaderboardButton=new JButton("LeaderBoard");
	 leader.add(leaderboardButton);
	 title.add(leader);
	 leaderboardButton.addActionListener(this);
	 cp.add(title);
	 
	 
        
	   //set main setting of CP
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close button clicked
	   setTitle("Draft Queens"); // "this" JFrame sets title
	   setSize(1000, 500);         // "this" JFrame sets initial size
	   setVisible(true);    
	}
	
	
	public void initDraft()
	{
	
	 //this is initial draft
    initialDraft=new JPanel();
     initialDraft.setLayout(new GridLayout(2,2));
     JTabbedPane tabs =new JTabbedPane();
     
     //tab1
     
     JPanel hTab=new JPanel();
     hTab.setLayout(new GridLayout(1,1));
     //this nextpart is for testing
     String[] input={"Positions","QB","RB","RB","WR","WR","WR","TE"};
     JList<String> cTeam=new JList<String>(input);
     hTab.add(cTeam);
     
     
     tabs.addTab("HUMAN TEAM", hTab);
     //AI one
     JPanel a1Tab=new JPanel();
     a1Tab.setLayout(new GridLayout(1,1));
    JList<String> a1Team=new JList<String>(input);
     a1Tab.add(a1Team);
      tabs.addTab("Ai1", a1Tab);
      
     //AI two
      JPanel a2Tab=new JPanel();
      a2Tab.setLayout(new GridLayout(1,1));
     JList<String> a2Team=new JList<String>(input);
      a2Tab.add(a2Team);
       tabs.addTab("Ai2", a2Tab);
     //AI three
       JPanel a3Tab=new JPanel();
       a3Tab.setLayout(new GridLayout(1,1));
      JList<String> a3Team=new JList<String>(input);
       a3Tab.add(a3Team);
        tabs.addTab("Ai3", a3Tab);
      //AI four
        JPanel a4Tab=new JPanel();
        a4Tab.setLayout(new GridLayout(1,1));
       JList<String> a4Team=new JList<String>(input);
        a4Tab.add(a4Team);
         tabs.addTab("Ai4", a4Tab);
       //AI five
         JPanel a5Tab=new JPanel();
         a5Tab.setLayout(new GridLayout(1,1));
        JList<String> a5Team=new JList<String>(input);
         a5Tab.add(a5Team);
          tabs.addTab("Ai5", a5Tab);
        //AI six
          JPanel a6Tab=new JPanel();
          a6Tab.setLayout(new GridLayout(1,1));
         JList<String> a6Team=new JList<String>(input);
          a6Tab.add(a6Team);
           tabs.addTab("Ai6", a6Tab);
         //AI seven
           JPanel a7Tab=new JPanel();
           a7Tab.setLayout(new GridLayout(1,1));
          JList<String> a7Team=new JList<String>(input);
           a7Tab.add(a7Team);
            tabs.addTab("Ai7", a7Tab);
      
      
      
     
     ArrayList<String> temp=DBConnection.getAllPlayers(conn);
     String[] allPlayers =temp.toArray(new String[temp.size()]);
     JList<String> JallPlayers=new JList<String>(allPlayers);
     JScrollPane JscrollPlayers=new JScrollPane(JallPlayers);
     JPanel aPlayers= new JPanel();
     aPlayers.setLayout(new GridLayout());
     aPlayers.add(JscrollPlayers);
     
     tabs.addTab("Players", aPlayers);
     initialDraft.add(tabs);
     initialDraft.add(new JLabel("grid 2"));
     
     
     JPanel adder=new JPanel();
     adder.setLayout(new FlowLayout());
     adder.add(new JLabel("Add"));
     playerAdd.setEditable(false);
     JButton titleButton= new JButton("Confirm");
     adder.add(playerAdd);
     adder.add(titleButton);
     adder.add(new JLabel("Turn:"));
     teamName.setEditable(false);
     adder.add(teamName);
     adder.add(new JLabel("Round"));
     
     
     
     initialDraft.add(adder);

     initialDraft.add(new JLabel("remaining player positions"));
        cpid.add(initialDraft);
		
	}
	
	// All methods we need to overide so they do correct function
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		DBConnection.close(conn);
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DBConnection.initTeam(conn,teamName.getText());
		this.setContentPane(cpid);
		this.repaint();
	}

}
