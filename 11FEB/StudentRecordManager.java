class StudentRecord {
    private int rollNumber;
    private String name;
    private int age;
    private String grade;
    private StudentRecord next;

    public StudentRecord(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public StudentRecord getNext() {
        return next;
    }

    public void setNext(StudentRecord next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

public class StudentRecordManager {
    private StudentRecord head;

    public StudentRecordManager() {
        this.head = null;
    }

    public void addStudentAtBeginning(int rollNumber, String name, int age, String grade) {
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        newStudent.setNext(head);
        head = newStudent;
    }

    public void addStudentAtEnd(int rollNumber, String name, int age, String grade) {
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
        } else {
            StudentRecord current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newStudent);
        }
    }

    public void addStudentAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 0) {
            addStudentAtBeginning(rollNumber, name, age, grade);
            return;
        }
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        StudentRecord current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.getNext();
        }
        if (current != null) {
            newStudent.setNext(current.getNext());
            current.setNext(newStudent);
        }
    }

    public void deleteStudentByRollNumber(int rollNumber) {
        if (head == null) return;
        if (head.getRollNumber() == rollNumber) {
            head = head.getNext();
            return;
        }
        StudentRecord current = head;
        while (current.getNext() != null && current.getNext().getRollNumber() != rollNumber) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public StudentRecord searchStudentByRollNumber(int rollNumber) {
        StudentRecord current = head;
        while (current != null && current.getRollNumber() != rollNumber) {
            current = current.getNext();
        }
        return current;
    }

    public void updateStudentGrade(int rollNumber, String newGrade) {
        StudentRecord student = searchStudentByRollNumber(rollNumber);
        if (student != null) {
            student.setGrade(newGrade);
        }
    }

    public void displayAllStudents() {
        StudentRecord current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();
        manager.addStudentAtBeginning(1, "John", 20, "A");
        manager.addStudentAtEnd(2, "Alice", 22, "B");
        manager.addStudentAtPosition(1, 3, "Bob", 21, "C");

        manager.displayAllStudents();
        System.out.println();
        
        System.out.println("Searching for student with roll number 2:");
        System.out.println(manager.searchStudentByRollNumber(2));
        System.out.println();
        
        System.out.println("Updating grade for student with roll number 2:");
        manager.updateStudentGrade(2, "A+");
        System.out.println();

        System.out.println("Deleting student with roll number 1:");
        manager.deleteStudentByRollNumber(1);
        
        System.out.println();
        manager.displayAllStudents();
    }
}