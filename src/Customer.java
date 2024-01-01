import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{
    String name;
    String password;
    int ID;
    static int userID = 0;
    Ticketing booking;
    ArrayList<Ticket> user_tickets;
    ArrayList<Pair<Integer, Float>> movies_ratings;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        user_tickets = new ArrayList<>();
        movies_ratings = new ArrayList<>();
        booking = new Ticketing();
        this.ID = userID;
        userID++;
    }

    @Override
    public boolean equals(Object x) {
        if(this == x) return true;
        if (x == null || getClass() != x.getClass()) return false;

        Customer customer = (Customer) x;
        
        if (this.name.equals(customer.name) && this.password.equals(customer.password)) {
            return true;
        }
        return false;
    }

    /** functin to remove user ticket when remove presentation*/
    public void removePres(String movie_name, Date date, String hall_number) {
        ArrayList<Ticket> ans = new ArrayList<>();
        for(int  i = 0; i < user_tickets.size(); i++) {
            Ticket t = user_tickets.get(i);
            if(t.movie_name.equals(movie_name) && t.time.equals(date) && t.hall_name.equals(hall_number)) {}
            else ans.add(t);
        }
        user_tickets = ans;
    }

    public float addRate(int movieID, float rate) {
        Pair<Integer, Float> x = new Pair<>(movieID, rate);
        float c = -1;
        for(int i = 0; i < movies_ratings.size(); i++) {
            Pair<Integer, Float> t = movies_ratings.get(i);
            if(t.getKey() == movieID) {
                c = movies_ratings.remove(i).getValue();              
                break;
            }
        }
        movies_ratings.add(x);
        return c;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", password=" + password + ", ID=" + ID
                + ", user_tickets=" + user_tickets.toString() + ", movies_ratings="
                 + movies_ratings.toString() + "]";
    }

}
