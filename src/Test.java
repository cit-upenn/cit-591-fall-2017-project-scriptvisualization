import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptScraper ss = new ScriptScraper("http://www.imsdb.com/scripts/La-La-Land.html");
			ss.writeScriptToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
