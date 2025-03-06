public enum TransactionType {
    WITHDRAWAL("Withdrawal"),
    DEPOSIT("Deposit"),
    BALANCE_INQUIRY("Balance Inquiry");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

