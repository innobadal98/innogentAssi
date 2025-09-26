package sep29.Banking;

//creating a single responsibility class
public class BankAccount {
    private String accountNo;
    private double balance;

    public BankAccount(String accountNo, double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }
    public String getAccountNo(){
        return accountNo;
    }
    public void setAmount(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }
}
