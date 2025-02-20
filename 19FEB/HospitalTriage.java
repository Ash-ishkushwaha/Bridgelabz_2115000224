import java.util.*;

class Patient implements Comparable<Patient> {
    String name;
    int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(other.severity, this.severity);
    }

    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

public class HospitalTriage {
    public static void main(String[] args) {
        PriorityQueue<Patient> queue = new PriorityQueue<>();
        queue.add(new Patient("Patient1", 3));
        queue.add(new Patient("Patient2", 5));
        queue.add(new Patient("Patient3", 2));

        System.out.println("Triage Order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
