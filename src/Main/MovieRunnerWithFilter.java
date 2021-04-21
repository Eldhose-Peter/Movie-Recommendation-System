package src.Main;

import java.util.ArrayList;
import java.util.Collections;

import src.pdo.Rating;
import src.db.MovieDatabase;
import src.filters.*;

public class MovieRunnerWithFilter{

    static void printAverageRatings()
    {
        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters=1;
        ratings = thirdRatings.getAverageRatings(minimumraters);
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue()+"  "+title);
        }
    }

    public static void printAverageRatingsByYear()
    {
        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        int year =  2000;
        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters,new YearAfterFilter(year));
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        int year1;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            year1 = MovieDatabase.getYear(r.getItem());
            System.out.println(r.getValue()+"  "+year1+" "+title);
        }

    }

    public static void printAverageRatingsByGenre()
    {

        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        String genre =  "Crime";
        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters,new GenreFilter(genre));
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        String genre1;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            genre1 = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue()+"  "+genre1+" "+title);
        }

    }

    public static void printAverageRatingsByMinutes()
    {

        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        int min=110;
        int max=170;
        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters,new MinutesFilter(min,max));
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

        String title;
        int minutes;
        
        for(Rating r : ratings)
        {
            title = MovieDatabase.getTitle(r.getItem());
            minutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue()+" , Time : "+minutes+" , "+title);
        }

    }

    public static void printAverageRatingsByDirectors()
    {

        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters, new DirectorFilter(directors));
        System.out.println("Number of movies found: "+ratings.size());
        Collections.sort(ratings);

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

    public static void printAverageRatingsByYearAfterAndGenre()
    {
        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        String genre = "Romance";
        int year = 1980;

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));

        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters, allFilters);
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



    public static void printAverageRatingsByDirectorsAndMinutes()
    {
        ThirdRatings thirdRatings =new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters read: "+thirdRatings.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies read: "+MovieDatabase.size());
        

        ArrayList<Rating> ratings =new ArrayList<>();
        int minimumraters = 1;
        
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze,Francis Ford Coppola";
        int min = 30;
        int max = 170;

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorFilter(directors));
        allFilters.addFilter(new MinutesFilter(min,max));

        ratings = thirdRatings.getAverageRatingsByFilter(minimumraters, allFilters);
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





    public static void main(String[] args) {
        printAverageRatings();
        // printAverageRatingsByYear();
       // printAverageRatingsByGenre();
       // printAverageRatingsByMinutes();
        //printAverageRatingsByDirectors();
        printAverageRatingsByYearAfterAndGenre();
        //printAverageRatingsByDirectorsAndMinutes();
    }

}