import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptScraper ss = new ScriptScraper("http://www.imsdb.com/scripts/Pearl-Harbor.html");
			ScriptScraper ss2 = new ScriptScraper("http://www.imsdb.com/scripts/La-La-Land.html");
			ScriptScraper ss3 = new ScriptScraper("http://www.imsdb.com/scripts/Citizen-Kane.html");
			ss.writeScriptToFile();
			ss2.writeScriptToFile();
			ss3.writeScriptToFile();
			//MovieLists ml = new MovieLists("land");
			//ml.printMovieList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
