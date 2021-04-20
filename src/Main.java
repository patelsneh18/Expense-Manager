import Models.Transaction;
import Models.TransactionManager;
import Models.TransactionType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();
        Scanner sc = new Scanner(System.in);
        String menu = "\nChoose from the following: "
                + "\n0: Exit from application"
                + "\n1: Add New Transaction"
                + "\n2: Delete a Transaction"
                + "\n3: Edit a Transaction"
                + "\n4: Get Expenditure of Month"
                + "\n5: Show Transactions by Month"
                + "\n6: Show all Transactions"
                + "\nEnter your choice : ";

        while (true){
            System.out.print(menu);
            int choice = sc.nextInt();
            switch (choice){
                case 0:
                    System.out.println("Thank You!");
                    return;

                case 1:
                    transactionManager.add();
                    break;

                case 2:
                    transactionManager.deleteTransaction();
                    break;

                case 3:
                    transactionManager.editTransaction();
                    break;

                case 4:
                    transactionManager.monthlyCalc();
                    break;

                case 5:
                    transactionManager.showTransByMonth();
                    break;

                case 6:
                    transactionManager.showAllTrans();
                    break;

                default:
                    System.out.println("Incorrect choice");
                    break;
            }
        }
    }
}
