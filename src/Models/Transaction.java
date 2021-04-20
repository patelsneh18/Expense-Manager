package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public String date;            //Date of transaction
    public int transactionType;    //Spend or Income
    public float amount;           //Amount of transaction
    public String note;            //Special note about transaction

    //parameterised constructor for transaction class
    public Transaction(String date, float amount, int transactionType, String note) {
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.note = note;
    }

    @Override
    public String toString() {
        String s;
        if (transactionType == TransactionType.TYPE_INCOME) s = "Income";
        else s = "Expense";
        return "Transaction [Amount: " + amount + "; Date: " + date + "; Type: " + s + "; Note: " + note + "]";
    }
}
