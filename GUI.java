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
	private Container cpid;
	private JPanel title;
	private JPanel initialDraft;
	private JButton ConfirmButton;
	public Connection conn;
	public Statement statement = null;
	public ResultSet results = null;
	private JList<String> JQBPlayers;
	private JList<String> JRBPlayers;
	private JList<String> JWRPlayers;
	private JList<String> JTEPlayers;
	private JList<String> JAllPlayers;
	private JTabbedPane posTabs;
	private JTabbedPane tabs;
	private ButtonActionListener ButtonListener = new ButtonActionListener();
	private JList<String> cTeam;
	private int rbleft=2;
	private int wrleft=3;
	
	

	public GUI() throws Exception
	{
	
	conn = DBConnection.getConnection();
	 cp = getContentPane();
	 cp.setLayout(new GridLayout(1,1,1,1));
	 
	 //resets players
	 DBConnection.resetPlayer(conn);
	 
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
     tabs =new JTabbedPane();
     
     //tab1
     
     JPanel hTab=new JPanel();
     hTab.setLayout(new GridLayout(1,1));
     //this nextpart is for testing
     ArrayList<String> tempp=DBConnection.getAllTeamPlayers(conn,1);
     
     String[] input=tempp.toArray(new String[tempp.size()]);
     cTeam=new JList<String>(input);
     hTab.add(cTeam);
     
     
     tabs.addTab(teamName.getText(), hTab);
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
      
      
      //PLayer tab
     //tab 1 of Players Tab
     ArrayList<String> temp=DBConnection.getPosPlayers(conn,true,false,false,false,1);
     String[] QBPlayers =temp.toArray(new String[temp.size()]);
     JQBPlayers=new JList<String>(QBPlayers);
     JQBPlayers.addMouseListener(this);
     JScrollPane JscrollQBPlayers=new JScrollPane(JQBPlayers);
     posTabs=new JTabbedPane();
     posTabs.add("QB",JscrollQBPlayers);
     //tab 2
     temp=DBConnection.getPosPlayers(conn,false,true,false,false,1);
     String[] RBPlayers =temp.toArray(new String[temp.size()]);
     JRBPlayers=new JList<String>(RBPlayers);
     JRBPlayers.addMouseListener(this);
     JScrollPane JscrollRBPlayers=new JScrollPane(JRBPlayers);
     posTabs.add("RB",JscrollRBPlayers);
     //tab 3
     temp=DBConnection.getPosPlayers(conn,false,false,true,false,1);
     String[] WRPlayers =temp.toArray(new String[temp.size()]);
     JWRPlayers=new JList<String>(WRPlayers);
     JWRPlayers.addMouseListener(this);
     JScrollPane JscrollWRPlayers=new JScrollPane(JWRPlayers);
     posTabs.add("WR",JscrollWRPlayers);
     //tab 4
     temp=DBConnection.getPosPlayers(conn,false,false,false,true,1);
     String[] TEPlayers =temp.toArray(new String[temp.size()]);
     JTEPlayers=new JList<String>(TEPlayers);
     JTEPlayers.addMouseListener(this);
     JScrollPane JscrollTEPlayers=new JScrollPane(JTEPlayers);
     posTabs.add("TE",JscrollTEPlayers);
     //tab 5
     temp=DBConnection.getPosPlayers(conn,true,true,true,true,4);
     String[] AllPlayers =temp.toArray(new String[temp.size()]);
     JAllPlayers=new JList<String>(AllPlayers);
     JAllPlayers.addMouseListener(this);
     JScrollPane JscrollAllPlayers=new JScrollPane(JAllPlayers);
     posTabs.add("ALL",JscrollAllPlayers);
     
     
     
     JPanel aPlayers= new JPanel();
     aPlayers.setLayout(new GridLayout());
     aPlayers.add(posTabs);
     
     tabs.addTab("Players", aPlayers);
     initialDraft.add(tabs);
     initialDraft.add(new JLabel("grid 2"));
     
     JPanel bot=new JPanel();
     bot.setLayout(new GridLayout(2,1));
     JPanel adder=new JPanel();
     adder.setLayout(new FlowLayout());
     adder.add(new JLabel("Add"));
     playerAdd.setEditable(false);
     //Confirm Button
     ConfirmButton= new JButton("Confirm");
     ConfirmButton.addActionListener(ButtonListener);
     adder.add(playerAdd);
     adder.add(ConfirmButton);
     bot.add(adder);
     JPanel turn =new JPanel();
     turn.setLayout(new FlowLayout());
     turn.add(new JLabel("Turn:"));
     teamName.setEditable(false);
     turn.add(teamName);
     turn.add(new JLabel("Round"));
     bot.add(turn);
     
     initialDraft.add(bot);

     initialDraft.add(new JLabel("remaining player positions"));
        cpid.add(initialDraft);
		
	}
	
	// All methods we need to overide so they do correct function
	public void mouseClicked(MouseEvent arg0) {
		if(tabs.getSelectedIndex()==0)
		{
			ArrayList<String> tempp=DBConnection.getAllTeamPlayers(conn,1);
			 String[] input=tempp.toArray(new String[tempp.size()]);
			 cTeam=new JList<String>(input);
			 this.repaint();
		}	
		else if(tabs.getSelectedIndex()==8)
		{
			if(posTabs.getSelectedIndex()==0)
				playerAdd.setText((String)JQBPlayers.getSelectedValue());
			else if(posTabs.getSelectedIndex()==1)
				playerAdd.setText((String)JRBPlayers.getSelectedValue());
			else if(posTabs.getSelectedIndex()==2)
				playerAdd.setText((String)JWRPlayers.getSelectedValue());
			else if(posTabs.getSelectedIndex()==3)
				playerAdd.setText((String)JTEPlayers.getSelectedValue());
			else if(posTabs.getSelectedIndex()==4)
				playerAdd.setText((String)JAllPlayers.getSelectedValue());
		}
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
		cpid=getContentPane();
		initDraft();
		cpid.remove(title);
		this.setContentPane(cpid);
		this.repaint();
	}
	private class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			Object source=arg0.getSource();
			if(source==ConfirmButton)
			{
				String temp=playerAdd.getText();
				String playerId=temp.substring(0,7);
				String pos=temp.substring(8,10);
				if(pos.equals("RB") && rbleft>0)
				{
				DBConnection.updatePlayer(conn,playerId,1);
				DBConnection.updateTeam(conn,pos+rbleft,playerId,1);
				rbleft--;
				}
				if(pos.equals("WR") && wrleft>0)
				{
				DBConnection.updatePlayer(conn,playerId,1);
				DBConnection.updateTeam(conn,pos+wrleft,playerId,1);
				wrleft--;
				}
				else
				{
				DBConnection.updatePlayer(conn,playerId,1);
				DBConnection.updateTeam(conn,pos,playerId,1);	
				}
				
			}
		}
	}
}
