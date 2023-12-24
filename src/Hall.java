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
            if(t.time.day == x.time.day && t.time.hour == x.time.hour) {
                return false;
            }
        }   

        hall_presentations.add(x);
        return true; 
    }

    public boolean remove_resentation(Presentation x) {
        for(int i = 0; i < hall_presentations.size(); i++) {
            if(hall_presentations.get(i).equals(x)) {
                hall_presentations.remove(x);
                return true;
            }
        }
        return false;
    }    

}
