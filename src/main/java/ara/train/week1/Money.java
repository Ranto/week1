package ara.train.week1;

import java.math.BigDecimal;
import java.util.Currency;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public record Money(BigDecimal amount, Currency currency) {

    public Money {
        requireNonNull(amount, "Le montant est requis.");
        requireNonNull(currency, "L'unité monétaire est requise.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Money(BigDecimal amount1, Currency currency1)) {
            return currency1.equals(currency) && amount1.compareTo(amount) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(amount.stripTrailingZeros(), currency);
    }

    public Money add(Money money) {
        requireNonNull(money, "La valeur à ajouter doit exister.");
        if (!currency.equals(money.currency())) {
            throw new IllegalArgumentException("Les unités monétaires doivent être identiques.");
        }

        return new Money(amount.add(money.amount()), currency);
    }
}
