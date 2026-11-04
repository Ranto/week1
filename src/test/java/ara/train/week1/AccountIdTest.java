package ara.train.week1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountIdTest {

    @Test
    void shouldEqualWhenHavingSameValue() {
        AccountId id1 = new AccountId("ABC");
        AccountId id2 = new AccountId("ABC");

        assertEquals(id1, id2);
    }

    @Test
    void shouldThrowExceptionWhenValueNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AccountId(null));
        assertEquals("L'identifiant doit avoir une valeur.", exception.getMessage());
    }

    @Test
    void shouldHavingSameHashCodeWhenEqual() {
        AccountId id1 = new AccountId("ABC");
        AccountId id2 = new AccountId("ABC");

        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void shouldThrowExceptionWhenBlankValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AccountId(""));

        assertEquals("L'identifiant doit avoir une valeur.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenContainingOnlySpace() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new AccountId("    "));

        assertEquals("L'identifiant doit avoir une valeur.", exception.getMessage());
    }

    @Test
    void shouldNotAddTwoSameIdInHashSet() {
        String value = "TEST";
        AccountId id1 = new AccountId(value);
        AccountId id2 = new AccountId(value);

        HashSet<AccountId> accountIds = new HashSet<>();
        assertTrue(accountIds.add(id1));
        assertFalse(accountIds.add(id2));
    }
}