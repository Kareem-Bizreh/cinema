import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cinema implements CanOperation, Serializable {

    private ArrayList<Customer> customers;
    private ArrayList<Movie> movies;
    Hall halls[] = new Hall[20];
    int personsPerHour[];

    // unparameterized constructor
    public Cinema() {
        this.personsPerHour = new int[24];
        this.halls = new Hall[20];
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        for (int i = 0; i < 24; i++)
            personsPerHour[i] = 0;
    }

    public boolean addMovie(String name, int duration, TypeMovie type) {
        for (int i = 0; i < movies.size(); i++)
            if (movies.get(i).name == name)
                return false;
        movies.add(new Movie(name, duration, type));
        return true;
    }

    public ArrayList<String> mostPopularMovie() {
        ArrayList<String> ans = new ArrayList<>();
        int c = 0;
        for (Movie i : movies) {
            if (i.counter > c) {
                c = i.counter;
            }
        }
        for (Movie i : movies) {
            if (i.counter == c) {
                ans.add(i.name);
            }
        }
        return ans;
    }

    public ArrayList<Date> mostPopularDateForMovie(Movie x) {
        Set<Map.Entry<Date, Integer>> st = x.showtimes.entrySet();
        Iterator<Map.Entry<Date, Integer>> it = st.iterator();
        ArrayList<Date> ans = new ArrayList<>();
        int mx = 0;
        while (it.hasNext()) {
            Map.Entry<Date, Integer> mEntry = (Map.Entry<Date, Integer>) it.next();
            if ((int) mEntry.getValue() > mx) {
                mx = (int) mEntry.getValue();
            }
        }
        it = st.iterator();
        while (it.hasNext()) {
            Map.Entry<Date, Integer> mEntry = (Map.Entry<Date, Integer>) it.next();
            if ((int) mEntry.getValue() == mx) {
                ans.add((Date) mEntry.getKey());
            }
        }
        return ans;
    }

    public int mostCrowdingHourForCinema() {
        int mx = 0;
        for (int i : personsPerHour) {
            mx = Math.max(mx, i);
        }
        return mx;
    }

    public boolean addCustomer(String name, String password) {
        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).name.equals(name))
                return false;
        Customer customer = new Customer(name, password);
        customers.add(customer);
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        return customers.remove(customer);
    }

    public Customer findCustomer(String name, String password) {
        Customer c = new Customer(name, password);
        for (var i : customers) {
            if (i.equals(c)) {
                return i;
            }
        }
        return null;
    }

    
    private void bookTicket(Customer c, Movie movie, Date date, int hall_number,
                            Pair<Integer, Integer> position) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().bookTicket(c,movie,date,hall_number,position, halls, personsPerHour);
            }
        }.start();
    }

    private void unbookTicket(Customer c, Movie movie, Date date, int hall_number,
                            Pair<Integer, Integer> position) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().unbookTicket(c, movie, date, hall_number, position, halls, personsPerHour);
            }
        }.start();
    }
    
    public void bookTicket(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        for(Pair<Integer, Integer> position : positions) {
            bookTicket(c, movie, date, hall_number, position);
        }
    }
    
    public void unbookTicket(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        for(Pair<Integer, Integer> position : positions) {
            unbookTicket(c, movie, date, hall_number, position);
        }
    }                        

}
