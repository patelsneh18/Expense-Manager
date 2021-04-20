package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionManager {
    Scanner sc = new Scanner(System.in);
    HashMap<String,ArrayList<Transaction>> transactions;
    ArrayList<Transaction> allTransactions;

    public TransactionManager(){
        transactions = new HashMap<>();
        allTransactions = new ArrayList<>();
    }

    //Method to add a transaction
    public void add(){
        System.out.print("Enter Transaction Amount: ");
        float amount = sc.nextFloat();

        System.out.print("Enter Date (yyyy-mm-dd): ");
        String date = sc.nextLine();
        while (date.isEmpty()) date = sc.nextLine();

        System.out.println("Select Transaction Type:" + "\n1: Income" + "\n2: Expense");
        System.out.print("Enter your choice: ");
        int type = sc.nextInt();

        System.out.print("Enter note: ");
        String note = sc.nextLine();
        while (note.isEmpty()) note = sc.nextLine();

        Transaction transaction = new Transaction(date,amount,type-1,note);
        allTransactions.add(transaction);
        LocalDate localDate = LocalDate.parse(transaction.date,DateTimeFormatter.ISO_DATE);

        //extracting key for hashmap
        String key = localDate.getMonth().toString() + " " + localDate.getYear();

        //If particular month of a year already has previous transactions
        if (transactions.containsKey(key)){
            transactions.get(key).add(transaction);
        }
        // If its first transaction of particular month of a year
        else{
            ArrayList<Transaction> arr = new ArrayList<>();
            arr.add(transaction);
            transactions.put(key,arr);
        }
        System.out.println(Colors.GREEN + "Transaction Added!" + Colors.RESET);
    }

    // Monthly Calculator.. provides spent, earnings and savings
    public void monthlyCalc(){
        System.out.print("Enter month and year (Format: JULY 2020) : ");
        String key = sc.nextLine();
        while (key.isEmpty()) key = sc.nextLine();
        float spent = 0;
        float earned = 0;

        String[] ddyy = key.split(" ");
        ArrayList<Transaction> trans = transactions.get(key);
        // Calculate spent and earnings
        for (int i=0;i<trans.size();i++){
            if (trans.get(i).transactionType == TransactionType.TYPE_INCOME) earned+= trans.get(i).amount;
            else spent += trans.get(i).amount;
        }


        System.out.println("Spent: " +  spent + "\nEarned: " + earned + "\nSavings: " + (earned-spent));
    }

    public void editTransaction(){
        System.out.print("Enter transaction month and year (Format: JULY 2020) : ");
        String key = sc.nextLine();
        while(key.isEmpty()) key = sc.nextLine();
        ArrayList<Transaction> trans = transactions.get(key);

        //print all transactions of month
        for (int i=0;i< trans.size();i++){
            System.out.println((i+1) + ". " + trans.get(i));
        }
        System.out.print("Choose from above transactions : ");
        int c = sc.nextInt();
        Transaction transaction = trans.get(c-1);
        String menu = "Choose what you want to edit:"
                + "\n0: Go Back"
                + "\n1: Amount"
                + "\n2: Date"
                + "\n3: Note"
                + "\nEnter your choice: ";
        int choice = 1;
        while (choice!=0){
            System.out.print(menu);
            choice = sc.nextInt();
            //edit amount
            if (choice == 1){
                System.out.print("Enter new amount: ");
                int am = sc.nextInt();
                transaction.amount = am;
                System.out.println(Colors.GREEN + "Amount updated to " + am + Colors.RESET);
            }
            //edit date
            else if (choice == 2){
                System.out.print("Enter new date: ");
                String date = sc.nextLine();
                while(date.isEmpty()) date = sc.nextLine();
                transaction.date = date;
                System.out.println(Colors.GREEN + "Date updated to " + date + Colors.RESET);
            }
            //edit note
            else if (choice == 3){
                System.out.print("Enter new note: ");
                String note = sc.nextLine();
                while(note.isEmpty()) note = sc.nextLine();
                transaction.note = note;
                System.out.println(Colors.GREEN + "Note updated to " + note + Colors.RESET);
            }
            else if (choice !=0) System.out.println("Incorrect Choice (Choose from 0-3)");
        }
    }

    //delete a transaction
    public void deleteTransaction(){
        System.out.print("Enter transaction month and year (Format: JULY 2020) : ");
        String key = sc.nextLine();
        while(key.isEmpty()) key = sc.nextLine();
        if (!transactions.containsKey(key)){
            System.out.println("No transactions in given month");
        }
        ArrayList<Transaction> trans = transactions.get(key);

        //print all transactions of given month
        for (int i=0;i< trans.size();i++){
            System.out.println((i+1) + ". " + trans.get(i));
        }
        System.out.print("Choose from above transactions : ");
        int c = sc.nextInt();
        Transaction transaction = trans.get(c-1);
        LocalDate localDate = LocalDate.parse(transaction.date,DateTimeFormatter.ISO_DATE);
        trans.remove(transaction);
        System.out.println("Transaction Removed!");
    }

    //Show all transactions of user
    public void showAllTrans(){
        for (int i=0;i<allTransactions.size();i++){
            System.out.println((i+1) + ". " + allTransactions.get(i));
        }
    }

    //Show transactions of given month
    public void showTransByMonth(){
        System.out.print("Enter Month and Year: ");
        String key = sc.nextLine();
        while (key.isEmpty()) key = sc.nextLine();
        if (!transactions.containsKey(key)){
            System.out.println("No transactions in given month");
        }
        ArrayList<Transaction> trans = transactions.get(key);

        for (int i=0;i< trans.size();i++){
            System.out.println((i+1) + ". " + trans.get(i));
        }

    }
}
