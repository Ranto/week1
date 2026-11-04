package ara.train.week1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    @Test
    void shouldNotAcceptNullName() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Customer(UUID.randomUUID(), null, "test"));

        assertEquals("Le nom est requis", exception.getMessage());
    }

    @Test
    void shouldNotAcceptNullId() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Customer(null, "test", "test"));

        assertEquals("L'id est requis", exception.getMessage());
    }

    @Test
    void shouldNotAcceptNullEmail() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Customer(UUID.randomUUID(), "test", null));

        assertEquals("L'adresse mail est requise", exception.getMessage());
    }

    @Test
    void shouldNotAcceptBlankName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(), "  ", "test"));

        assertEquals("Le nom ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void shouldNotAcceptBlankEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(), "test", "   "));

        assertEquals("L'adresse email ne doit pas être vide.", exception.getMessage());
    }

    @Test
    void shouldConsiderTwoCustomersWithSameIdEqual() {
        UUID id = UUID.randomUUID();

        Customer customer1 = new Customer(id, "Alice", "alice@example.com");
        Customer customer2 = new Customer(id, "Bob", "bob@example.com");

        assertEquals(customer1, customer2);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    void shouldConsiderTwoCustomersWithDifferentIdNotEqual() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Customer customer1 = new Customer(id1, "Alice", "alice@example.com");
        Customer customer2 = new Customer(id2, "Alice", "alice@example.com");

        assertNotEquals(customer1, customer2);
    }

    @Test
    void shouldHaveUniqueCustomerInHashSet() {
        UUID id = UUID.randomUUID();
        HashSet<Customer> customers = new HashSet<>();

        Customer customer1 = new Customer(id, "Alice", "alice@example.com");
        Customer customer2 = new Customer(id, "Bob", "bob@example.com");

        assertTrue(customers.add(customer1));
        assertFalse(customers.add(customer2));
    }
}