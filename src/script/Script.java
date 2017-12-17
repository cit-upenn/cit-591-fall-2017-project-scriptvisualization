package script;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

/**
 * This class represents a script
 * A script has all information that is needed for webpage visualization
 * @author yueyin, syou
 *
 */
public class Script {

	private String name;
	private String content;
	private Relationships relationgraph;
	private BufferedImage poster;

	private ArrayList<Persona> mainCharacters;
	private AnalysisResults categories;
	private AnalysisResults keywords;
	private double sentiment;

	/**
	 * constructor without param
	 */
	public Script() {

	}

	/**
	 * constructor with fields
	 * 
	 * @param name
	 *            name of the script
	 * @param content
	 *            all written words of the script
	 * @param relationgraph
	 *            graph of relationships between characters
	 * @param poster
	 *            poster of the film
	 * @param mainCharacters
	 *            top ten main characters of the script
	 * @param keywords
	 *            keywords of the script
	 * @param categories
	 *            three categories of the script
	 * @param sentiment
	 *            overall sentiment of the script
	 */
	public Script(String name, String content, Relationships relationgraph, BufferedImage poster,
			ArrayList<Persona> mainCharacters, AnalysisResults keywords, AnalysisResults categories, double sentiment) {
		super();
		this.name = name;
		this.content = content;
		this.relationgraph = relationgraph;
		this.poster = poster;

		this.mainCharacters = mainCharacters;
		this.keywords = keywords;
		this.categories = categories;
		this.sentiment = sentiment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Relationships getRelationgraph() {
		return relationgraph;
	}


	public void setRelationgraph(Relationships relationgraph) {
		this.relationgraph = relationgraph;
	}


	public BufferedImage getPost() {
		return poster;
	}


	public void setPost(BufferedImage post) {
		this.poster = post;
	}

	public ArrayList<Persona> getMainCharacters() {
		return mainCharacters;
	}

	public void setMainCharacters(ArrayList<Persona> mainCharacters) {
		this.mainCharacters = mainCharacters;
	}

	public AnalysisResults getKeywords() {
		return keywords;
	}

	public void setKeywords(AnalysisResults keywords) {
		this.keywords = keywords;
	}

	public AnalysisResults getCategories() {
		return categories;
	}

	public void setCategories(AnalysisResults categories) {
		this.categories = categories;
	}

	public double getSentiment() {
		return sentiment;
	}

	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}

}
