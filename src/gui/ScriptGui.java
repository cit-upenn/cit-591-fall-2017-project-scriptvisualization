package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import script.Persona;
import script.Script;
import script.ScriptReader;
import script.ScriptScraper;

import java.awt.CardLayout;
import javax.swing.JPanel;
 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

 

public class ScriptGui {

	ScriptScraper ss = new ScriptScraper();
	ScriptReader sr = new ScriptReader();
	private JFrame frame;
	private JTextField searchBox;
	private JTextField movieTag;
	 

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
		
		//search page
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
		
		//choose page
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
		movieButton1.setVisible(false);
		
		JButton movieButton2 = new JButton("New button");
		movieButton2.setBounds(316, 338, 117, 29);
		choose.add(movieButton2);
		movieButton2.setVisible(false);
		
		JButton movieButton3 = new JButton("New button");
		movieButton3.setBounds(535, 338, 117, 29);
		choose.add(movieButton3);
		movieButton3.setVisible(false);
		
		//graph page
		JPanel graph = new JPanel();
		frame.getContentPane().add(graph, "name_20177380893280");
		graph.setLayout(null);
		
		JLabel moviePost = new JLabel(" ");
		moviePost.setBounds(609, 21, 117, 159);
		graph.add(moviePost);
		
		JLabel p2 = new JLabel("");
		p2.setBounds(121, 243, 61, 67);
		graph.add(p2);
		
		movieTag = new JTextField();
		movieTag.setColumns(10);
		movieTag.setBounds(588, 194, 130, 165);
		graph.add(movieTag);
		
		JLabel p3 = new JLabel("");
		p3.setBounds(377, 243, 61, 67);
		graph.add(p3);
		
		JLabel p1 = new JLabel(" ");
		p1.setBounds(243, 51, 61, 67);
		graph.add(p1);
		
		JButton backButton2 = new JButton("Back");
		
		backButton2.setBounds(609, 455, 117, 29);
		graph.add(backButton2);
		
		JButton BackButton = new JButton("Back");
		BackButton.setBounds(316, 443, 117, 29);
		choose.add(BackButton);
		
		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(moviePost1);
		labels.add(moviePost2);
		labels.add(moviePost3);
		
		ArrayList<JButton> movieButtons = new ArrayList<>();
		movieButtons.add(movieButton1);
		movieButtons.add(movieButton2);
		movieButtons.add(movieButton3);
		
		ArrayList<JLabel> characters = new ArrayList<>();
		characters.add(p1);
		characters.add(p2);
		characters.add(p3);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptScraper ss = new ScriptScraper();
				HashMap<String, BufferedImage> posts = new HashMap<>();
				try {
					ss.getMoviesFromSearchKey(searchBox.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				posts = ss.getMoviePosts();
				String[] movieNames = new String[posts.keySet().size()];
				posts.keySet().toArray(movieNames);
					for(int i = 0; i < Math.min(movieNames.length, 3); i++) {
						JLabel label = labels.get(i);
						String movieName = movieNames[i];
						
						movieButtons.get(i).setText(movieName);
						movieButtons.get(i).setVisible(true);
						Image post = posts.get(movieName).getScaledInstance(label.getWidth()	, label.getHeight(), Image.SCALE_SMOOTH);
						 
						label.setIcon(new ImageIcon(post));
					}
					 
					CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
					cardLayout.next(frame.getContentPane());
				 
			}
		});
		
		movieButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptReader sr = new ScriptReader();
				String url = ss.getMovieList().get(movieButton1.getText());
				Script script = new Script();
				try {
					script = sr.readScript(ScriptScraper.scrapeScript(url), movieButton1.getText());
				} catch (IOException | GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				moviePost.setIcon(labels.get(0).getIcon());
				ArrayList<Persona> mainCharacters = script.getMainCharacters();
				for(int i = 0; i < 3; i++) {
					JLabel label = characters.get(i);
					Image personalImage = mainCharacters.get(i).getImage().getScaledInstance(label.getWidth()	, label.getHeight(), Image.SCALE_SMOOTH);
					label.setIcon(new ImageIcon(personalImage));
				}
				movieTag.setText(script.getNaturalLangUnderstanding().keySet().toString());
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				
			}
		});
		
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.first(frame.getContentPane());
			}
		});
		
		
		backButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.first(frame.getContentPane());
				
			}
		});
		
		
	}
}
