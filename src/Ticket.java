import java.io.Serializable;

public class Ticket implements Serializable{
    Pair<Integer, Integer> position = new Pair<Integer, Integer>(); 

    int ticket_price;
    Date time;
    boolean sold;
    int chair_number;
    String movie_name;
    String hall_name;

    public Ticket(Pair<Integer, Integer> position, Date time, String movie_name, String hall_name) {
        this.position = position;
        this.chair_number = (this.position.getKey() - 1) * 20 + this.position.getValue();
        this.time = time;
        this.movie_name = movie_name;
        this.hall_name = hall_name;
        this.ticket_price = 10000;
        this.sold = false;
    }

    public int getPrice() {
        if(this.time.hour <= 12 && this.time.hour >= 5)
        {
            return this.ticket_price / 2;
        }
        return this.ticket_price;
    }

    public int getChair_number() {
        return chair_number;
    }

    @Override
    public String toString() {
        return "Ticket [position=" + position.toString() + ", ticket_price=" + ticket_price
                 + ", time=" + time.toString() + ", sold=" + sold + ", chair_number="
                  + chair_number + ", movie_name=" + movie_name + ", hall_name=" + hall_name + "]";
    }

}
