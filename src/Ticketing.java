import java.util.ArrayList;

public class Ticketing {

    public void bookTicket(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions,
                            Hall[] halls, int[] personsPerHour) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        for(Pair<Integer, Integer> pos : positions) {
            Ticket t = p.p_tickets[pos.getKey() - 1][pos.getValue() - 1];
            t.sold = true;
            c.user_tickets.add(t);
            p.numberSoldTicke++;
            movie.counter++;
            movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 1 : v + 1);
            personsPerHour[t.time.hour]++;
        }
    }

    public void bookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking, int[] personsPerHour) {
        for(Ticket t : booking) {
            t.sold = true;
            c.user_tickets.add(t);
            p.numberSoldTicke++;
            movie.counter++;
            movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 1 : v + 1);
            personsPerHour[t.time.hour]++;
        }
    }

    public void unbookTicket(Customer c, Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions,
                            Hall[] halls, int[] personsPerHour) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        for(Pair<Integer, Integer> pos : positions) {
            Ticket t = p.p_tickets[pos.getKey() - 1][pos.getValue() - 1];
            t.sold = false;
            c.user_tickets.remove(t);
            p.numberSoldTicke--;
            movie.counter--;
            movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 0 : v - 1);
            personsPerHour[t.time.hour]--;
        }
    }

    public void unbookTicket(Customer c, Movie movie, Presentation p, ArrayList<Ticket> booking, int[] personsPerHour) {
        for(Ticket t : booking) {
            t.sold = false;
            c.user_tickets.remove(t);
            p.numberSoldTicke--;
            movie.counter--;
            movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 0 : v - 1);
            personsPerHour[t.time.hour]--;
        }
    }

    public int priceWithDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions,
                                    Hall[] halls) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        int sum = 0;
        for(Pair<Integer, Integer> pos : positions) {
            Ticket t = p.p_tickets[pos.getKey() - 1][pos.getValue() - 1];
            sum += t.getPrice();
        }

        if(positions.size() >= 10) {
            sum /= 2;
        }
        else if(positions.size() >= 5) {
            sum *= 3;
            sum /= 4;
        }
        return sum;
    }

    public int priceWithDiscounts(ArrayList<Ticket> tickets) {
        int sum = 0;
        for(Ticket t : tickets) {
            sum += t.getPrice();
        }
        if(tickets.size() >= 10) {
            sum /= 2;
        }
        else if(tickets.size() >= 5) {
            sum *= 3;
            sum /= 4;
        }
        return sum;
    }

    public int priceWithoutDiscounts(Movie movie, Date date, int hall_number, ArrayList<Pair<Integer, Integer>> positions,
                                        Hall[] halls) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        int sum = 0;
        for(Pair<Integer, Integer> pos : positions) {
            Ticket t = p.p_tickets[pos.getKey() - 1][pos.getValue() - 1];
            sum += t.ticket_price;
        }
        return sum;
    }
    
    public int priceWithoutDiscounts(ArrayList<Ticket> tickets) {
        int sum = 0;
        for(Ticket t : tickets) {
            sum += t.ticket_price;
        }
        return sum;
    }

}
