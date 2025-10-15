import java.util.*;

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
abstract class Person {
    String name;
    int customerID;
    String membership;
    Person(String name, int customerID, String membership) {

        this.name = name;
        this.customerID = customerID;
        this.membership = membership;
    }
    public String toString() {
        return "Name: " + this.name + "\nCustomer ID: " + this.customerID + "\nMembership: " + this.membership;
    }
    public void Info(){ // needs to be abstract? //
        System.out.println(this);
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
    double STUDENT_FEE = 5.0;
    double EXTERNAL_MEMBER_FEE = 10.0;
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

        System.out.println("| " + this.name + " | ID: " + this.movieID+ " |");

    }



}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Movies> movies = new ArrayList<>();
        // In my example, I assume that 1000s = Drama, 2000s = Action, 3000s = Sci-Fi.
        movies.add(new Movies(1001, "The Godfather"));
        movies.add(new Movies(1002, "The Dark Knight"));
        movies.add(new Movies(2001, "The Lord of the Rings"));
        movies.add(new Movies(2002, "The Shawshank Redemption"));
        movies.add(new Movies(3001, "Avengers: Infinity War"));
        movies.add(new Movies(3002, "The Matrix"));
        movies.add(new Movies(3003, "Star Wars: The Rise of Skywalker"));
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<External_Member> external_members = new ArrayList<>();
        System.out.println("MOVIE RENTAL SYSTEM:");
        while (true) {
            System.out.print("\n1. Add Student\n2. Add External Member\n3. List Students\n4. List External Members\n5. List Movies\n6. Rent a movie\n8. Return a movie\n9. Exit\n> ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = input.next();
                    System.out.print("Enter Student ID: ");
                    int customerID = input.nextInt();
                    System.out.print("Enter student's grade: ");
                    int grade = input.nextInt();
                    System.out.print("Enter Student membership: ");
                    String membership = input.next();
                    Student student = new Student(name, customerID, membership, grade);
                    students.add(student);
                    System.out.println("Student added!");
                    break;
                case 2:
                    System.out.print("Enter client's name: ");
                    String client_name = input.next();
                    System.out.print("Enter client ID: ");
                    int client_id = input.nextInt();
                    System.out.println("Enter client Membership: ");
                    String client_membership = input.next();
                    System.out.println("Enter client's job: ");
                    String client_job = input.next();
                    System.out.print("Enter client's organization name: ");
                    String client_org_name = input.next();
                    External_Member external_member = new External_Member(client_name, client_id, client_membership, client_job, client_org_name);
                    external_members.add(external_member);
                    System.out.println("Client added!");
                    break;
                case 3:
                    for (Student s : students) {
                        s.Info();
                    }
                    if (students.isEmpty()) {
                        System.out.println("No students added.");
                    }
                    break;
                case 4:
                    for (External_Member e : external_members) {
                        e.Info();
                    }
                    if (external_members.isEmpty()) {
                        System.out.println("No clients added.");
                    }
                    break;
                case 5:
                    for (Movies m : movies) {
                        m.show();
                    }

                    break;
                case 6:
                    for (Movies m : movies) {
                        m.show();
                    }
                    System.out.println("Enter the movie name you want to rent: ");
                    String movie_name = input.next();


                    for (Movies m : movies) {
                        if (m.name.equalsIgnoreCase(movie_name)) {
                            movie_name = m.name;

                        }
                    }
                    System.out.println("Enter the number of nights you want to rent: ");
                    int nights = input.nextInt();


                    break;
                case 8:
                    for (Movies m : movies) {
                        m.show();
                    }
                    System.out.println("Enter the movie name you want to return: ");
                    String movie_name_return = input.next();

                    for (Movies m : movies) {
                        if (m.name.equalsIgnoreCase(movie_name_return)) {
                            movie_name_return = m.name;

                        }
                    }

                    break;
                case 9:
                    System.out.println("Logging out...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.\n");
                    break;
            }
        }
    }
}
