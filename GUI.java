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


public class GUI extends JFrame implements ActionListener, WindowListener, MouseListener {
	private JTextField teamName =new JTextField("", 20);// 20 is variable
	private JTextField playerAdd=new JTextField("", 20);
	
	public GUI()
	{
		//making player name not eitable
		teamName.setEditable(true);
		
		
		
		
		
		 Container cp = getContentPane();
	      cp.setLayout(new GridLayout(1,1,1,1));
     JPanel initialDraft=new JPanel();
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
      
      
      
     tabs.addTab("Players", new JPanel());
     
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
     adder.add(teamName);
     adder.add(new JLabel("Round"));
     
     
     
     initialDraft.add(adder);

     initialDraft.add(new JLabel("remaining player positions"));
     cp.add(initialDraft);
        
        
	   //set main setting of CP
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close button clicked
	   setTitle("Draft Queens"); // "this" JFrame sets title
	   setSize(1000, 500);         // "this" JFrame sets initial size
	   setVisible(true);    
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
