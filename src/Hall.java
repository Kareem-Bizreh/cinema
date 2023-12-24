import java.util.ArrayList;

public class Hall {

    String name;
    int number;
    private ArrayList<Presentation> hall_presentations;

    public Hall(String name, int number) {
        this.name = name;
        this.number = number;
        hall_presentations = new ArrayList<Presentation>();
    }

    public boolean add_presentation(Presentation x) {

        for(int i = 0; i < hall_presentations.size(); i++) {
            Presentation t = hall_presentations.get(i);
            if(!check2times(t.time, t.duration, x.time, x.duration)) {
                return false;
            }
        }

        hall_presentations.add(x);
        return true; 
    }

    public boolean add_presentation(String movie_name, int movie_ID, Date time, int duratoin, int hall_number) {
        Presentation p = new Presentation(movie_name, movie_ID, time, duratoin, hall_number);
        return this.add_presentation(p);
    }

    public boolean remove_presentation(Presentation x) {
        for(int i = 0; i < hall_presentations.size(); i++) {
            if(hall_presentations.get(i).equals(x)) {
                hall_presentations.remove(i);
                return true;
            }
        }
        return false;
    }    

    private boolean check2times(Date d1, int du1, Date d2, int du2) {
        if(d1.day == d2.day) {
            if(d1.hour + du1 >= d2.hour || d1.hour <= d2.hour + du2)
            {
                return true; 
            }
        }
        return false;
    }

    public ArrayList<Integer> hallsOfMovie(int filmID, Date filmDate) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = hall_presentations.size();

        for(int i = 0; i < n; i++) {
            Presentation p = hall_presentations.get(i);
            if(p.movie_ID == filmID && p.time.equals(filmDate)) 
                ans.add(p.hull_number);
        }
        return ans;
    }

    public ArrayList<Integer> hullOfMovie(int filmID) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = hall_presentations.size();

        for(int i = 0; i < n; i++) {
            Presentation p = hall_presentations.get(i);
            if(p.movie_ID == filmID)
                ans.add(p.hull_number);
        }
        return ans;
    }

}
