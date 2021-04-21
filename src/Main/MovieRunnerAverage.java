package src.Main;

import src.pdo.*;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    static void printAverageRatings()
    {
        SecondRatings secondRatings =new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        System.out.println("Number of movies : "+secondRatings.getMovieSize());
        System.out.println("Number of raters : "+secondRatings.getRaterSize());

        ArrayList<Rating> ratings =new ArrayList<>();
        ratings = secondRatings.getAverageRatings(3);
        Collections.sort(ratings);

        String title;
        
        for(Rating r : ratings)
        {
            title = secondRatings.getTitle(r.getItem());
            System.out.println(r.getValue()+"  "+title);
        }
    }

    public static void getAverageRatingOneMovie()
    {
        SecondRatings secondRatings =new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        String title = "The Godfather";
        String id = secondRatings.getID(title);

        ArrayList<Rating> ratings =new ArrayList<>();
        ratings = secondRatings.getAverageRatings(3);

        for(Rating r : ratings)
        {   
            if(r.getItem().compareTo(id)==0)
            
            System.out.println("Average Rating :"+r.getValue());
        }

      
    }

    public static void main (String[] args)
    {
        printAverageRatings();
        getAverageRatingOneMovie();
    }

    
    
}