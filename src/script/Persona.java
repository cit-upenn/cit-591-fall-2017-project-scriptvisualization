package script;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a character in a script
 * 
 * @author yueyin
 *
 */
public class Persona implements Comparable<Persona> {

	private int occurrence; // occurrence of the character in the script
	private String name; // name of the character
	private ArrayList<String> lines; // all lines belong to this character
	private HashMap<String, Double> personality;
	private BufferedImage personaImage;
	private ArrayList<HashMap<String, HashMap<String, Double>>> emotionTimeline;
	private ArrayList<HashMap<String, HashMap<String, Double>>> langToneTimeline;

	public Persona(String name) {
		// TODO Auto-generated constructor stub
		lines = new ArrayList<>();
		this.name = name;
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public String getName() {
		return name;
	}

	public int getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(int number) {
		this.occurrence = number;
	}

	public String toString() {
//		String person = "name = " + name + "\n" + " ocurrance = " + occurrence + "\n";
//		for (String s : lines) {
//			person += s;
//		}
		String person = name;
		return person;
	}

	@Override
	public int compareTo(Persona o) {
		// TODO Auto-generated method stub
		int num = o.occurrence - this.occurrence;
		int num2 = num == 0 ? this.name.compareTo(o.name) : num;
		return num2;
	}

	public void setImage(BufferedImage image) {
		personaImage = image;
	}

	public Image getImage() {
		return personaImage;
	}

	public ArrayList<HashMap<String, HashMap<String, Double>>> getEmotionTimeline() {
		return emotionTimeline;
	}

	public void setEmotionTimeline(ArrayList<HashMap<String, HashMap<String, Double>>> emotionTimeline) {
		this.emotionTimeline = emotionTimeline;
	}

	public ArrayList<HashMap<String, HashMap<String, Double>>> getLangToneTimeline() {
		return langToneTimeline;
	}

	public void setLangToneTimeline(ArrayList<HashMap<String, HashMap<String, Double>>> langToneTimeline) {
		this.langToneTimeline = langToneTimeline;
	}

}
