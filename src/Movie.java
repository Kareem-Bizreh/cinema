/*
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
*/
import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    private String name;
    private int ID;
    static int movieID = 0;
    int duration;
    TypeMovie type;
    int counter;
    HashMap<Date, Integer> showtimes;
    ArrayList<Pair<Date, Integer>> date_hall;
    ArrayList<String> comments;
    ArrayList<String> categories;
/*
    static {
        try {
            File f = new File("movieID.txt");
            if(f.createNewFile()) {
                PrintWriter pw = new PrintWriter("movieID.txt");
                pw.print(0);
                pw.close();
            }
            Scanner sc = new Scanner(f);
            movieID = sc.nextInt();
            sc.close();
        } catch(IOException ioe) { } catch(Exception e) { }
    }
*/
    public Movie(String name, int duration, TypeMovie type) {
        this.name = name;
        this.type = type;
        this.ID = movieID;
        movieID++;
        this.showtimes = new HashMap<>();
        this.categories = new ArrayList<>();
        this.comments = new ArrayList<>();
        date_hall = new ArrayList<>();
        counter = 0;
    }

    public boolean addTime(Date date, int hall_number) {
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

    public void removeTime(Date date) {
        for(int i = 0; i < date_hall.size(); i++) {
            if(date_hall.get(i).getKey().equals(date))
            {
                date_hall.remove(i);
            }
        }
        
        showtimes.remove(date);
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public TypeMovie getType() {
        return type;
    }

    public HashMap<Date, Integer> getShowtimes() {
        return showtimes;
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

}