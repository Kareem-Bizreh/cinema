/*
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
*/
import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    String name;
    int ID;
    static int movieID = 0;
    int duration;
    TypeMovie type;
    int counter;
    float rate;
    private int number_ratings;
    private float sum_ratings;
    HashMap<Date, Integer> showtimes;
    ArrayList<Pair<Date, Integer>> date_hall;
    ArrayList<Pair<String,String>> comments;

    public Movie(String name, int duration, TypeMovie type) {
        this.name = name;
        this.type = type;
        this.ID = movieID;
        rate = 5;
        this.number_ratings = 0;
        movieID++;
        this.showtimes = new HashMap<>();
        this.comments = new ArrayList<>();
        date_hall = new ArrayList<>();
        counter = 0;
    }

    public boolean addPres(Date date, int hall_number) {
        for(int i = 0; i < date_hall.size(); i++) {
            if(date_hall.get(i).getValue() == hall_number) {
                if(!check2times(date_hall.get(i).getKey(), this.duration, date, this.duration)) {
                    return false;
                }
            }
        }

        date_hall.add(new Pair<>(date, hall_number));

        if (showtimes.containsKey(date));
        else {
            showtimes.put(date, 0);
        }
        return true;
    }

    public void removePres(Date date, int number_booking_users) {
        ArrayList<Pair<Date, Integer>> ans = new ArrayList<>();
        for(int i = 0; i < date_hall.size(); i++) {
            if(!date_hall.get(i).getKey().equals(date)) {
                ans.add(date_hall.get(i));
            }
        }
        
        this.date_hall = ans;
        showtimes.compute(date, (k, v) -> (v == null) ? 0 : v - number_booking_users);
    }

    private boolean check2times(Date d1, int du1, Date d2, int du2) {
        if(d1.day == d2.day) {
            if(d1.hour + du1 >= d2.hour || d1.hour <= d2.hour + du2)
            {
                return true; 
            }
            return false;
        }
        return true;
    }

    public void addComment(String name, String comment) {
        this.comments.add(new Pair<>(name, comment));
    }

    public boolean addRate(float x) {
        if(x <= 10 && x >= 0) {
            this.number_ratings++;
            this.sum_ratings += x;
            this.rate = sum_ratings / number_ratings;
            return true;
        }
        return false;
    }

}