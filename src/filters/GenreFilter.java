package src.filters;

import src.db.MovieDatabase;

public class GenreFilter implements Filter{

    private String myGenre;

        public GenreFilter(String genre)
        {
            myGenre = genre;
        }

    @Override
    public boolean satisfies(String id) {
        String g = MovieDatabase.getGenres(id);
        return g.contains(myGenre);
    
    }
}
    
