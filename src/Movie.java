import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Movie {
    private String name;
    private int ID;
    static int movieID;
    int duration;
    TypeMovie type;
    int counter;
    HashMap<Date, Integer> showtimes;
    ArrayList<String> comments;
    ArrayList<String> categories;

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

    public Movie(String name, int duration, TypeMovie type) {
        this.name = name;
        this.type = type;
        this.ID = movieID;
        movieID++;
        this.showtimes = new HashMap<>();
        this.categories = new ArrayList<>();
        this.comments = new ArrayList<>();
        counter = 0;
    }

    public boolean addTime(Date data) {
        if (showtimes.containsKey(data))
            return false;
        else {
            showtimes.put(data, 0);
            return true;
        }
    }

    public void removeTime(Date data) {
        showtimes.remove(data);
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

}