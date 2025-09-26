package sep29.Banking;

public class TransactionProcessor {
    private final Notifer notifer;
    TransactionProcessor(Notifer notifer){
        this.notifer = notifer;
    }
    public void processOfDeposit(BankAccount account, double amount){
        account.setAmount(account.getBalance()+amount);
        notifer.notify(account.getAccountNo(), "deposit amount " + amount + " to your account and the balance is "+account.getBalance());
    }
    public void processOfWithdraw(BankAccount account, double amount){
        account.setAmount(account.getBalance()-amount);
        notifer.notify(account.getAccountNo(), "withdraw amount " + amount + " from your account and the balance is "+account.getBalance());
    }
}
