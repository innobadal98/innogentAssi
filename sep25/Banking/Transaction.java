package sep29.Banking;

public class Transaction {
    public void withdraw(BankAccount account, double amount){
        if(account.getBalance()>amount){
            account.setAmount(account.getBalance()-amount);
        }else {
            System.out.println("low balance");
        }
    }
    public void deposit(BankAccount account, double amount){
        if(amount>0){
            account.setAmount(account.getBalance()+amount);
        }else {
            System.out.println("invalid amount");
        }
    }
}
