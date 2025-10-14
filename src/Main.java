import java.util.ArrayList;
import java.util.Scanner;

class MovieRental {
    int customerID;
    int movieID;
    int nights_rented;
    boolean rentable;
    MovieRental(int customerID, int movieID, int nights_rented, boolean rentable) {
        this.customerID = customerID;
        this.movieID = movieID;
        this.nights_rented = nights_rented;
        this.rentable = rentable;
    }
    public String getMovieStatus() {

        return updateMovieStatus();

    }
    public String updateMovieStatus() {
        if (this.rentable) {
            return "Available";
        }
        else {
            return "Unavailable";

        }

    }
    public void printInfo() {
        System.out.println("Customer ID: " + this.customerID);
        System.out.println("Movie ID: " + this.movieID);
        System.out.println("Nights Rented: " + this.nights_rented);
        System.out.println("Rentable: " + getMovieStatus());
    }


}
abstract class Person extends MovieRental {
    String name;
    int customerID;
    String membership;
    Person(String name, int customerID, String membership) {
        super(customerID, 0, 0, true);

        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }
    public String toString() {
        return "Name: " + this.name + "\nCustomer ID: " + this.customerID + "\nMembership: " + this.membership;
    }
    public void Info(){ /** needs to be abstract? **/
        System.out.println(this.toString());
    }

}
class Student extends Person {
    String name;
    int customerID;
    String membership;
    int grade;
    Student(String name, int customerID, String membership, int grade) {
        super(name, customerID, membership);
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
        this.grade = grade;
    }
    public String toString() {
        return super.toString() + "\nGrade: " + this.grade;
    }
    public int getCustomerID() {
        return this.customerID;
    }
    public String getMembership() {
        return this.membership;
    }
}
class External_Member extends Person {
    String name;
    int customerID;
    String membership;
    String job;
    String org_name;
    External_Member(String name, int customerID, String membership, String job, String org_name) {
        super(name, customerID, membership);
        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
        this.job = job;
        this.org_name = org_name;
    }
    public String toString() {
        return super.toString() + "\nJob: " + this.job + "\nOrganization Name: " + this.org_name;
    }
    public int getCustomerID() {
        return this.customerID;
    }
    public String getMembership() {
        return this.membership;
    }
}

interface Payment {
    final static double STUDENT_FEE = 5.0;
    final static double EXTERNAL_MEMBER_FEE = 10.0;
    double calculateFee();


}
class Movies{
    int movieID;
    String name;
    Movies(int movieID, String name) {
        this.movieID = movieID;
        this.name = name;
    }

    public String getMovieID() {
        return String.valueOf(this.movieID);
    }
    public void show() {
        System.out.println("Movie Name: " + this.name);
        System.out.println("Movie ID: " + this.movieID);
    }



}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Movies> movies = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<External_Member> external_members = new ArrayList<>();
        System.out.println("Programming 2 Final Project: Movie Rental System");
        while (true) {
            System.out.println("1. Add Student\n2. Add External Member\n3. List Students\n4. List External Members\n5. List Movies\n6. Rent a movie\n8. Return a movie\n9. Exit\n> ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Student name: ");
                String name = input.next();
                System.out.println("Enter Student ID: ");
                int customerID = input.nextInt();
                System.out.println("Enter Student Grade: ");
                int grade = input.nextInt();
                System.out.println("Enter Student Membership: ");
                String membership = input.next();
                Student student = new Student(name, customerID, membership, grade);
                students.add(student);
                System.out.println("Student added!");
                break;
                case 2:
                    System.out.println("Enter client name: ");
                    String client_name = input.next();
                    System.out.println("Enter client ID: ");
                    int client_id = input.nextInt();
                    System.out.println("Enter client Membership: ");
                    String client_membership = input.next();
                    System.out.println("Enter client Job: ");
                    String client_job = input.next();
                    System.out.println("Enter client Organization Name: ");
                    String client_org_name = input.next();
                    External_Member external_member = new External_Member(client_name, client_id, client_membership, client_job, client_org_name);
                    external_members.add(external_member);
                    System.out.println("Client added!");
                    break;
                    case 3:
                        for (Student s : students) {
                            s.printInfo();
                        }
                        break;
                        case 4:
                            for (External_Member e : external_members) {
                                e.printInfo();
                            }
                            break;
                            case 5:

                                break;
                                case 6:
                                    break;
                                    case 8:
                                        break;
                                        case 9:
                                            System.out.println("Logging out...");
                                            System.exit(0);
                                        default:
                                            System.out.println("Invalid choice! Try again.");
                                            break;
        }
        }
    }
}