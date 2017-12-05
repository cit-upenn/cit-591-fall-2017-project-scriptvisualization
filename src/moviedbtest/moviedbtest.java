 

import retrofit2.Call;

public class moviedbtest {
	public static void main(String[] args) {
		Tmdb tmdb = new Tmdb("edf1b9d248b7dee1398bb1159e9f19cc");
		MoviesService movieService = tmdb.moviesService();
		Call<Movie> call = movieService.summary(550);
		Movie movie = call.execute().body();
		Call<Trailers> callTrailers = movieService.trailers(550);
		Trailers trailers = callTrailers.execute().body();
	}
}
