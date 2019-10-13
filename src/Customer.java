import java.time.LocalDate;

public class Customer {
    private String personNr;
    private String name;
    private LocalDate datePayment;

    public Customer(String personNr, String name, LocalDate datePayment){
        this.personNr = personNr;
        this.name = name;
        this.datePayment = datePayment;
    }

    public String getPersonNr() {
        return personNr;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }
}
