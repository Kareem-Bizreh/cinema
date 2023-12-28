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

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Presentation> getHall_presentations() {
        return hall_presentations;
    }

    public boolean add_presentation(Presentation x) {

        if(x.hall_number != this.number) return false;

        for(int i = 0; i < hall_presentations.size(); i++) {
            Presentation t = hall_presentations.get(i);
            if(!check2times(t.time, t.duration, x.time, x.duration)) {
                return false;
            }
        }

        hall_presentations.add(x);
        return true; 
    }

    public boolean add_presentation(String movie_name, int movie_ID, Date time, int duratoin) {
        Presentation p = new Presentation(movie_name, movie_ID, time, duratoin, this.number);
        return this.add_presentation(p);
    }

    public int remove_presentation(Presentation x) {
        for(int i = 0; i < hall_presentations.size(); i++) {
            Presentation hp = hall_presentations.get(i);
            if(hp.equals(x)) {
                int n = hp.numberSoldTicke;
                hall_presentations.remove(i);
                return n;
            }
        }
        return 0;
    }    

    private boolean check2times(Date d1, int du1, Date d2, int du2) {
        if(d1.day == d2.day) {
            if(d1.hour + du1 >= d2.hour || d1.hour <= d2.hour + du2) {
                return true;
            }
            return false;
        }
        return true;
    }

    public Presentation presentationOfMovie(int filmID, Date filmDate) {
        int n = hall_presentations.size();

        for(int i = 0; i < n; i++) {
            Presentation p = hall_presentations.get(i);
            if(p.movie_ID == filmID && p.time.equals(filmDate))
                return p;
        }
        return null;
    }

    public ArrayList<Presentation> presentationOfMovie(int filmID) {
        ArrayList<Presentation> ans = new ArrayList<>();
        int n = hall_presentations.size();

        for(int i = 0; i < n; i++) {
            Presentation p = hall_presentations.get(i);
            if(p.movie_ID == filmID )
                ans.add(p);
        }
        return ans;
    }

}
