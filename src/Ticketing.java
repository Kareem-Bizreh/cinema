import java.util.ArrayList;

public class Ticketing {

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
    
}
