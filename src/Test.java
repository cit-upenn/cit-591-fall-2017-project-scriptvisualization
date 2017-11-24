import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptScraper ss = new ScriptScraper("http://www.imsdb.com/transcripts/South-Park-South-Park-Is-Gay!.html");
			ss.writeScriptToFile();
			MovieLists ml = new MovieLists("back");
			ml.printMovieList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
