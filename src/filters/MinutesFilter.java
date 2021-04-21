package src.filters;

import src.db.MovieDatabase;

public class MinutesFilter implements Filter {

    int myMin;
    int myMax;
    public MinutesFilter(int min,int max)
    {
        myMin = min;
        myMax = max;
    }

    @Override
    public boolean satisfies(String id) {
        int min = MovieDatabase.getMinutes(id);
        
        return ((min>=myMin)&&(min<=myMax));
    }
    
}