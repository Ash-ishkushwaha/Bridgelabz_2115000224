class Student {
    private static String universityName = "Global University";
    private static int totalStudents = 0;
    
    private final String rollNumber;
    private String name;
    private String grade;
    
    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        totalStudents++;
    }
    
    public static void displayTotalStudents() {
        System.out.println("Total Students Enrolled: " + totalStudents);
    }
    
    public void displayStudentDetails() {
        if (this instanceof Student) {
            System.out.println("University: " + universityName);
            System.out.println("Name: " + this.name);
            System.out.println("Roll Number: " + this.rollNumber);
            System.out.println("Grade: " + this.grade);
        } else {
            System.out.println("Invalid student object.");
        }
    }
    
    public static void main(String[] args) {
        Student student1 = new Student("Alice Johnson", "S001", "A");
        Student student2 = new Student("Bob Smith", "S002", "B");
        
        student1.displayStudentDetails();
        student2.displayStudentDetails();
        
        Student.displayTotalStudents();
    }
}