import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GymMembershipDepartmentTestTest {
    GymMembershipDepartment gymMembershipDepartment = new GymMembershipDepartment();
    final String realFileName = "customerData.txt";
    final String fakeFileName = "fake name";
    final String realCustomerName = "Greger Ganache";
    final String fakeCustomerName = "Kening Fohlin";

    @Test
    void shouldGetCustomerListWhenTheListIsLoading(){
        boolean result = gymMembershipDepartment.loadCustomerList(realFileName);
        assertTrue(result);
    }

    @Test
    void ifCustomerListIsMissingItShouldReturnFalse(){
        boolean result = gymMembershipDepartment.loadCustomerList(fakeFileName);
        assertFalse(result);
    }

    @Test
    void ifCanGetACustomerFromListByName(){
        gymMembershipDepartment.loadCustomerList(realFileName);
        Customer customer = gymMembershipDepartment.getCustomer(realCustomerName);
        assertNotNull(customer);
    }

    @Test
    void ifCanNotGetACustomerFromListByNameReturnNull(){
        gymMembershipDepartment.loadCustomerList(realFileName);
        Customer customer = gymMembershipDepartment.getCustomer(fakeCustomerName);
        assertNull(customer);
    }

}