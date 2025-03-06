import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private TransactionType type;
    private double amount;
    private Date timestamp;

    public Transaction(TransactionType type, double amount, Date timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("%s: $%.2f on %s", 
                type.toString(), 
                amount, 
                dateFormat.format(timestamp));
    }
}

