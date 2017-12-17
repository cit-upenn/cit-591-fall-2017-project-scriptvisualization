package script;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

/**
 * This class represents a script, a script has content and charaters
 * 
 * @author yueyin
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
	

	public Script() {

	}

	public Script(String name, String content, Relationships relationgraph, BufferedImage post,
			ArrayList<Persona> mainCharacters, AnalysisResults keywords, AnalysisResults categories, double sentiment) {
		super();
		this.name = name;
		this.content = content;
		this.relationgraph = relationgraph;
		this.poster = post;

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

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the relationgraph
	 */
	public Relationships getRelationgraph() {
		return relationgraph;
	}

	/**
	 * @param relationgraph
	 *            the relationgraph to set
	 */
	public void setRelationgraph(Relationships relationgraph) {
		this.relationgraph = relationgraph;
	}

	/**
	 * @return the post
	 */
	public BufferedImage getPost() {
		return poster;
	}

	/**
	 * @param post
	 *            the post to set
	 */
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
