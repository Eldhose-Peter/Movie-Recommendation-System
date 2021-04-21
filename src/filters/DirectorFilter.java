package src.filters;


import src.db.MovieDatabase;

public class DirectorFilter implements Filter{

    String myDirectors;

    public DirectorFilter(String directors)
    {
        myDirectors=directors;
    }

    @Override
    public boolean satisfies(String id) {
    
        String director = MovieDatabase.getDirector(id);
        String[] d = myDirectors.split(",");
        for ( String s : d)
        {
            
            if(director.contains(s))
                return true;
        }
        return false;
    }
    
    
}