class BankAccount {
    private static String bankName = "Global Bank";
    private static int totalAccounts = 0;
    
    private final String accountNumber;
    private String accountHolderName;
    private double balance;
    
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        totalAccounts++;
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public void displayAccountDetails() {
        if (this instanceof BankAccount) {
            System.out.println("Bank Name: " + bankName);
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Balance: $" + balance);
        } else {
            System.out.println("Invalid account object.");
        }
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
    
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("John Doe", "123456789", 500.0);
        BankAccount acc2 = new BankAccount("Jane Smith", "987654321", 1000.0);
        
        acc1.displayAccountDetails();
        acc2.displayAccountDetails();
        
        acc1.deposit(200);
        acc1.displayAccountDetails();
        
        acc2.withdraw(300);
        acc2.displayAccountDetails();
        
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
    }
}
