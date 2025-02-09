class Course {
    String courseName;
    int duration;

    Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    void displayInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration + " hours");
    }
}

class OnlineCourse extends Course {
    String platform;
    boolean isRecorded;

    OnlineCourse(String courseName, int duration, String platform, boolean isRecorded) {
        super(courseName, duration);
        this.platform = platform;
        this.isRecorded = isRecorded;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Platform: " + platform);
        System.out.println("Recorded: " + (isRecorded ? "Yes" : "No"));
    }
}

class PaidOnlineCourse extends OnlineCourse {
    double fee;
    double discount;

    PaidOnlineCourse(String courseName, int duration, String platform, boolean isRecorded, double fee,
            double discount) {
        super(courseName, duration, platform, isRecorded);
        this.fee = fee;
        this.discount = discount;
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Fee: " + fee);
        System.out.println("Discount: " + discount + "%");
    }
}

public class Education {
    public static void main(String[] args) {
        Course course = new Course("Java Programming", 40);
        OnlineCourse onlineCourse = new OnlineCourse("C Programming", 30, "Coursera", true);
        PaidOnlineCourse paidOnlineCourse = new PaidOnlineCourse("Machine Learning", 50, "Udemy", false, 200.0,
                20.0);

        course.displayInfo();
        System.out.println();
        onlineCourse.displayInfo();
        System.out.println();
        paidOnlineCourse.displayInfo();
    }
}