import java.util.ArrayList;
import java.util.List;

abstract class JobRole {
    private String roleName;

    public JobRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer");
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist");
    }
}

class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager");
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private T jobRole;

    public Resume(String candidateName, T jobRole) {
        this.candidateName = candidateName;
        this.jobRole = jobRole;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public T getJobRole() {
        return jobRole;
    }

    @Override
    public String toString() {
        return "Candidate: " + candidateName + " | Applying for: " + jobRole.getRoleName();
    }
}

class ResumeScreeningSystem {
    private List<Resume<? extends JobRole>> resumePipeline = new ArrayList<>();

    public void addResume(Resume<? extends JobRole> resume) {
        resumePipeline.add(resume);
    }

    public void processResumes() {
        for (Resume<? extends JobRole> resume : resumePipeline) {
            System.out.println("Processing " + resume);
        }
    }

    public static <T extends JobRole> Resume<T> createResume(String candidateName, T jobRole) {
        return new Resume<>(candidateName, jobRole);
    }
}

public class ResumeScreening {
    public static void main(String[] args) {
        Resume<SoftwareEngineer> seResume = ResumeScreeningSystem.createResume("Person1", new SoftwareEngineer());
        Resume<DataScientist> dsResume = ResumeScreeningSystem.createResume("Person2", new DataScientist());
        Resume<ProductManager> pmResume = ResumeScreeningSystem.createResume("Person3", new ProductManager());

        ResumeScreeningSystem system = new ResumeScreeningSystem();
        system.addResume(seResume);
        system.addResume(dsResume);
        system.addResume(pmResume);

        system.processResumes();
    }
}