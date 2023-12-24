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

    public boolean add_resentation(Presentation x) {

        for(int i = 0; i < hall_presentations.size(); i++) {
            Presentation t = hall_presentations.get(i);
            if(!check2times(t.time, t.duration, x.time, x.duration)) {
                return false;
            }
        }   

        hall_presentations.add(x);
        return true; 
    }

    public boolean remove_resentation(Presentation x) {
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

}
