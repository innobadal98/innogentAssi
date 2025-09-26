package sep29.Banking;

import java.util.Scanner;

// application starting point is here
public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        BankAccount badal = new BankAccount("123456789", 1000);

//        from here we can create Saving Acount or Current Acccount
        BankAccount sA = new SavingAccount("111111111", 2000);

//        from here we can choose notification type we can easily change Email Notification or SMS notification
        Notifer notifer = new EmailNotifer();

        // from transaction class we can choose which process we want to perform
        TransactionProcessor transactionType = new TransactionProcessor(notifer);


        System.out.println("1. deposit");
        System.out.println("2. withdraw");
        int n = Integer.parseInt(sc.nextLine());
        switch (n){
            case 1: {
                System.out.println("give amount");
                double a = Double.parseDouble(sc.nextLine());
                transactionType.processOfDeposit(sA, a);
            }break;
            case 2: {
                System.out.println("give amount");
                double a = Double.parseDouble(sc.nextLine());
                transactionType.processOfWithdraw(sA, a);
            }break;
            default:{
                System.out.println("give valid input");
            }
        }
    }
}
