import java.io.Serializable;
import java.util.*;

public class Cinema implements CanOperation, Serializable {

    private ArrayList<Customer> customers;
    private ArrayList<Movie> movies;
    Hall halls[];
    int personsPerHour[];

    // unparameterized constructor
    public Cinema() {
        this.personsPerHour = new int[24];
        this.halls = new Hall[20];
        for(int i = 0; i < 20; i++) {
            halls[i] = new Hall("Hall" + Integer.valueOf(i + 1), i + 1);
        }
        this.movies = new ArrayList<>();
        this.customers = new ArrayList<>();
        for (int i = 0; i < 24; i++)
            personsPerHour[i] = 0;
    }

    @Override
    public boolean addMovie(String name, int duration, TypeMovie type) {
        for (int i = 0; i < movies.size(); i++)
            if (movies.get(i).name.equals(name))
                return false;
        movies.add(new Movie(name, duration, type));
        return true;
    }

    @Override
    public void removeMovie(Movie m) {
        new Thread() {
            @Override
            public void run() {
                for(Hall h : halls) {
                    ArrayList<Presentation> a = h.presentationOfMovie(m.ID);
                    for(Presentation p : a) {
                        removePresentation(m, p);
                    }
                }
                movies.remove(m);
            }
        }.start(); 
    }

    @Override
    public void removeMovie(int index_of_movie) {
        new Thread() {
            @Override
            public void run() {
                Movie m = movies.get(index_of_movie);
                for(Hall h : halls) {
                    ArrayList<Presentation> a = h.presentationOfMovie(m.ID);
                    for(Presentation p : a) {
                        removePresentation(m, p);
                    }
                }
                movies.remove(index_of_movie);
            }
        }.start(); 
    }

    @Override
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

    @Override
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

    @Override
    public int mostCrowdingHourForCinema() {
        int mx = 0;
        for (int i : personsPerHour) {
            mx = Math.max(mx, i);
        }
        return mx;
    }

    @Override
    public boolean addCustomer(String name, String password) {
        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).name.equals(name))
                return false;
        Customer customer = new Customer(name, password);
        customers.add(customer);
        return true;
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        return customers.remove(customer);
    }

    @Override
    public Customer findCustomer(String name, String password) {
        Customer c = new Customer(name, password);
        for (Customer i : customers) {
            if (i.equals(c)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void bookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions, Thread t) {
        t = new Thread(){
            @Override
            public void run() {
                new Ticketing().bookTicket(c,movie,date,hall_number, positions, halls, personsPerHour);
            }
        }; t.start();
    }

    @Override
    public void bookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking, Thread t) {
        t = new Thread() {
            @Override
            public void run() {
                new Ticketing().bookTicket(c, movie, p, booking, personsPerHour);
            }
        }; t.start();
    }

    @Override
    public void unbookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        new Thread() {
            @Override
            public void run() {
                new Ticketing().unbookTicket(c, movie, date, hall_number, positions, halls, personsPerHour);
            }
        }.start();
    }

    @Override
    public void unbookTicket(Customer c, Ticket t) {
        new Thread() {
            @Override
            public void run() {
                Movie movie = findMovie(t.movie_name);
                Presentation p = getPresentation(movie, t.time, convert(t.hall_name));
                new Ticketing().unbookTicket(c, movie, p, t, personsPerHour);
            }
        }.start();
    }

    @Override
    public int priceWithDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        return new Ticketing().priceWithDiscounts(movie, date, hall_number, positions, halls);
    }

    @Override
    public int priceWithDiscounts(ArrayList<Ticket> tickets) {
        return new Ticketing().priceWithDiscounts(tickets);
    }

    @Override
    public int priceWithoutDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        return new Ticketing().priceWithoutDiscounts(movie, date, hall_number, positions, halls);
    }  

    @Override
    public int priceWithoutDiscounts(ArrayList<Ticket> tickets) {
        return new Ticketing().priceWithoutDiscounts(tickets);
    }

    @Override
    public boolean addPresentation(Movie movie, Date date, int hall_number) {
        int duration = movie.duration;
        if(hall_number >= 20 || hall_number < 0) return false;
        boolean c = halls[hall_number - 1].add_presentation(movie.name, movie.ID, date, duration);
        if(c) movie.addPres(date, hall_number);
        return c;
    }

    @Override
    public void removePresentation(Movie m, Presentation p) {
        new Thread() {
            @Override
            public void run() {
                int n = halls[p.hall_number - 1].remove_presentation(p);
                m.removePres(p.time, n);
                personsPerHour[p.time.hour] -= n;
                for(int i = 0; i < customers.size(); i++) {
                    customers.get(i).removePres(p.movie_name, p.time, p.hall_name);
                }
            }
        }.start();   
    }

    @Override
    public void addComment(Customer customer, Movie movie, String Comment) {
        new Thread() {
            @Override
            public void run() {
                movie.addComment(customer.name, Comment);
            }
        }.start();
    }

    @Override
    public synchronized void addRate(Customer user, Movie movie, float rate) {
        new Thread() {
            @Override
            public void run() {
                movie.addRate(user, rate);
            }
        }.start();
    }

    @Override
    public Presentation getPresentation(Movie m, Date date, int hall_number) {
        return halls[hall_number - 1].presentationOfMovie(m.ID, date);
    }

    @Override
    public Ticket[][] getTickets(Presentation p) {
        return p.p_tickets;
    }

    @Override
    public ArrayList<Pair<String, String>> getCommrnts(Movie m) {
        return m.comments;
    }

    @Override
    public float getRate(Movie m) {
        return m.rate; 
    }

    @Override
    public ArrayList<Presentation> getHall_presentations(int hall_number) {
        return halls[hall_number - 1].getHall_presentations();
    }

    @Override
    public boolean is_full(Presentation p) {
        return p.is_full();
    }

    @Override
    public int getChair_number(Ticket t) {
        return t.getChair_number();
    }

    @Override
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public Movie findMovie(String name) {
        for(Movie m : movies){
            if(m.name.equals(name))
                return m;
        }
        return null;
    }

    private int convert(String hall_name) {
        String s = hall_name.substring(5);
        int ans = Integer.parseInt(s);
        return ans;
    }

    @Override
    public String toString() {
        return "Cinema [customers=" + customers.toString() + ", movies=" + movies.toString()
         + ", halls=" + Arrays.toString(halls) + ", personsPerHour=" + Arrays.toString(personsPerHour) + "]";
    }

}
