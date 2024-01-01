import java.io.Serializable;
import java.util.Arrays;

public class Presentation implements Serializable{

    String hall_name;
    int hall_number;
    String movie_name;
    int movie_ID;
    Date time;
    Ticket p_tickets [][];
    int numberSoldTicke;
    int duration;

    public Presentation(String movie_name, int movie_ID, Date time, int duratoin, int hall_number) {
        this.hall_number = hall_number;
        this.hall_name = "Hall" + String.valueOf(hall_number);
        this.movie_name = movie_name;
        this.movie_ID = movie_ID;
        this.time = time;
        this.duration = duratoin;
        this.numberSoldTicke = 0;
        this.p_tickets = new Ticket[20][10];

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                Pair<Integer, Integer> temp = new Pair<Integer, Integer>(i + 1, j + 1);
                p_tickets[i][j] = new Ticket(temp, time, movie_name, hall_name);
            }    
        }    
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;

        Presentation p = (Presentation) o;

        if(this.hall_number == p.hall_number && this.movie_ID == p.movie_ID && this.time.equals(p.time))
            return true;
        return false;
    }
    
    public boolean is_full() {
        if(numberSoldTicke == 200) return true;
        return false;
    }

    @Override
    public String toString() {
        String toString_p_tickets = "";
        for(int i = 0; i < 20; i++) {
            toString_p_tickets += Arrays.toString(p_tickets[i]);
            toString_p_tickets += "\n";
        }
        return "Presentation [hall_name=" + hall_name + ", hall_number=" + hall_number + ", movie_name=" + movie_name
                + ", movie_ID=" + movie_ID + ", time=" + time.toString() + ", p_tickets=" + toString_p_tickets
                + ", numberSoldTicke=" + numberSoldTicke + ", duration=" + duration + "]";
    }

}
