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
	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() 
	         {
					new GUI();
	         }}
		);

	}

	public GUI()
	{
		Container cp = getContentPane();
	      cp.setLayout(new GridLayout(1,1,1,1));
	      //this is where main GUI Goes
	      JPanel title=new JPanel();
	      title.setLayout(new FlowLayout());
	      title.add(new JLabel("Team Name"));
	      teamName.setEditable(true);
	      JButton titleButton= new JButton("Continue");
	      title.add(teamName);
	      title.add(titleButton);
	      cp.add(title);
	      titleButton.addActionListener(this);
	      teamName.addActionListener(this);
	      
	      
	      
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
