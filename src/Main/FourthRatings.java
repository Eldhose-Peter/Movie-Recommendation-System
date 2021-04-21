package src.Main;

import java.util.ArrayList;
import java.util.Collections;

import src.db.MovieDatabase;
import src.db.RaterDatabase;
import src.filters.*;

import src.pdo.Rater;
import src.pdo.Rating;

public class FourthRatings {

    private ArrayList<Rater> myRaters;

    public FourthRatings(){
        this("ratings.csv");
    }

    public FourthRatings(String filename)
    {
        RaterDatabase.initialize(filename);
        myRaters = RaterDatabase.getRaters();

    }

    private Double getAverageByID(String id,int minimalRaters)
    {
        
        ArrayList<Double> numRates= new ArrayList<>(); 

        for(Rater r : myRaters)
        {
            if(r.hasRating(id))
            {
                numRates.add(r.getRating(id));
            }
        }

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


    private double dotProduct (Rater me , Rater r)
    {
            ArrayList<String> myRatings = me.getItemsRated();
            double myRate,rRate,dotProduct=0;
    

            for(String s : myRatings)
            {
                myRate = me.getRating(s);
                if(r.hasRating(s))
                {
                    rRate = r.getRating(s);
                //translate a rating from the scale 0 to 10 to the scale ­-5 to 5
                    myRate-=5;
                    rRate-=5;

                    dotProduct+=(myRate*rRate);
                }    

            }
            return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id)
    {
            //ArrayList<Rater> ourRaters = RaterDatabase.getRaters();
            ArrayList<Rating> ourRatings = new ArrayList<>();
            Rater me = RaterDatabase.getRater(id);
            Rating rating;
            for(Rater r : myRaters)
            {
                if(r.getID().compareTo(id)!=0)
                {
                    rating = new Rating(r.getID(), dotProduct(me, r));
                    ourRatings.add(rating);
                }
            }
            //Note that in each Rating object the item field is a rater’s ID, and the value
            //field is the dot product comparison between that rater and the rater whose ID is
            //the parameter to ​getSimilarities
            Collections.sort(ourRatings,Collections.reverseOrder());
            return ourRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id , int numSimilarRaters , int minimalRaters)
    {
            //raters ID and their closesness to id
            ArrayList<Rating> weightRatings = getSimilarities(id);
            System.out.println(weightRatings.size());

            ArrayList<String> movieIDList = MovieDatabase.filterBy(new TrueFilter());
            System.out.println(movieIDList.size());
            //movie ID and their weighted average ratings
            ArrayList<Rating> rList = new ArrayList<>();
            Rating r;  
            Rater rater;

            for(String movieId: movieIDList)
            {
                ArrayList<Double> listRatings = new ArrayList<>();
                //System.out.println(movieId);
                for(int i = 0;i<numSimilarRaters;i++)
                {
                    r = weightRatings.get(i);
                    String raterId = r.getItem();
                    double raterClossness = r.getValue();
                    
                    //if(movieId.compareTo("2582846")==0)
                       // System.out.println(raterId+"  , "+raterClossness+" , "+i);
                   
                    if(raterClossness<=0)
                    {break;}

                    rater = RaterDatabase.getRater(raterId);

                    if(rater.hasRating(movieId))
                    {
                        //if(movieId.compareTo("2582846")==0)
                        //System.out.println(raterId+"  , "+raterClossness);
                        listRatings.add(raterClossness*rater.getRating(movieId));
                    }
                
                }
                double sum=0.0;

                if(listRatings.size()<minimalRaters)
                {continue;}
                
                for(double rating : listRatings)
                {
                    sum+=rating;
                } 
               // System.out.println(movieId+" ,,, "+sum/listRatings.size());
                r = new Rating(movieId, sum/listRatings.size());
                rList.add(r);
                
            
            }
            Collections.sort(rList,Collections.reverseOrder());
            return rList;
            
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id , int numSimilarRaters , int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> weightRatings = getSimilarities(id);
       

        ArrayList<String> movieIDList = MovieDatabase.filterBy(filterCriteria);

        //movie ID and their weighted average ratings
        ArrayList<Rating> rList = new ArrayList<>();
        Rating r;  
        Rater rater;

        for(String movieId: movieIDList)
        {
            ArrayList<Double> listRatings = new ArrayList<>();
                    
            for(int i =0;i<numSimilarRaters;i++)
            {
                r = weightRatings.get(i);
                String raterId = r.getItem();
                double raterClossness = r.getValue();

                if(raterClossness<=0)
                {break;}

                rater = RaterDatabase.getRater(raterId);

                if(rater.hasRating(movieId))
                {
                    listRatings.add(raterClossness*rater.getRating(movieId));
                }
            
            }
            Double sum=0.0;

             if(listRatings.size()>=minimalRaters)
            {
                for(Double rating : listRatings)
                {
                    sum+=rating;
                } 
                r = new Rating(movieId, sum/listRatings.size());
                rList.add(r);
            }
        
        }
        Collections.sort(rList,Collections.reverseOrder());
        return rList;

    }

    
}