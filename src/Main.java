public class Main {
    public static void main(String[] args) {
        Cinema c = new Cinema();
        c.addCustomer("maneger","1234");
        c.saveC();
        new Welcome(c);
    }
}