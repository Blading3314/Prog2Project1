/** Student class. This class receives the student's info such as his/her school name and grade.*/
public class Student extends Person {
    String schoolName;
    int grade;
    Student(String name, int customerID, String schoolName, int grade) {
        super(name, customerID);
        this.grade = grade;
        this.membership = "Student";
        this.schoolName = schoolName;
    }
    /** Show student's info.*/
    public void Info() {
        System.out.printf("Name        : %s%n", this.name);
        System.out.printf("Customer ID : %d%n", this.customerID);
        System.out.printf("Membership  : %s%n", this.membership);
        System.out.printf("School Name : %s%n", this.schoolName);
        System.out.printf("Grade       : %d%n", this.grade);
        System.out.println("--------------------------\n");
    }
    /** This method returns a string representation of the object.
     * @return a formatted string containing name, customer ID, membership type, grade, and school name (appears in file).*/
    public String toString() {
        return super.toString() + "\nGrade: " + this.grade + "\nSchool Name: " + this.schoolName;
    }
}
