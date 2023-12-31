import java.time.DayOfWeek;
import java.util.ArrayList;

public interface CanOperation {


   /*
    * //! be carefull sume functin could throw an exception if you don't treat it correctly ...
    */

    // 3 boolean functions to add or remove movie
    boolean addMovie(String name, int duration, TypeMovie type);
    void removeMovie(Movie x); //bad time
    void removeMovie(int index_of_movie); //speed

    // fun to return the names of most popular movies in the cinema
    ArrayList<String> mostPopularMovie();

    /**  
     * this function to return the most popular time for a movie 
     * @param x : movie
     */
    ArrayList<Date> mostPopularDateForMovie(Movie x);

    /** fun to return the most crowding hour in the cinema */
    int mostCrowdingHourForCinema();

    // add user in the application user == customer
    boolean addCustomer(String name, String password);

    // remove the user from the application user == customer
    boolean removeCustomer(Customer customer);

    // return User Info
    Customer findCustomer(String name, String password);

    boolean changeName(Customer customer, String name);

    void changePassword(Customer customer, String password);

    // to book a ticket for a user
    /**
     * this function  with bad time complexity to book a ticket
     */
    void bookTickets(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions, Thread t); // bad time
    /**
     * this function with speed time complexity to book a tickets
     */
    void bookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking, Thread t); // speed time

    // to unbook a ticket
    /**
     * this function  with bad time complexity to unbook a ticket
     */
    void unbookTickets(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions); // bad time
    /**
     * this function with speed time complexity to unbook a tickets
     */
    void unbookTicket(Customer c, Ticket t); // speed time

    // return the price of group from tickets WITH discounts
    int priceWithDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions); // bad time
    int priceWithDiscounts(ArrayList<Ticket> tickets); // speed time

    // return the price of group from tickets WITHOUT discounts
    int priceWithoutDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions); // bad time
    int priceWithoutDiscounts(ArrayList<Ticket> tickets); // speed time

    // 2 boolean functions to add or remove presentation
    boolean addPresentation(Movie movie, Date date, int hall_number);
    void removePresentation(Movie m, Presentation p);
    ArrayList<Integer> allFreTime(Movie movie, int hall_number, DayOfWeek day);
    
    void addComment(Customer customer, Movie movie, String Comment);
    void addRate(Customer user, Movie movie, float rate);

    Presentation getPresentation(Movie m, Date date, int hall_number);
    Ticket[][] getTickets(Presentation p);
    ArrayList<Pair<String, String>> getCommrnts(Movie m);
    float getRate(Movie m);
    ArrayList<Presentation> getHall_presentations(int hall_number);
    boolean is_full(Presentation p);
    int getChair_number(Ticket t);
    ArrayList<Customer> getCustomers();
    ArrayList<Movie> getMovies();
    Movie findMovie(String name);

}
