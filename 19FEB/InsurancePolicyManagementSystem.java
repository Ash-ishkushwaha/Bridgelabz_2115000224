import java.util.*;

class InsurancePolicy implements Comparable<InsurancePolicy> {
    private String policyNumber;
    private String policyHolderName;
    private Date expiryDate;
    private String coverageType;
    private double premiumAmount;

    public InsurancePolicy(String policyNumber, String policyHolderName, Date expiryDate, String coverageType,
            double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyHolderName = policyHolderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "PolicyNumber: " + policyNumber + ", Holder: " + policyHolderName +
                ", Expiry: " + expiryDate + ", Type: " + coverageType + ", Premium: $" + premiumAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof InsurancePolicy))
            return false;
        InsurancePolicy other = (InsurancePolicy) obj;
        return Objects.equals(policyNumber, other.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public int compareTo(InsurancePolicy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }
}

public class InsurancePolicyManagementSystem {
    private Set<InsurancePolicy> policySet = new HashSet<>(); // Unique policies, fast lookup
    private Set<InsurancePolicy> orderedPolicySet = new LinkedHashSet<>(); // Maintains insertion order
    private Set<InsurancePolicy> sortedPolicySet = new TreeSet<>(); // Sorted by expiry date
    private Map<String, Integer> duplicatePolicies = new HashMap<>(); // Count duplicate policy numbers

    // Add a new policy
    public void addPolicy(InsurancePolicy policy) {
        if (!policySet.add(policy)) {
            duplicatePolicies.put(policy.getPolicyNumber(),
                    duplicatePolicies.getOrDefault(policy.getPolicyNumber(), 0) + 1);
        }
        orderedPolicySet.add(policy);
        sortedPolicySet.add(policy);
    }

    // List all unique policies
    public void listAllPolicies() {
        for (InsurancePolicy policy : policySet) {
            System.out.println(policy);
        }
    }

    // List policies expiring in the next 30 days
    public void listExpiringSoon() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date next30Days = calendar.getTime();

        for (InsurancePolicy policy : sortedPolicySet) {
            if (policy.getExpiryDate().after(today) && policy.getExpiryDate().before(next30Days)) {
                System.out.println(policy);
            }
        }
    }

    // List policies by coverage type
    public void listPoliciesByCoverageType(String type) {
        for (InsurancePolicy policy : policySet) {
            if (policy.getCoverageType().equalsIgnoreCase(type)) {
                System.out.println(policy);
            }
        }
    }

    // List duplicate policies
    public void listDuplicatePolicies() {
        for (Map.Entry<String, Integer> entry : duplicatePolicies.entrySet()) {
            System.out.println("Policy Number: " + entry.getKey() + ", Duplicates: " + entry.getValue());
        }
    }

    // Performance comparison
    public void comparePerformance() {
        int n = 100000;
        Set<InsurancePolicy> hashSet = new HashSet<>();
        Set<InsurancePolicy> linkedHashSet = new LinkedHashSet<>();
        Set<InsurancePolicy> treeSet = new TreeSet<>();
        Calendar cal = Calendar.getInstance();

        long start, end;

        // Adding elements
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cal.set(2025, Calendar.JANUARY, (i % 30) + 1);
            hashSet.add(new InsurancePolicy("P" + i, "Holder" + i, cal.getTime(), "Health", 100 + i));
        }
        end = System.nanoTime();
        System.out.println("HashSet add time: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cal.set(2025, Calendar.JANUARY, (i % 30) + 1);
            linkedHashSet.add(new InsurancePolicy("P" + i, "Holder" + i, cal.getTime(), "Health", 100 + i));
        }
        end = System.nanoTime();
        System.out.println("LinkedHashSet add time: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cal.set(2025, Calendar.JANUARY, (i % 30) + 1);
            treeSet.add(new InsurancePolicy("P" + i, "Holder" + i, cal.getTime(), "Health", 100 + i));
        }
        end = System.nanoTime();
        System.out.println("TreeSet add time: " + (end - start) / 1e6 + " ms");
    }

    public static void main(String[] args) {
        InsurancePolicyManagementSystem system = new InsurancePolicyManagementSystem();
        Calendar cal = Calendar.getInstance();

        cal.set(2024, Calendar.MARCH, 15);
        InsurancePolicy p1 = new InsurancePolicy("P1001", "Policy1", cal.getTime(), "Health", 500.0);

        cal.set(2024, Calendar.APRIL, 5);
        InsurancePolicy p2 = new InsurancePolicy("P1002", "Policy2", cal.getTime(), "Auto", 750.0);

        cal.set(2023, Calendar.DECEMBER, 30);
        InsurancePolicy p3 = new InsurancePolicy("P1003", "Policy3", cal.getTime(), "Home", 600.0);

        // Add policies
        system.addPolicy(p1);
        system.addPolicy(p2);
        system.addPolicy(p3);
        system.addPolicy(p1); // Duplicate

        system.listAllPolicies();

        system.listExpiringSoon();

        system.listPoliciesByCoverageType("Health");

        system.listDuplicatePolicies();

        system.comparePerformance();
    }
}
