import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptScraper ss = new ScriptScraper();
			System.out.println(ss.scrapeScript("http://www.imsdb.com/scripts/La-La-Land.html"));
			ss.printMovieList(ss.getMoviesFromSearchKey(Genre.MUSICAL));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
	}

}
