package script;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

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
	private String personaImage;
	private HashMap<Integer, HashMap<String, Double>> emotionTimeline;
	private HashMap<String, HashMap<String, Double>> langToneTimeline;
	private Profile personality;

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

	public void setImage(String image) {
		personaImage = image;
	}
	
	public String getImage() {
		return personaImage;
	}

	public HashMap<Integer, HashMap<String, Double>> getEmotionTimeline() {
		return emotionTimeline;
	}

	public void setEmotionTimeline(HashMap<Integer, HashMap<String, Double>> emotionTimeline) {
		this.emotionTimeline = emotionTimeline;
	}

	public HashMap<String, HashMap<String, Double>> getLangToneTimeline() {
		return langToneTimeline;
	}

	public void setLangToneTimeline(HashMap<String, HashMap<String, Double>> langToneTimeline) {
		this.langToneTimeline = langToneTimeline;
	}

	public Profile getPersonality() {
		return personality;
	}

	public void setPersonality(Profile personality) {
		this.personality = personality;
	}



}
