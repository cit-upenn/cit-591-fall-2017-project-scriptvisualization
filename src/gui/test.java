package gui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String line = "Hello. My name is Inigo Montoya. "+ "You killed my father. Prepare to die.";
		// not valid, since that constructor is private//         Pattern p = new Pattern();P
		Pattern pattern = Pattern.compile("Hello. My name is (.*). "+ "(You (killed) my (.*).) Prepare to (die).");
		Matcher match = pattern.matcher(line);if (match.matches()) {for (int i = 0; i <= match.groupCount(); i++) {System.out.println("Group " + i + " : " + match.group(i));}} else {System.out.println("No Match found");}}
	}


