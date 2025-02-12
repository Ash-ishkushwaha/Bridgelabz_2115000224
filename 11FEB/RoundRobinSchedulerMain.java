class Process {
    private int processId;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
    private Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.next = null;
    }

    public int getProcessId() {
        return processId;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public Process getNext() {
        return next;
    }

    public void setNext(Process next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Process ID: " + processId + ", Burst Time: " + burstTime + ", Priority: " + priority + ", Waiting Time: " + waitingTime + ", Turnaround Time: " + turnaroundTime;
    }
}

class CircularLinkedListScheduler {
    private Process head;
    private Process tail;
    private int processCount;

    public CircularLinkedListScheduler() {
        this.head = null;
        this.tail = null;
        this.processCount = 0;
    }

    public void addProcessAtEnd(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.setNext(newProcess);
        } else {
            newProcess.setNext(head);
            tail.setNext(newProcess);
            tail = newProcess;
        }
        processCount++;
    }

    public void removeProcessById(int processId) {
        if (head == null) return;
        if (head.getProcessId() == processId) {
            if (head == tail) {
                head = tail = null;
            } else {
                Process temp = head;
                while (temp.getNext() != head) {
                    temp = temp.getNext();
                }
                temp.setNext(head.getNext());
                head = head.getNext();
            }
            processCount--;
            return;
        }
        Process current = head;
        while (current.getNext() != head && current.getNext().getProcessId() != processId) {
            current = current.getNext();
        }
        if (current.getNext().getProcessId() == processId) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == head) {
                tail = current;
            }
            processCount--;
        }
    }

    public void simulateRoundRobin(int timeQuantum) {
        Process current = head;
        int currentTime = 0;

        while (current != null && processCount > 0) {
            if (current.getBurstTime() > timeQuantum) {
                current.setBurstTime(current.getBurstTime() - timeQuantum);
                currentTime += timeQuantum;
                current = current.getNext();
            } else {
                currentTime += current.getBurstTime();
                current.setTurnaroundTime(currentTime);
                removeProcessById(current.getProcessId());
                current = current == head ? head : current.getNext();
            }
            updateWaitingTime();
            displayProcesses();
        }
        calculateAverageTimes();
    }

    private void updateWaitingTime() {
        Process current = head;
        do {
            if (current != null) {
                current.setWaitingTime(current.getTurnaroundTime() - current.getBurstTime());
                current = current.getNext();
            }
        } while (current != head);
    }

    private void displayProcesses() {
        Process current = head;
        do {
            if (current != null) {
                System.out.println(current);
                current = current.getNext();
            }
        } while (current != head);
    }

    private void calculateAverageTimes() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalProcesses = processCount;

        Process current = head;
        while (current != null && totalProcesses > 0) {
            totalWaitingTime += current.getWaitingTime();
            totalTurnaroundTime += current.getTurnaroundTime();
            current = current.getNext();
        }

        double averageWaitingTime = (double) totalWaitingTime / totalProcesses;
        double averageTurnaroundTime = (double) totalTurnaroundTime / totalProcesses;

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}

public class RoundRobinSchedulerMain {
    public static void main(String[] args) {
        CircularLinkedListScheduler scheduler = new CircularLinkedListScheduler();
        scheduler.addProcessAtEnd(1, 10, 1);
        scheduler.addProcessAtEnd(2, 5, 2);
        scheduler.addProcessAtEnd(3, 8, 3);
        scheduler.addProcessAtEnd(4, 12, 1);

        System.out.println("Simulating Round Robin Scheduling with Time Quantum = 4:");
        scheduler.simulateRoundRobin(4);
    }
}