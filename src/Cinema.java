import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cinema implements CanOperation, Serializable {

    private ArrayList<Customer> customers;
    private ArrayList<Movie> movies;
    Hall halls[];
    int personsPerHour[];
    File moviesFile;
    File customersFile;
    File hallsFile;
    File cperHourFile;

    public Cinema() {
        moviesFile = new File("movies.txt");
        customersFile = new File("customers.txt");
        hallsFile = new File("halls.txt");
        cperHourFile = new File("perHour.txt"); 
        try {
            if(!moviesFile.exists() || !customersFile.exists() || !hallsFile.exists() || !cperHourFile.exists()) {
                moviesFile.createNewFile();
                hallsFile.createNewFile();
                customersFile.createNewFile();
                cperHourFile.createNewFile();

                this.personsPerHour = new int[24];
                this.halls = new Hall[20];
                for(int i = 0; i < 20; i++) {
                    halls[i] = new Hall("Hall" + Integer.valueOf(i + 1), i + 1);
                }
                this.movies = new ArrayList<>();
                this.customers = new ArrayList<>();
                saveM();
                saveH();
                saveC();
                saveCpH();
            }
            else {
                readAll();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void readM() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(moviesFile))) {
            movies = (ArrayList<Movie>) ois.readObject();
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void readC() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customersFile))) {
            this.customers = (ArrayList<Customer>)ois.readObject();
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void readH() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hallsFile))) {
            this.halls = (Hall[]) ois.readObject();
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void readCpH() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cperHourFile))) {
            this.personsPerHour = (int[]) ois.readObject();
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void  readAll() {
        readC();
        readCpH();
        readH();
        readM();
    }

    private void saveM() {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(moviesFile))) {
            ois.writeObject(movies);
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void saveC() {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(customersFile))) {
            ois.writeObject(customers);
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void saveH() {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(hallsFile))) {
            ois.writeObject(halls);
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void saveCpH() {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(cperHourFile))) {
            ois.writeObject(personsPerHour);
        } catch(IOException ioe) {
            System.out.println(ioe);
        } catch(Exception e) {
            System.out.println(e);
        }
    }



    @Override
    public boolean addMovie(String name, int duration, TypeMovie type) {
        //// readAll();
        for (int i = 0; i < movies.size(); i++)
            if (movies.get(i).name.equals(name))
                return false;
        movies.add(new Movie(name, duration, type));
        saveM();
        return true;
    }

    @Override
    public void removeMovie(Movie m) {
        // // readAll();
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
                saveM();
                saveH();
            }
        }.start(); 
    }

    @Override
    public void removeMovie(int index_of_movie) {
        // // readAll();
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
                saveM();
                saveH();
            }
        }.start(); 
    }

    @Override
    public Movie findMovie(String name) {
        // readAll();
        for(Movie m : movies){
            if(m.name.equals(name))
                return m;
        }
        return null;
    }



    @Override
    public ArrayList<String> mostPopularMovie() {
        // readAll();
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
        // readAll();
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
        // readAll();
        int mx = 0;
        for (int i : personsPerHour) {
            mx = Math.max(mx, i);
        }
        return mx;
    }



    @Override
    public boolean addCustomer(String name, String password) {
        // readAll();
        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).name.equals(name))
                return false;
        Customer customer = new Customer(name, password);
        customers.add(customer);
        saveC();
        return true;
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        // readAll();
        saveC();
        return customers.remove(customer);
    }

    @Override
    public Customer findCustomer(String name, String password) {
        // readAll();
        Customer c = new Customer(name, password);
        for (Customer i : customers) {
            if (i.equals(c)) {
                return i;
            }
        }
        return null;
    }

    private void changeComments(String user_name, String new_name) {
        // readAll();
        for(Movie m : movies) {
            for(Pair<String, String> pair : m.comments) {
                if(pair.getKey().equals(user_name)) {
                    pair.setKey(new_name);
                }
            }
        }
        saveM();
    }

    @Override
    public boolean changeName(Customer customer, String name) {
        // readAll();
        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).name.equals(name))
                return false;
        customer.name = name;
        saveC();
        new Thread() {
            @Override
            public void run() {
                changeComments(customer.name, name);
            }
        }.start();
        return true;       
    }

    @Override
    public void changePassword(Customer customer, String password) {
        // readAll();
        customer.password = password;
        saveC();
    }



    @Override
    public void bookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions, Thread t) {
        // readAll();
        t = new Thread(){
            @Override
            public void run() {
                new Ticketing().bookTicket(c,movie,date,hall_number, positions, halls, personsPerHour);
                saveCpH();
                saveC();
                saveH();
                saveM();
            }
        }; t.start();
    }

    @Override
    public void bookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking, Thread t) {
        // readAll();
        t = new Thread() {
            @Override
            public void run() {
                new Ticketing().bookTicket(c, movie, p, booking, personsPerHour);
                saveCpH();
                saveC();
                saveH();
                saveM();
            }
        }; t.start();
    }

    @Override
    public void unbookTickets(Customer c, Movie movie, Date date, int hall_number,
                            ArrayList<Pair<Integer, Integer>> positions) {
        // readAll();                        
        new Thread() {
            @Override
            public void run() {
                new Ticketing().unbookTicket(c, movie, date, hall_number, positions, halls, personsPerHour);
                saveCpH();
                saveC();
                saveH();
                saveM();
            }
        }.start();
    }

    @Override
    public void unbookTicket(Customer c, Ticket t) {
        // readAll();
        new Thread() {
            @Override
            public void run() {
                Movie movie = findMovie(t.movie_name);
                Presentation p = getPresentation(movie, t.time, convert(t.hall_name));
                new Ticketing().unbookTicket(c, movie, p, t, personsPerHour);
                saveCpH();
                saveC();
                saveH();
                saveM();
            }
        }.start();
    }



    @Override
    public int priceWithDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        // readAll();
        return new Ticketing().priceWithDiscounts(movie, date, hall_number, positions, halls);
    }

    @Override
    public int priceWithDiscounts(ArrayList<Ticket> tickets) {
        // readAll();
        return new Ticketing().priceWithDiscounts(tickets);
    }

    @Override
    public int priceWithoutDiscounts(Movie movie, Date date, int hall_number,
            ArrayList<Pair<Integer, Integer>> positions) {
        // readAll();        
        return new Ticketing().priceWithoutDiscounts(movie, date, hall_number, positions, halls);
    }  

    @Override
    public int priceWithoutDiscounts(ArrayList<Ticket> tickets) {
        // readAll();
        return new Ticketing().priceWithoutDiscounts(tickets);
    }



    @Override
    public boolean addPresentation(Movie movie, Date date, int hall_number) {
        // readAll();
        int duration = movie.duration;
        if(hall_number >= 20 || hall_number < 0) return false;
        boolean c = halls[hall_number - 1].add_presentation(movie.name, movie.ID, date, duration);
        if(c) movie.addPres(date, hall_number);
        saveM();
        saveH();
        return c;
    }

    @Override
    public void removePresentation(Movie m, Presentation p) {
        // readAll();
        new Thread() {
            @Override
            public void run() {
                int n = halls[p.hall_number - 1].remove_presentation(p);
                m.removePres(p.time, n);
                personsPerHour[p.time.hour] -= n;
                for(int i = 0; i < customers.size(); i++) {
                    customers.get(i).removePres(p.movie_name, p.time, p.hall_name);
                }
                saveCpH();
                saveC();
                saveH();
                saveM();
            }
        }.start();   
    }

    @Override
    public ArrayList<Integer> allFreTime(Movie movie, int hall_number, DayOfWeek day)
                                             throws IndexOutOfBoundsException {
        // readAll();                                        
        int hours[] = new int[24];
        ArrayList<Integer> ans = new ArrayList<>();

        for(Presentation p : halls[hall_number - 1].getHall_presentations()) {
            if(p.time.day.equals(day)) {
                hours[p.time.hour]++;
                hours[p.time.hour + p.duration]--;
            }
        }

        for(int i = 1; i < hours.length; i++) {
            hours[i] += hours[i - 1];  
        }

        for(int i = 0; i < 25 - movie.duration; i++) { // i < 22
            boolean ok = true;
            for(int j = 0; j < movie.duration; j++) { // 21 + 2 = 23
                if(hours[i + j] == 1) {
                    ok = false; break;
                }
            }
            if(ok) {
                ans.add(i);
            }
        }

        return ans;
    }

    @Override
    public Presentation getPresentation(Movie m, Date date, int hall_number) {
        // readAll();
        return halls[hall_number - 1].presentationOfMovie(m.ID, date);
    }



    @Override
    public void addComment(Customer customer, Movie movie, String Comment) {
        // readAll();
        new Thread() {
            @Override
            public void run() {
                movie.addComment(customer.name, Comment);
                saveM();
            }
        }.start();
    }

    @Override
    public synchronized void addRate(Customer user, Movie movie, float rate) {
        // readAll();
        new Thread() {
            @Override
            public void run() {
                movie.addRate(user, rate);
                saveM();
                saveC();
            }
        }.start();
    }

    @Override
    public ArrayList<Pair<String, String>> getCommrnts(Movie m) {
        // readAll();
        return m.comments;
    }

    @Override
    public float getRate(Movie m) {
        // readAll();
        return m.rate; 
    }



    @Override
    public Ticket[][] getTickets(Presentation p) {
        // readAll();
        return p.p_tickets;
    }

    @Override
    public ArrayList<Presentation> getHall_presentations(int hall_number) {
        // readAll();
        return halls[hall_number - 1].getHall_presentations();
    }

    @Override
    public boolean is_full(Presentation p) {
        // readAll();
        return p.is_full();
    }

    @Override
    public int getChair_number(Ticket t) {
        // readAll();
        return t.getChair_number();
    }

    @Override
    public ArrayList<Customer> getCustomers() {
        // readAll();
        return customers;
    }

    @Override
    public ArrayList<Movie> getMovies() {
        // readAll();
        return movies;
    }

    private int convert(String hall_name) {
        String s = hall_name.substring(4);
        int ans = Integer.parseInt(s);
        return ans;
    }

    @Override
    public String toString() { 
        return "Cinema [customers=" + customers.toString() + ", movies=" + movies.toString()
         + ", halls=" + Arrays.toString(halls) + ", personsPerHour=" + Arrays.toString(personsPerHour) + "]";
    }

}
