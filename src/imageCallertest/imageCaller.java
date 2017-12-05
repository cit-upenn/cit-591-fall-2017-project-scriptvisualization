package imageCallertest;
import java.io.IOException;
import java.security.GeneralSecurityException;

 
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
 

public class imageCaller {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
    	String searchQuery = "mia lala land"; 
        String cx = "016310474112609901486:dlk05n5m1fm"; //Your search engine

        //Instance Customsearch
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null) 
        					.setApplicationName("scriptVisualization")
                       .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyBsUA3Jt08xohiLevUttDAG5SYpg75kCdE")) 
                       .build();

        //Set search parameter
        Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx).setSearchType("image"); 

        //Execute search
        Search result = list.execute();
        if (result.getItems()!=null){
            for (Result ri : result.getItems()) {
                //Get title, link, body etc. from search
                System.out.println(ri.getLink());
            }
        }
    }
}