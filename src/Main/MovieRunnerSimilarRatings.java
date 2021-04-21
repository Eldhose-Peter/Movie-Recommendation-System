package src.Main;

import java.util.ArrayList;
import java.util.Collections;

import src.db.MovieDatabase;
import src.db.RaterDatabase;
import src.filters.AllFilters;
import src.filters.DirectorFilter;
import src.filters.GenreFilter;
import src.filters.MinutesFilter;
import src.filters.YearAfterFilter;
import src.pdo.Rating;

public class MovieRunnerSimilarRatings {

    static void printAverageRatings()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters=1;
        ratings = fourthRatings.getAverageRatings(minimumraters);
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue()+"  "+title);
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        String genre = "Romance";
        int year = 1980;

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));

        ratings = fourthRatings.getAverageRatingsByFilter(minimumraters, allFilters);
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        int minutes;
        String director;
        int Year;
        String Genre;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            minutes = MovieDatabase.getMinutes(r.getItem());
            director = MovieDatabase.getDirector(r.getItem());
            Year = MovieDatabase.getYear(r.getItem());
            Genre = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue()+" ,Time : "+minutes+", "+title+" ,Year : "+Year+", "+Genre+ " ,director :"+director);
        }


    }

    public static void printSimilarRatings()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());

        String id ="65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> ratings =fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println("Rating size :" +ratings.size());

        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }

    }

    public static void printSimilarRatingsByGenre()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());

        ArrayList<Rating> ratings =new ArrayList<>();
        String id ="65";
        int numSimilarRaters = 20;
        int minimumraters = 5;
        String genre =  "Action";
        ratings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimumraters,new GenreFilter(genre));
        System.out.println("Number of movies found: "+ratings.size());
        //Collections.sort(ratings);

        String title;
        String genre1;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            genre1 = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue()+"  "+genre1+" "+title);
        }
    }

    public static void printSimilarRatingsByDirector()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());

        ArrayList<Rating> ratings =new ArrayList<>();
        String id ="1034";
        int numSimilarRaters = 10;
        int minimumraters = 3;
        String directors =  "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        ratings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimumraters,new DirectorFilter(directors));
        System.out.println("Number of movies found: "+ratings.size());
        //Collections.sort(ratings);

        String title;
        int minutes;
        String director;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            minutes = MovieDatabase.getMinutes(r.getItem());
            director = MovieDatabase.getDirector(r.getItem());
            System.out.println(r.getValue()+" , Time : "+minutes+" , "+title+" director :"+director);
        }
    }

    public static void printSimilarRatingsByGenreAndMinutes()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 5;
        
        String genre = "Adventure";
        int min = 100;
        int max = 200;

        String id ="65";
        int numSimilarRaters = 10;

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new MinutesFilter(min,max));

        ratings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimumraters, allFilters);
        System.out.println("Number of movies found: "+ratings.size());
        //Collections.sort(ratings);

        String title;
        int minutes;
        String director;
        int Year;
        String Genre;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            minutes = MovieDatabase.getMinutes(r.getItem());
            director = MovieDatabase.getDirector(r.getItem());
            Year = MovieDatabase.getYear(r.getItem());
            Genre = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue()+" ,Time : "+minutes+", "+title+" ,Year : "+Year+", "+Genre+ " ,director :"+director);
        }

    }

    public static void printAverageRatingsByYearAfterAndMinutes()
    {
        FourthRatings fourthRatings =new FourthRatings("ratings.csv");
        System.out.println("Number of raters read: "+RaterDatabase.size());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        String id ="65";
        int minimumraters = 5;
        int numSimilarRaters =10;
        int min = 80;
        int max = 100;
        int year = 2000;

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new MinutesFilter(min,max));

        ratings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters ,minimumraters, allFilters);
        System.out.println("Number of movies found: "+ratings.size());
        //Collections.sort(ratings);

        String title;
        int minutes;
      
        int Year;
       
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            minutes = MovieDatabase.getMinutes(r.getItem());
            //director = MovieDatabase.getDirector(r.getItem());
            Year = MovieDatabase.getYear(r.getItem());
            //Genre = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue()+" ,Time : "+minutes+", "+title+" ,Year : "+Year+", ");
        }

    }


    public static void main(String[] args) {
       // printAverageRatings();
       // printAverageRatingsByYearAfterAndGenre();
        //printSimilarRatings();
        printSimilarRatingsByGenre();
        printSimilarRatingsByDirector();
        printSimilarRatingsByGenreAndMinutes();
        printAverageRatingsByYearAfterAndMinutes();



    }
    
}