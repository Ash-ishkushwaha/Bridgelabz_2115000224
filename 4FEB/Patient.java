
class Patient {
    private static String hospitalName = "City Hospital";
    private static int totalPatients = 0;
    
    private final String patientID;
    private String name;
    private int age;
    private String ailment;
    
    public Patient(String name, int age, String ailment, String patientID) {
        this.name = name;
        this.age = age;
        this.ailment = ailment;
        this.patientID = patientID;
        totalPatients++;
    }
    
    public static void getTotalPatients() {
        System.out.println("Total Patients Admitted: " + totalPatients);
    }
    
    public void displayPatientDetails() {
        if (this instanceof Patient) {
            System.out.println("Hospital: " + hospitalName);
            System.out.println("Name: " + this.name);
            System.out.println("Age: " + this.age);
            System.out.println("Ailment: " + this.ailment);
            System.out.println("Patient ID: " + this.patientID);
        } else {
            System.out.println("Invalid patient object.");
        }
    }
    
    public static void main(String[] args) {
        Patient patient1 = new Patient("Alice Johnson", 30 , "Flu", "P001");
        Patient patient2 = new Patient("Bob Smith", 45, "Fracture", "P002");
        
        patient1.displayPatientDetails();
        patient2.displayPatientDetails();
        
        Patient.getTotalPatients();
    }
}

