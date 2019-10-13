import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.*;

public class GymMembershipDepartment {
    private List<Customer> customerList = new ArrayList<>();

    public boolean loadCustomerList(String filename) {
        BufferedReader bufIn;
        try {
            bufIn = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            showMessageDialog(null, "We could not find the file 'customerData.txt'. Please go back.");
            return false;
        }

        String info = "";
        String[] info2;

        while (true) {
            try {
                if ((info = bufIn.readLine()) != null) {
                    info2 = info.split(",");
                    info = bufIn.readLine().trim();
                    LocalDate localDate = LocalDate.parse(info);

                    Customer customer = new Customer(info2[0].trim(), info2[1].trim(), localDate);
                    customerList.add(customer);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Something went wrong.");
            }
        }
        return true;
    }

    public Customer getCustomer(String input) {
        Customer customerFound = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (input.equalsIgnoreCase(customerList.get(i).getName()) || input.equals(customerList.get(i).getPersonNr())) {
                customerFound = customerList.get(i);
                break;
            }
        }
        return customerFound;
    }

    public boolean hasMembershipExpired(Customer customer) {
        LocalDate now = LocalDate.now();
        Period period = customer.getDatePayment().until(now);
        return period.getYears() > 0;
    }

    public Period whenIsTheMembershipExpired(Customer customer) {
        LocalDate expiryDate = customer.getDatePayment().plusYears(1);
        return LocalDate.now().until(expiryDate);
    }

    public void showMembershipValidUntil(Customer customer) {
        Period period = whenIsTheMembershipExpired(customer);
        int monthsTillExpired = period.getMonths();
        int daysTillExpired = period.getDays();

        showMessageDialog(null, String.format("Welcome %s ! \nYour membership will be expired in %d months and %d days.",
                customer.getName(), monthsTillExpired, daysTillExpired));
    }

    public void trackCustomerFrequency(Customer customer) {
        Path path = Paths.get(customer.getName() + "-" + customer.getPersonNr() + ".txt");
        BufferedWriter bufWt;
        try {
            if (Files.exists(path)) {
                bufWt = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            } else {
                bufWt = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
            }
            bufWt.append(LocalDate.now().toString()).append(" ").append(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            bufWt.append("\n");
            bufWt.flush();
            bufWt.close();
        } catch (IOException e) {
            e.printStackTrace();
            showMessageDialog(null, "Something went wrong.");
        }
    }
}
