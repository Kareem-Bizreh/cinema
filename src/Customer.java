import java.util.ArrayList;

public class Customer {
    String name;
    String password;
    int ID;
    static int userID = 0;
    Ticketing booking;
    ArrayList<Ticket> user_tickets;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        user_tickets = new ArrayList<>();
        booking = new Ticketing();
        this.ID = userID;
        userID++; 
    }

    boolean equals(Customer x) {
        if (this.name.equals(x.name) && this.password.equals(x.password)) {
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

}
