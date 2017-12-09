package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ScriptGui {

	private JFrame frame;
	private JTextField searchBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptGui window = new ScriptGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScriptGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 748, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel search = new JPanel();
		frame.getContentPane().add(search, "name_20161426369847");
		search.setLayout(null);
		
		searchBox = new JTextField();
		searchBox.setText("Search  for Movie Script");
		searchBox.setBounds(121, 222, 472, 38);
		search.add(searchBox);
		searchBox.setColumns(10);
		
		JButton searchButton = new JButton("ScriptGo");
		searchButton.setBounds(292, 306, 117, 29);
		search.add(searchButton);
		
		JLabel searchLabel = new JLabel("");
		Image searchImg = frame.getToolkit().getImage("image/search-small.png");
		searchLabel.setIcon(new ImageIcon(searchImg));
		searchLabel.setBounds(292, 97, 117, 93);
		search.add(searchLabel);
		
		JPanel choose = new JPanel();
		frame.getContentPane().add(choose, "name_20169639935065");
		choose.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JPanel graph = new JPanel();
		frame.getContentPane().add(graph, "name_20177380893280");
		graph.setLayout(new BorderLayout(0, 0));
	}

}
