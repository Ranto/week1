package ara.train.week1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyTest {

    @Test
    void shouldNotAcceptNullAmount() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Money(null, Currency.getInstance("MGA")));

        assertEquals("Le montant est requis.", exception.getMessage());
    }

    @Test
    void shouldNotAcceptNullCurrency() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Money(BigDecimal.ZERO, null));

        assertEquals("L'unité monétaire est requise.", exception.getMessage());
    }

    @Test
    void shouldMakeDifferenceBetweenSameAmountButDifferentCurrency() {
        BigDecimal amount = new BigDecimal("100");

        Money eur = new Money(amount, Currency.getInstance("EUR"));
        Money mga = new Money(amount, Currency.getInstance("MGA"));

        assertNotEquals(eur, mga);
    }

    @Test
    void shouldAddTwoMoneyWithSameCurrency() {
        Money money1 = new Money(BigDecimal.valueOf(100), Currency.getInstance("MGA"));
        Money money2 = new Money(BigDecimal.valueOf(400), Currency.getInstance("MGA"));

        Money sum = money1.add(money2);

        // Somme effectuées
        assertEquals(BigDecimal.valueOf(500), sum.amount());
        assertEquals(Currency.getInstance("MGA"), sum.currency());

        // Les deux objets initiaux ne changent pas
        assertEquals(BigDecimal.valueOf(100), money1.amount());
        assertEquals(BigDecimal.valueOf(400), money2.amount());
    }

    @Test
    void shouldThrowExceptionWhenAddingMoneyWithDifferentCurrency() {
        Money money1 = new Money(BigDecimal.valueOf(100), Currency.getInstance("MGA"));
        Money money2 = new Money(BigDecimal.valueOf(400), Currency.getInstance("EUR"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> money1.add(money2));

        assertEquals("Les unités monétaires doivent être identiques.", exception.getMessage());
    }

    @Test
    void shouldConsiderAmountsWithDifferentScalesEqual() {
        Money money1 = new Money(new BigDecimal("100.0"), Currency.getInstance("MGA"));
        Money money2 = new Money(new BigDecimal("100.00"), Currency.getInstance("MGA"));

        assertEquals(money1, money2);
        assertEquals(money1.hashCode(), money2.hashCode());
    }

    @Test
    void shouldThrowExceptionWhenAddingWithANullValue() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Money(BigDecimal.TEN, Currency.getInstance("MGA")).add(null));
        assertEquals("La valeur à ajouter doit exister.", exception.getMessage());
    }

    @Test
    void shouldRespectEqualsContract() {
        Currency mga = Currency.getInstance("MGA");

        Money a = new Money(new BigDecimal("100"), mga);
        Money b = new Money(new BigDecimal("100.0"), mga);
        Money c = new Money(new BigDecimal("100.00"), mga);

        // Symétrie
        assertEquals(a, b);
        assertEquals(b, a);

        // Transitivité
        assertEquals(b, c);
        assertEquals(a, c);

        // Comparaison avec null
        assertNotEquals(null, a);

        // Cohérence des hash codes
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(b.hashCode(), c.hashCode());
    }

    @Test
    void shouldNotAddEquivalentMoneyToHashSet() {
        Money money1 = new Money(
                new BigDecimal("100.0"),
                Currency.getInstance("MGA")
        );
        Money money2 = new Money(
                new BigDecimal("100.00"),
                Currency.getInstance("MGA")
        );

        Set<Money> monies = new HashSet<>();

        assertTrue(monies.add(money1));
        assertFalse(monies.add(money2));
        assertEquals(1, monies.size());
    }
}