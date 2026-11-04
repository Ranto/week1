package ara.train.week1;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public record Customer(UUID id, String name, String email) {

    public Customer {
        requireNonNull(id, "L'id est requis");
        requireNonNull(name, "Le nom est requis");
        requireNonNull(email, "L'adresse mail est requise");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Le nom ne doit pas être vide.");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("L'adresse email ne doit pas être vide.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Customer customer && customer.id().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
