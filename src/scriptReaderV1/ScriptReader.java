package scriptReaderV1;
/**
 * This class reads in scripts.
 * Gets names of key roles and their occurrences in the script.
 * Gets characters' lines.
 * @author syou
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptReader {
	/**
	 * Constructor without parameter
	 */
	public ScriptReader() {

	}

	/**
	 * This method gets top ten most important character names and their occurrences in the script
	 * @param inputFile input script txt file
	 * @return TreeMap, key - character name, value - number of occurrences
	 * @throws IOException
	 */
	public TreeMap<String, Integer> getNames(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		TreeMap<String, Integer> majorCharacters = new TreeMap<String, Integer>();
		ArrayList<String> al = new ArrayList<String>();

		char[] chs = new char[1024];
		int len = 0;
		while ((len = br.read(chs)) != -1) {
			sb.append(new String(chs, 0, len));
		}
		// System.out.println(sb);
		br.close();

		Pattern pTag = Pattern.compile("<b>\\s+.+\\s+</b>");
		Matcher mTag = pTag.matcher(sb.toString());
		while (mTag.find()) {
			// System.out.println(mTag.group().replaceAll("<b>", "").replaceAll("</b>",
			// "").trim());
			al.add(mTag.group().replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("\\(.+\\)", "").trim());
		}

		for (String s : al) {
			if (!s.contains("DISSOLVE")) {
				if (hm.get(s) == null) {
					hm.put(s, 1);
				} else {
					hm.put(s, hm.get(s) + 1);
				}
			}
		}

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// System.out.println(list);
		// System.out.println("Top 10 most important characters in " +
		// inputFile.replace("-", " ").replaceAll(".txt", "")
		// + " :");
		for (int i = 0; i < 10; i++) {
			majorCharacters.put(list.get(i).getKey(), list.get(i).getValue());
			// System.out.println(list.get(i).getKey() + " : " + list.get(i).getValue() + "
			// occurences");
		}
		// System.out.println("======");
		for (String s : majorCharacters.keySet()) {
			// System.out.println(s + " : "+ majorCharacters.get(s) + " occurences");
		}
		return majorCharacters;

	}

	/**
	 * This method gets all lines of a character in a script 
	 * @param inputFile input script txt file
	 * @return HashMap, key - character name, value - character lines
	 * @throws IOException
	 */
	public HashMap<String, String> getLines(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> roleLines = new HashMap<String, String>();
		ArrayList<String> keyRoles = new ArrayList<String>();

		char[] chs = new char[1024];
		int len = 0;
		while ((len = br.read(chs)) != -1) {
			sb.append(new String(chs, 0, len));
		}
		
		

		// Pattern pTag = Pattern.compile("<b><b>");
		String[] lines = sb.toString().split("<b>");
		
//		PrintWriter pw = new PrintWriter("testLines.txt");
//		for (String s: lines) {
//		pw.println(s);
//		}
//		pw.close();
		
		
		for (String s : lines) {
//			 System.out.println(s);
//			 System.out.println("======");
			for (String name : getNames(inputFile).keySet()) {
				if (s.trim().startsWith(name)&&!s.contains("'S")) {
					if (roleLines.get(name) == null) {
						roleLines.put(name, s.replaceAll(name, "").replaceAll("</b>", "").trim());
					} else {
						roleLines.put(name,
								roleLines.get(name) + "\n" + s.replaceAll(name, "").replaceAll("</b>", "").trim());
					}
				}
			}
		}
		

//		System.out.println(roleLines);
		System.out.println(roleLines.get("DANNY"));
		return roleLines;
	}
}
