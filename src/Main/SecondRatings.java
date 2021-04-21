package src.Main;

import src.pdo.*;
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile , String ratingsfile)
    {
      
      FirstRatings firstRatings = new FirstRatings();
      myMovies= firstRatings.loadMovies(moviefile);
      myRaters =firstRatings.loadRaters(ratingsfile);

    }

    public int getMovieSize()
    {
      return myMovies.size();
    }
    
    public int getRaterSize()
    {
      return myRaters.size();
    }

    private Double getAverageByID(String id,int minimalRaters)
    {
        ArrayList<Double> numRates =new ArrayList<>();

        FirstRatings firstRatings = new FirstRatings();
        numRates = firstRatings.numRateMovie(id);
        Double sum=0.0;

        if(numRates.size()>minimalRaters)
        {
            for(Double rating : numRates)
            {
                sum+=rating;

            } 
            return sum/numRates.size();
        }
        else
        {
          return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<String> movieIDList = new ArrayList<>();
        ArrayList<Rating> rList = new ArrayList<>();
        Rating r;

        FirstRatings firstRatings = new FirstRatings();
        movieIDList = firstRatings.numMovies();
        Double avgRating;
        for(String id : movieIDList)
        {
            avgRating = getAverageByID(id, minimalRaters);

            if(avgRating>0.0)
            {
               r=new Rating(id, avgRating);
               rList.add(r);
            }
        }
        return rList;
    }

    public String getTitle(String id)
    {
        for(Movie movie : myMovies)
        {
        
          if(movie.getID().compareTo(id)==0)
          {
            return movie.getTitle();
          }
        
        }

        return "Movie ID not found ";
    }

    public String getID(String title)
    {
      
      for(Movie movie : myMovies)
      {
      
        if(movie.getTitle().compareTo(title)==0)
        {
          return movie.getID();
        }
      
      }

      return "No Such Title Found ";
    }

  
}
