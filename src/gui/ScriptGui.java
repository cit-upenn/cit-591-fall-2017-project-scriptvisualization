package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import script.ScriptScraper;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

 

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
		choose.setLayout(null);
		
		JLabel moviePost1 = new JLabel(" ");
		moviePost1.setBounds(53, 72, 172, 235);
		choose.add(moviePost1);
		
		JLabel moviePost2 = new JLabel(" ");
		moviePost2.setBounds(285, 72, 172, 235);
		choose.add(moviePost2);
		
		JLabel moviePost3 = new JLabel(" ");
		moviePost3.setBounds(524, 72, 172, 235);
		choose.add(moviePost3);
		
		JButton movieButton1 = new JButton("New button");
		movieButton1.setBounds(77, 338, 117, 29);
		choose.add(movieButton1);
		
		JButton movieButton2 = new JButton("New button");
		movieButton2.setBounds(316, 338, 117, 29);
		choose.add(movieButton2);
		
		JButton movieButton3 = new JButton("New button");
		movieButton3.setBounds(535, 338, 117, 29);
		choose.add(movieButton3);
		
		
		JPanel graph = new JPanel();
		frame.getContentPane().add(graph, "name_20177380893280");
		graph.setLayout(new BorderLayout(0, 0));
		
		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(moviePost1);
		labels.add(moviePost2);
		labels.add(moviePost3);
		
		ArrayList<JButton> movieButtons = new ArrayList<>();
		movieButtons.add(movieButton1);
		movieButtons.add(movieButton2);
		movieButtons.add(movieButton3);
		
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.first(frame.getContentPane());
			}
		});
		BackButton.setBounds(316, 443, 117, 29);
		choose.add(BackButton);
		
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, BufferedImage> posts = new HashMap<>();
				try {
					posts = ScriptScraper.getMoviesPostsFromSearchKey(searchBox.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] movieNames = new String[posts.keySet().size()];
				posts.keySet().toArray(movieNames);
				 
				 
					for(int i = 0; i < Math.min(movieNames.length, 3); i++) {
						JLabel label = labels.get(i);
						String movieName = movieNames[i];
						 
						movieButtons.get(i).setText(movieName);
						Image post = posts.get(movieName).getScaledInstance(label.getWidth()	, label.getHeight(), Image.SCALE_SMOOTH);
						 
						label.setIcon(new ImageIcon(post));
					}
					 
					CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
					cardLayout.next(frame.getContentPane());
				 
			}
		});
	}
	
	 

}
