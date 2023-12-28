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

    public void removeMovie(Movie m) {
        new Thread(){
            @Override
            public void run() {
                for(var h : halls) {
                    ArrayList<Presentation> a = h.presentationOfMovie(m.ID);
                    for(Presentation p : a) {
                        removePresentation(m, p);
                    }
                }
                movies.remove(m);
            }
        }.start(); 
    }

    public void removeMovie(int index_of_movie) {
        new Thread(){
            @Override
            public void run() {
                Movie m = movies.get(index_of_movie);
                for(var h : halls) {
                    ArrayList<Presentation> a = h.presentationOfMovie(m.ID);
                    for(Presentation p : a) {
                        removePresentation(m, p);
                    }
                }
                movies.remove(index_of_movie);
            }
        }.start(); 
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
            if ((int) mEntry.getValue() == mx && mx != 0) {
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
    
    public void bookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().bookTicket(c,movie,date,hall_number, positions, halls, personsPerHour);
            }
        }.start();
    }
    
    public void bookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().bookTicket(c, movie, p, booking, personsPerHour);
            }
        }.start();
    }

    public void unbookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().unbookTicket(c, movie, date, hall_number, positions, halls, personsPerHour);
            }
        }.start();
    }

    public void unbookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking) {
        new Thread(){
            @Override
            public void run() {
                new Ticketing().unbookTicket(c, movie, p, booking, personsPerHour);
            }
        }.start();
    }

    public int priceWithDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        return new Ticketing().priceWithDiscounts(movie, date, hall_number, positions, halls);
    }

    public int priceWithDiscounts(ArrayList<Ticket> tickets) {
        return new Ticketing().priceWithDiscounts(tickets);
    }

    public int priceWithoutDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        return new Ticketing().priceWithoutDiscounts(movie, date, hall_number, positions, halls);
    }                  

    public int priceWithoutDiscounts(ArrayList<Ticket> tickets) {
        return new Ticketing().priceWithoutDiscounts(tickets);
    }

    public boolean addPresentation(Movie movie, Date date, int duration, int hall_number) {
        if(hall_number >= 20 || hall_number < 0) return false;
        boolean c = halls[hall_number - 1].add_presentation(movie.name, movie.ID, date, duration);
        if(c) movie.addPres(date, hall_number);
        return c;
    }

    public void removePresentation(Movie m, Presentation p) {
        new Thread(){
            @Override
            public void run() {
                m.removePres(p.time, halls[p.hall_number - 1].remove_presentation(p));
                for(int i = 0; i < customers.size(); i++) {
                    customers.get(i).removePres(p.movie_name, p.time, p.hall_name);
                }
            }
        }.start();   
    }

}
