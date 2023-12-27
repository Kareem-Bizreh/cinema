import java.util.ArrayList;

public class Ticketing {

    public void bookTicket(Customer c, Movie movie, Date date, int hall_number, Pair<Integer, Integer> position,
                            Hall[] halls, int[] personsPerHour) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        Ticket t = p.p_tickets[position.getKey()][position.getValue()];
        t.sold = true;
        c.user_tickets.add(t);
        p.numberSoldTicke++;
        movie.counter++;
        movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 1 : v + 1);
        personsPerHour[t.time.hour]++;
    }

    public void unbookTicket(Customer c, Movie movie, Date date, int hall_number, Pair<Integer, Integer> position,
                            Hall[] halls, int[] personsPerHour) {
        Presentation p = halls[hall_number - 1].presentationOfMovie(movie.ID, date);
        Ticket t = p.p_tickets[position.getKey()][position.getValue()];
        t.sold = false;
        c.user_tickets.remove(t);
        p.numberSoldTicke--;
        movie.counter--;
        movie.showtimes.compute(t.time, (k, v) -> (v == null) ? 0 : v - 1);
        personsPerHour[t.time.hour]--;
    }

    public int priceWithDiscounts(Ticket a[][], ArrayList <Pair<Integer, Integer>> al) {
        int sum = 0;

        for(int i = 0; i < al.size(); i++) {
            sum += a[al.get(i).getKey() - 1][al.get(i).getValue() - 1].getPrice();
        }

        if(al.size() >= 10) {
            sum /= 2;
        }

        else if(al.size() >= 5) {
            sum *= 3;
            sum /= 4;
        }
        return sum;
    }
    
/*
    public void bookTicket(Ticket a[][], ArrayList <Pair<Integer, Integer>> al) {
        for(int i = 0; i < al.size(); i++) {
            a[al.get(i).getKey() - 1][al.get(i).getValue() - 1].sold = true;
        }
    }

    public void unbookTicket(Ticket a[][], ArrayList <Pair<Integer, Integer>> al) {
        for(int i = 0; i < al.size(); i++) {
            a[al.get(i).getKey() - 1][al.get(i).getValue() - 1].sold = false;
        }
    }
*/

}
