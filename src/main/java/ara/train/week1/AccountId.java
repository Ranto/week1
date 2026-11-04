package ara.train.week1;

public record AccountId(String value) {
    public AccountId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("L'identifiant doit avoir une valeur.");
        }
    }
}
