package script;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException{
		ScriptScraper ss = new ScriptScraper();
		ss.getMoviesFromGenre(Genre.SCIFI);
		System.out.println(ss.getMovieList().toString());
 
		 
	}

}
