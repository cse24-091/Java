package hellofx.src;
public class Teller {
    private String name;

    public Teller(String name) {
        this.name = name;
    }

    public void assistCustomer(Customer customer) {
        System.out.println("Teller: " + name + " is assisting customer " + customer.getName());
    }

    public String getName() {
        return name;
    }
}
