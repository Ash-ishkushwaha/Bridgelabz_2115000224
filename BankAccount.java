class BankAccount {
    String accountNumber;
    double balance;

    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {
    double interestRate;

    SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    void displayAccountType() {
        System.out.println("This is a Savings Account.");
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

class CheckingAccount extends BankAccount {
    double withdrawalLimit;

    CheckingAccount(String accountNumber, double balance, double withdrawalLimit) {
        super(accountNumber, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    void displayAccountType() {
        System.out.println("This is a Checking Account.");
        System.out.println("Withdrawal Limit: $" + withdrawalLimit);
    }
}

class FixedDepositAccount extends BankAccount {
    double depositAmount;
    int maturityPeriod;

    FixedDepositAccount(String accountNumber, double balance, double depositAmount, int maturityPeriod) {
        super(accountNumber, balance);
        this.depositAmount = depositAmount;
        this.maturityPeriod = maturityPeriod;
    }

    void displayAccountType() {
        System.out.println("This is a Fixed Deposit Account.");
        System.out.println("Deposit Amount: $" + depositAmount);
        System.out.println("Maturity Period: " + maturityPeriod + " months");
    }
}

public class BankSystem {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount("SA123", 5000.00, 4.5);
        CheckingAccount checkingAccount = new CheckingAccount("CA456", 2000.00, 1000.00);
        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount("FD789", 10000.00, 10000.00, 12);

        savingsAccount.displayInfo();
        savingsAccount.displayAccountType();
        System.out.println();

        checkingAccount.displayInfo();
        checkingAccount.displayAccountType();
        System.out.println();

        fixedDepositAccount.displayInfo();
        fixedDepositAccount.displayAccountType();
    }
}