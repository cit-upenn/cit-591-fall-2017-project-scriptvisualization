package moviegui;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame {
	private JTextField textField;
	private String s;
	
	public MyFrame() {
		setTitle("My Empty Frame");
		setSize(300,200); // default size is 0,0
		setLocation(10,200); // default is 0,0 (top left corner)
		
		JButton btnSearch = new JButton("Search");
	
	 
		getContentPane().add(btnSearch, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setBackground(Color.GRAY);
		getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.EAST);
		
		
		
	  }
	
	 
	 
	  public static void main(String[] args) {
	    JFrame f = new MyFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	    f.setVisible(true);
	 
	  }
}
