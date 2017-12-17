package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import script.Genre;
import script.Persona;
import script.Script;
import script.ScriptReader;
import script.ScriptScraper;

import java.awt.CardLayout;
import java.awt.Desktop;

import javax.swing.JPanel;
 
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

 

public class ScriptGui {

	ScriptScraper ss = new ScriptScraper();
	ScriptReader sr = new ScriptReader();
	private JFrame frame;
	private JTextField searchBox;
	private JTextField textField;
	 

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
		frame.setBounds(100, 100, 678, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		//search page
		JPanel search = new JPanel();
		frame.getContentPane().add(search, "name_20161426369847");
		search.setLayout(null);
		
		searchBox = new JTextField();
		searchBox.setText("                         Search  for Movie Script");
		searchBox.setBounds(151, 234, 352, 44);
		search.add(searchBox);
		searchBox.setColumns(10);
		
		JButton searchButton = new JButton("ScriptGo");
		searchButton.setBounds(264, 313, 129, 35);
		search.add(searchButton);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBox.setMaximumRowCount(40);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Genre", "Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Family", "Fantasy", "FilmNoir", "Horror", "Musical", "Mystery", "Romance", "Scifi", "Short", "Thriller", "War", "Western"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(277, 377, 104, 34);
		search.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image/happycat.gif"));
		lblNewLabel.setBounds(251, 32, 191, 190);
		search.add(lblNewLabel);
		Image searchImg = frame.getToolkit().getImage("image/search-small.png");
		
		//choose page
		JPanel choose = new JPanel();
		frame.getContentPane().add(choose, "name_20169639935065");
		choose.setLayout(null);
		
		JLabel moviePost1 = new JLabel(" ");
		moviePost1.setBounds(51, 72, 172, 235);
		choose.add(moviePost1);
		
		JLabel moviePost2 = new JLabel(" ");
		moviePost2.setBounds(281, 72, 172, 235);
		choose.add(moviePost2);
		
		JLabel moviePost3 = new JLabel(" ");
		moviePost3.setBounds(500, 72, 172, 235);
		choose.add(moviePost3);
		
		JButton movieButton1 = new JButton("Click");
		
		movieButton1.setBounds(51, 338, 172, 29);
		choose.add(movieButton1);
		movieButton1.setVisible(false);
		
		JButton movieButton2 = new JButton("Click");
		movieButton2.setBounds(281, 338, 172, 29);
		choose.add(movieButton2);
		movieButton2.setVisible(false);
		
		JButton movieButton3 = new JButton("Click");
		movieButton3.setBounds(500, 338, 172, 29);
		choose.add(movieButton3);
		movieButton3.setVisible(false);

			
		//graph page
		JPanel graph = new JPanel();
		frame.getContentPane().add(graph, "name_20177380893280");
		graph.setLayout(null);
		
		
		JButton btnGraphgo = new JButton("Back");
		btnGraphgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
			}
		});
		btnGraphgo.setBounds(93, 379, 117, 29);
		graph.add(btnGraphgo);
		
		JButton Visualize = new JButton("Visualize");
	
		Visualize.setBounds(424, 379, 117, 29);
		graph.add(Visualize);
		
		/*
		JLabel moviePost = new JLabel(" ");
		moviePost.setBounds(609, 21, 117, 159);
		graph.add(moviePost);
		*/
		ArrayList<JLabel> pp=new ArrayList<>();
		for(int i=0;i<3;i++){
			
			JLabel ppp=new JLabel("");
			if(i<5){
				ppp.setBounds(25+i*80,30,61,67);
			}
			else{
				ppp.setBounds(25+(i-5)*80,530,61,67);
			}
			graph.add(ppp);
			pp.add(ppp);
			
		}
		
	
		
		JButton BackButton = new JButton("Back");
		BackButton.setBounds(281, 398, 117, 29);
		choose.add(BackButton);
		
		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(moviePost1);
		labels.add(moviePost2);
		labels.add(moviePost3);
		
		ArrayList<JButton> movieButtons = new ArrayList<>();
		movieButtons.add(movieButton1);
		movieButtons.add(movieButton2);
		movieButtons.add(movieButton3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("image/loading.gif"));
		lblNewLabel_1.setBounds(97, 6, 489, 329);
		graph.add(lblNewLabel_1);
		
		lblNewLabel_1.setVisible(true);
		
		
		
		ArrayList<JLabel> characters = new ArrayList<>();
		//characters.add(p1);
		//characters.add(p2);
		//characters.add(p3);
		for(int i=0;i<3;i++){
			characters.add(pp.get(i));
		}
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ss = new ScriptScraper();
				HashMap<String, BufferedImage> posts = new HashMap<>();
				String x = comboBox.getSelectedItem().toString();
				
				try {
					if(x=="Genre"){
						ss.getMoviesFromSearchKey(searchBox.getText());	
					}
					else{
						ss.getMoviesFromGenre(Genre.valueOf(x.toUpperCase()));
						//System.out.println(x);
					}
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
				/*sr = new ScriptReader();
				String url = ss.getMovieList().get(movieButton1.getText());
				Script script = new Script();
				try {
					
					script = sr.readScript(ScriptScraper.scrapeScript(url), movieButton1.getText());
 
					JLabel lblNewLabel_1 = new JLabel("");
					lblNewLabel_1.setIcon(new ImageIcon("/Users/yichaoli/Documents/study/MCIT_1/591project/image/ajax-loader.gif"));
					lblNewLabel_1.setBounds(140, 108, 176, 87);
					choose.add(lblNewLabel_1);
 
				} catch (IOException | GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				moviePost.setIcon(labels.get(0).getIcon());
				
				ArrayList<Persona> mainCharacters = script.getMainCharacters();
				for(int i = 0; i < 3; i++) {
					JLabel label = characters.get(i);
					System.out.println(mainCharacters.get(i).getName());
					//Image personalImage = mainCharacters.get(i).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
					textField = new JTextField();
					if(i<5) textField.setBounds(25+i*80,100,61,20);
					else{
						textField.setBounds(25+(i-5)*80,540,61,20);
					}
					textField.setText(mainCharacters.get(i).getName());
					graph.add(textField);
					//textField.setColumns(10);
					
					
					System.out.println(personalImage);
					label.setIcon(new ImageIcon(personalImage));
				}
				movieTag.setText(script.getNaturalLangUnderstanding().keySet().toString());*/
				
			
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				
				
			}
		});
		
		movieButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				
			}
		});
		
		movieButton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
			}
		});
		
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.first(frame.getContentPane());
				//moviePost1.setVisible(false);
				moviePost2.setVisible(false);
				moviePost3.setVisible(false);
				movieButton2.setVisible(false);
				movieButton3.setVisible(false);
			
				
			}
		});
		
		Visualize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel_1.setVisible(false);
				if(Desktop.isDesktopSupported()) {
					String absolutePath = FileSystems.getDefault().getPath("data/index.html").normalize().toAbsolutePath().toString();
						    try {
								Desktop.getDesktop().browse(new URI("file:///"+absolutePath));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
			}
		});
		
		
	}
}
