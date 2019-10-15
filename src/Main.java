import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args)  {
        GymMembershipDepartment gymMembershipDepartment = new GymMembershipDepartment();
        boolean loadResult = gymMembershipDepartment.loadCustomerList("customerData.txt");
        if(!loadResult){
            System.exit(0);
        }
        String input;
        Customer customerFound;
        while (true) {
            input = JOptionPane.showInputDialog("What is your name or your personal number?");
            if (input == null || input.equals("")) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    System.exit(0);
                }
            } else {
                input = input.replaceAll("\\s+", " ").trim();
                customerFound = gymMembershipDepartment.getCustomer(input);
                break;
            }
        }
        if (customerFound == null) {
            showMessageDialog(null, "Sorry, you do not have a membership.");
        } else if (gymMembershipDepartment.hasMembershipExpired(customerFound)) {
            showMessageDialog(null, "Sorry, your membership has expired.");
        } else {
            gymMembershipDepartment.showMembershipValidUntil(customerFound);
            gymMembershipDepartment.trackCustomerFrequency(customerFound);
        }
    }
}
