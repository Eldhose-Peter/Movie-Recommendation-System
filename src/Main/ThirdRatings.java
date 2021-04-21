package src.Main;

import src.pdo.*;
import src.db.*;
import src.filters.*;

import java.util.ArrayList;

public class ThirdRatings {

    private ArrayList<EfficientRater> myRaters;
    FirstRatings firstRatings;

    public ThirdRatings()
    {
        this("ratings.csv");
    }

    public ThirdRatings(String filename)
    {
        firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(filename);
    }
    
  
    public int getRaterSize()
    {
      return myRaters.size();
    }

    private Double getAverageByID(String id,int minimalRaters)
    {
        ArrayList<Double> numRates =new ArrayList<>();

        numRates = firstRatings.numRateMovie(id);
        Double sum=0.0;

        if(numRates.size()>=minimalRaters)
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
        movieIDList = MovieDatabase.filterBy(new TrueFilter());
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters , Filter filterCriteria)
    {
        
        ArrayList<String> movieIDList = new ArrayList<>();
        ArrayList<Rating> rList = new ArrayList<>();
        Rating r;
        movieIDList = MovieDatabase.filterBy(filterCriteria);
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
    

}