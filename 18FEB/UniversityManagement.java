import java.util.ArrayList;
import java.util.List;

abstract class CourseType {
    private String evaluationMethod;

    public CourseType(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public String getEvaluationMethod() {
        return evaluationMethod;
    }
}

class ExamCourse extends CourseType {
    public ExamCourse() {
        super("Exam-Based");
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse() {
        super("Assignment-Based");
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse() {
        super("Research-Based");
    }
}

class Course<T extends CourseType> {
    private String courseName;
    private String department;
    private T courseType;

    public Course(String courseName, String department, T courseType) {
        this.courseName = courseName;
        this.department = department;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDepartment() {
        return department;
    }

    public T getCourseType() {
        return courseType;
    }

    @Override
    public String toString() {
        return courseName + " (" + department + ") - " + courseType.getEvaluationMethod();
    }
}

class University {
    private List<Course<? extends CourseType>> courses = new ArrayList<>();

    public void addCourse(Course<? extends CourseType> course) {
        courses.add(course);
    }

    public void displayCourses() {
        for (Course<? extends CourseType> course : courses) {
            System.out.println(course);
        }
    }
}

public class UniversityManagement {
    public static void main(String[] args) {
        Course<ExamCourse> math = new Course<>("Probality", "Mathematics", new ExamCourse());
        Course<AssignmentCourse> cs = new Course<>("C Programming", "Computer Science", new AssignmentCourse());
        Course<ResearchCourse> physics = new Course<>("Refraction", "Physics", new ResearchCourse());

        University university = new University();
        university.addCourse(math);
        university.addCourse(cs);
        university.addCourse(physics);

        university.displayCourses();
    }
}