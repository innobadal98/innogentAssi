package sep29.Banking;

interface DepositService {
void deposit(BankAccount account, double amount);
}

interface WithdrawService {
    void withdraw(BankAccount account, double amount);
}
