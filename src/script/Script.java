package script;

import java.awt.Image;
import java.util.Set;

/**
 * This class represents a script, a script has content and charaters
 * @author yueyin
 *
 */
public class Script {
	
	private String name;
	private String content;
	private Relationships relationgraph;
	private Image post;
	private String[] tags;
	private Set<Persona> mainCharacters;
	
	
	
	public Script(String name, String content, Relationships relationgraph, Image post, String[] tags,
			Set<Persona> mainCharacters) {
		super();
		this.name = name;
		this.content = content;
		this.relationgraph = relationgraph;
		this.post = post;
		this.tags = tags;
		this.mainCharacters = mainCharacters;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
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
	 * @param relationgraph the relationgraph to set
	 */
	public void setRelationgraph(Relationships relationgraph) {
		this.relationgraph = relationgraph;
	}
	/**
	 * @return the post
	 */
	public Image getPost() {
		return post;
	}
	/**
	 * @param post the post to set
	 */
	public void setPost(Image post) {
		this.post = post;
	}
	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public Set<Persona> getMainCharacters() {
		return mainCharacters;
	}
	
	

}
