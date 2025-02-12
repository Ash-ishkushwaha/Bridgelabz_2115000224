class Task {
    private int taskId;
    private String taskName;
    private int priority;
    private String dueDate;
    private Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Task getNext() {
        return next;
    }

    public void setNext(Task next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Priority: " + priority + ", Due Date: " + dueDate;
    }
}

public class TaskScheduler {
    private Task head;

    public TaskScheduler() {
        this.head = null;
    }

    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.setNext(head);
        } else {
            Task tail = head;
            while (tail.getNext() != head) {
                tail = tail.getNext();
            }
            newTask.setNext(head);
            head = newTask;
            tail.setNext(head);
        }
    }

    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.setNext(head);
        } else {
            Task tail = head;
            while (tail.getNext() != head) {
                tail = tail.getNext();
            }
            tail.setNext(newTask);
            newTask.setNext(head);
        }
    }

    public void addTaskAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position <= 0) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task current = head;
        for (int i = 1; i < position && current.getNext() != head; i++) {
            current = current.getNext();
        }
        newTask.setNext(current.getNext());
        current.setNext(newTask);
    }

    public void removeTaskById(int taskId) {
        if (head == null) return;
        if (head.getTaskId() == taskId) {
            if (head.getNext() == head) {
                head = null;
            } else {
                Task tail = head;
                while (tail.getNext() != head) {
                    tail = tail.getNext();
                }
                head = head.getNext();
                tail.setNext(head);
            }
            return;
        }
        Task current = head;
        while (current.getNext() != head && current.getNext().getTaskId() != taskId) {
            current = current.getNext();
        }
        if (current.getNext().getTaskId() == taskId) {
            current.setNext(current.getNext().getNext());
        }
    }

    public Task viewCurrentTask() {
        return head;
    }

    public void moveToNextTask() {
        if (head != null) {
            head = head.getNext();
        }
    }

    public void displayAllTasks() {
        if (head == null) return;
        Task current = head;
        do {
            System.out.println(current);
            current = current.getNext();
        } while (current != head);
    }

    public Task searchTaskByPriority(int priority) {
        if (head == null) return null;
        Task current = head;
        do {
            if (current.getPriority() == priority) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTaskAtBeginning(1, "Task 1", 1, "2023-12-01");
        scheduler.addTaskAtEnd(2, "Task 2", 2, "2023-12-02");
        scheduler.addTaskAtPosition(1, 3, "Task 3", 3, "2023-12-03");

        System.out.println("Displaying all tasks:");
        scheduler.displayAllTasks();
        System.out.println();
        System.out.println("Current task:");
        System.out.println(scheduler.viewCurrentTask());

        scheduler.moveToNextTask();
        System.out.println("After moving to the next task:");
        System.out.println(scheduler.viewCurrentTask());
        System.out.println();
        System.out.println("Searching for task with priority 2:");
        Task searchResult = scheduler.searchTaskByPriority(2);
        if (searchResult != null) {
            System.out.println(searchResult);
        } else {
            System.out.println("Task not found.");
        }
        System.out.println();
        System.out.println("Removing task with ID 2:");
        scheduler.removeTaskById(2);
        scheduler.displayAllTasks();
    }
}