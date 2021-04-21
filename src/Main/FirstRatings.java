package src.Main;

import src.pdo.*;

import edu.duke.*;
//import jdk.javadoc.internal.doclets.formats.html.resources.standard;

import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    ArrayList<Movie> mList = new ArrayList<>();
    ArrayList<EfficientRater> mRaters = new ArrayList<>();

    public ArrayList<Movie> loadMovies(String filename) 
    {
        String path = "/home/eldho/Desktop/fun/Java/RecommendationSystem/data/" + filename;
        FileResource fr = new FileResource(path);

        
        Movie movie;
        int time;

        for(CSVRecord record : fr.getCSVParser())
        {
            time = (int)Integer.parseInt(record.get("minutes"));
            movie = new Movie(record.get("id"),record.get("title"),record.get("year"),
                        record.get("genre"),record.get("director"),record.get("country"),
                        record.get("poster"),time);
            mList.add(movie);
        }

        return mList;
    }

    public void testLoadMovies()
    {
        ArrayList<Movie> mArrayList = new ArrayList<>();
        mArrayList = loadMovies("ratedmoviesfull.csv");
        System.out.println(mArrayList.size());
    }

    public  ArrayList<EfficientRater> loadRaters(String filename) 
    {
        String path = "/home/eldho/Desktop/fun/Java/RecommendationSystem/data/" + filename;
        FileResource fr = new FileResource(path);

        
        
        String prevId = "0";
        String currId ;
        EfficientRater rater = new EfficientRater(prevId);
    
        for(CSVRecord record : fr.getCSVParser())
        {   
    
            currId = record.get("rater_id");

            if(currId.compareTo(prevId)!=0 ){
            rater = new EfficientRater(currId);
            mRaters.add(rater);
            prevId = currId;
            }
    
            rater.addRating(record.get("movie_id"),Double.parseDouble( record.get("rating")));

           
        }

        return mRaters;
    }

    public  void testLoadRaters()
    {
        ArrayList<EfficientRater> mArrayList = new ArrayList<>();
        mArrayList = loadRaters("ratings.csv");
        System.out.println(mArrayList.size());
    }


    public  int numRate(String id)
    {   //number of ratings for a rater(id)
        
        for(EfficientRater rater : mRaters)
        {
            if(rater.getID().compareTo(id)==0)
            {   
                return rater.numRatings(); 
            }
        }
        return 0;
    }

    public void maxRate()
    {
        //rater with max number of ratings
        int max =0;
        for(EfficientRater rater : mRaters)
        {
            if(rater.numRatings()>max)
            {
                max= rater.numRatings();
            }
        }

        System.out.println("Max rating from a rater : "+max);

        for(EfficientRater rater: mRaters)
        {
            if(rater.numRatings() == max )
            System.out.println(rater.getID());
        }
    }

    public  ArrayList<Double> numRateMovie(String id)
    {
        //ratings for a movie(id)

        ArrayList<Double> ratings = new ArrayList<>();
        for(EfficientRater rate : mRaters)
        {
            if(rate.hasRating(id))
            {
        
                ratings.add(rate.getRating(id));
            
            }
        }
        
        return ratings;
    }

    public  ArrayList<String> numMovies()
    {   
        //list of movies rated

        ArrayList<String> ratings = new ArrayList<>();
        for(EfficientRater rater:mRaters)
        {
            for(String s : rater.getItemsRated())
            {
                if(!ratings.contains(s))
                    ratings.add(s);
            }
        }
        return ratings;
    }

    public void maxDirector()
    {
        //director with max number of movies
        
        HashMap<String,Integer> directors = new HashMap<>();
        int val;
        int max =1;
        for(Movie movie : mList)
        {
            String[] dlist = movie.getDirector().split(",");
            for(String s :dlist)
            {
                if(directors.containsKey(s))
                {
                    val = directors.get(s);
                    val = val+1;
                    if(val>max)
                     {max =val;}
                    directors.remove(s);
                    directors.put(s, val);
                }
                else{
                directors.put(s, 1);
                }
            }
            
        }
        System.out.println("max value :"+max);
        
        for(String s : directors.keySet())
        {
            if(directors.get(s)==max)
            {
                System.out.println(s);
            }
        }

        

    }
/*
    public static void main(String[] args) {
        

        //Movies
        testLoadMovies();
        int count= 0;
        for(Movie movie : mList)
        {
              
            if(movie.getMinutes()>150){
                //System.out.print("time greater than 150 :");
                //System.out.println (movie.getTitle());
                count++;
            }
            
            if(movie.getGenres().contains("Comedy"))
            {
                
                //System.out.println("Comedy genre: "+movie.getTitle());
            }
            
        }
        System.out.println(count);

        //Rates
        testLoadRaters();
        
        System.out.println("number of ratings : "+numRate("193"));//number pf ratings by a rater
        maxRate();
        System.out.println(numRateMovie("1798709"));
        System.out.println(numMovies());
        maxDirector();
    }*/
}