import java.util.ArrayList;

public interface CanOperation {

    // boolean fun to add movie
    boolean addMovie(String name, int duration, TypeMovie type);

    // fun to return the names of most popular movies in the cinema
    ArrayList<String> mostPopularMovie();

    // fun to return the most popular time for a movie
    ArrayList<Date> mostPopularDateForMovie(Movie x);

    // fun to return the most crowding hour in the cinema
    int mostCrowdingHourForCinema();

    // add user in the application user == customer
    boolean addCustomer(String name, String password);

    // remove the user from the application user == customer
    boolean removeCustomer(Customer customer);

    // return User Info
    Customer findCustomer(String name, String password);

    // to book a ticket for a user
    void bookTickets(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions);

    // to unbook a ticket //! BE CARFULL THIS FUNCTION COULD THROW AN EXCEPTION IF THE TICKET IS NOT TOKEN
    void unbookTickets(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions);

    // return the price of group from tickets WITH discounts
    int priceWithDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions);

    // return the price of group from tickets WITHOUT discounts
    int priceWithoutDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions);

}
