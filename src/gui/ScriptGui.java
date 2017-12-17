package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import script.DataPrinter;
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

/**
 * This is a gui class. gui interacts with users, gets search key from user and output three movies that match the search key 
 * for users to choose from. Then backend analyzes the chosen script. User can press visualize button to redirect to website with
 * all information
 * @author yueyin
 *
 */

public class ScriptGui {

	ScriptScraper ss = new ScriptScraper();
	ScriptReader sr = new ScriptReader();
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
		
		//frame
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
		comboBox.setMaximumRowCount(40);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Genre", "Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Family", "Fantasy", "FilmNoir", "Horror", "Musical", "Mystery", "Romance", "Scifi", "Short", "Thriller", "War", "Western"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(277, 377, 104, 34);
		search.add(comboBox);
		
		JLabel mianPagegif = new JLabel("");
		mianPagegif.setIcon(new ImageIcon("image/happycat.gif"));
		mianPagegif.setBounds(251, 32, 191, 190);
		search.add(mianPagegif);
		 
		
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
		
		
		JButton BackButton = new JButton("Back");
		BackButton.setBounds(281, 398, 117, 29);
		choose.add(BackButton);

			
		//result page
		JPanel result = new JPanel();
		frame.getContentPane().add(result, "name_20177380893280");
		result.setLayout(null);
		
		
		JButton back2 = new JButton("Back");
		
		back2.setBounds(93, 379, 117, 29);
		result.add(back2);
		
		JButton Visualize = new JButton("Visualize");
	
		Visualize.setBounds(424, 379, 117, 29);
		result.add(Visualize);
		
		
		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(moviePost1);
		labels.add(moviePost2);
		labels.add(moviePost3);
		
		ArrayList<JButton> movieButtons = new ArrayList<>();
		movieButtons.add(movieButton1);
		movieButtons.add(movieButton2);
		movieButtons.add(movieButton3);
		
		JLabel loadingGif = new JLabel("");
		loadingGif.setIcon(new ImageIcon("image/loading.gif"));
		loadingGif.setBounds(97, 6, 489, 329);
		choose.add(loadingGif);
		
		loadingGif.setVisible(false);
		
		JLabel done = new JLabel("");
		done.setIcon(new ImageIcon("image/script6.png"));
		done.setBounds(213, 87, 241, 217);
		result.add(done);
		
		
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
				loadingGif.setVisible(true);
				//analyze(movieButton1.getText());
				loadingGif.setVisible(false);
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				
			
				
				
			}
		});
		
		movieButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadingGif.setVisible(true);
				analyze(movieButton2.getText());
				loadingGif.setVisible(false);
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());

				
				
				
			}
		});
		
		movieButton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadingGif.setVisible(true);
				analyze(movieButton3.getText());
				loadingGif.setVisible(false);
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				
				
			}
		});
		
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.first(frame.getContentPane());
				for(int i=0;i<3;i++){
				     labels.get(i).setIcon(null);
				     labels.get(i).revalidate();
				     movieButtons.get(i).setText(null);
				     movieButtons.get(i).revalidate();
				     movieButtons.get(i).setVisible(false);

				}
				
			}
		});
		
		back2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				for(int i=0;i<3;i++){
				     labels.get(i).setIcon(null);
				     labels.get(i).revalidate();
				     movieButtons.get(i).setText(null);
				     movieButtons.get(i).revalidate();
				     movieButtons.get(i).setVisible(false);

				}
			}
		});
		
		Visualize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Desktop.isDesktopSupported()) {
					String absolutePath = FileSystems.getDefault().getPath("data/script.html").normalize().toAbsolutePath().toString();
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
	
	private void analyze(String moviename) {
		sr = new ScriptReader();
		String url = ss.getMovieList().get(moviename);
		
		try {
			Script script = sr.readScript(ScriptScraper.scrapeScript(url), moviename);
			DataPrinter dp = new DataPrinter();
			
			dp.printPersonality(script);
					
			dp.printRelation(script);
			
			dp.printKeywords(script);
			
			dp.printMainPhotos(script);
			
			dp.printTimeLine(script);
			
		} catch (IOException | GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
