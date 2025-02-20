import java.util.*;

class Banking {
    private Map<Integer, Double> accountMap = new HashMap<>();
    private TreeMap<Double, Integer> sortedAccounts = new TreeMap<>();
    private Queue<Integer> withdrawalQueue = new LinkedList<>();

    public void addAccount(int accountNumber, double balance) {
        accountMap.put(accountNumber, balance);
        sortedAccounts.put(balance, accountNumber);
    }

    public void deposit(int accountNumber, double amount) {
        if (accountMap.containsKey(accountNumber)) {
            double newBalance = accountMap.get(accountNumber) + amount;
            sortedAccounts.remove(accountMap.get(accountNumber));
            accountMap.put(accountNumber, newBalance);
            sortedAccounts.put(newBalance, accountNumber);
            System.out.println(amount + accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void requestWithdrawal(int accountNumber) {
        if (accountMap.containsKey(accountNumber)) {
            withdrawalQueue.add(accountNumber);
            System.out.println("Withdrawal requested for account " + accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void processWithdrawals() {
        System.out.println("Processing Withdrawals:");
        while (!withdrawalQueue.isEmpty()) {
            int accountNumber = withdrawalQueue.poll();
            if (accountMap.containsKey(accountNumber)) {
                double balance = accountMap.get(accountNumber);
                System.out
                        .println("Withdrawal processed for account " + accountNumber + " (Balance:" + balance + ")");
            }
        }
    }

    public void displaySortedAccounts() {
        System.out.println("\nAccounts Sorted by Balance:");
        for (Map.Entry<Double, Integer> entry : sortedAccounts.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }

    public static void main(String[] args) {
        Banking bank = new Banking();

        bank.addAccount(101, 5000.00);
        bank.addAccount(102, 1200.00);
        bank.addAccount(103, 3000.00);

        bank.deposit(102, 800.00);

        bank.requestWithdrawal(101);
        bank.requestWithdrawal(103);

        bank.displaySortedAccounts();

        bank.processWithdrawals();
    }
}
