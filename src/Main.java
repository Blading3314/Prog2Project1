import java.util.*;

class MovieRental implements Payment {
    int customerID;
    String membership;   // New: Membership type (Student or Regular)
    int movieID;
    int nights_rented;
    boolean rentable;

    MovieRental(int customerID, String membership, int movieID, int nights_rented, boolean rentable) {
        this.customerID = customerID;
        this.membership = membership;  // Assign membership
        this.movieID = movieID;
        this.nights_rented = nights_rented;
        this.rentable = rentable;
    }
    public String getMovieStatus() {
        return this.rentable ? "Available" : "Rented";
    }
    
    

    public void Info(boolean showFee) {
        System.out.println("\n--- Movie Rental Information ---");
        System.out.printf("Customer ID     : %d%n", this.customerID);
        System.out.printf("Movie ID        : %d%n", this.movieID);
        System.out.printf("Nights Rented   : %d%n", this.nights_rented);
        System.out.printf("Status          : %s%n", getMovieStatus());
        if (showFee) {
            System.out.printf("Rental Fee      : $%.2f%n", calculate());
        }
        System.out.println("---------------------------------");
    }

    @Override
    public double calculate() {
        int additionalNights = Math.max(0, nights_rented - 7);  // excess nights beyond the first week
        if (isStudent()) {
            return STUDENT_FEE + (additionalNights);  // $1 per extra night for students
        } else {
            return EXTERNAL_MEMBER_FEE + (additionalNights * 2); // $2 per extra night for members
        }

    }

    public boolean isStudent() {
        return "Student".equalsIgnoreCase(this.membership);
    }
}
abstract class Person {
    String name;
    int customerID;
    String membership;

    Person(String name, int customerID) {
        this.name = name;
        this.customerID = customerID;
    }

    public String toString() {
        return "Name: " + this.name + "\nCustomer ID: " + this.customerID + "\nMembership: " + this.membership;
    }
    abstract public void Info();
    
}

class Student extends Person {
    String schoolName;
    int grade;
    Student(String name, int customerID, String schoolName, int grade) {
        super(name, customerID);
        this.grade = grade;
        this.membership = "Student";
        this.schoolName = schoolName;


    }

    public void Info() {
        System.out.printf("Name        : %s%n", this.name);
        System.out.printf("Customer ID : %d%n", this.customerID);
        System.out.printf("Membership  : %s%n", this.membership);
        System.out.printf("School Name : %s%n", this.schoolName);
        System.out.printf("Grade       : %d%n", this.grade);
        System.out.println("--------------------------\n");
    }

    public String toString() {
        return super.toString() + "\nGrade: " + this.grade;
    }
}
class External_Member extends Person {
    String name;
    int customerID;
    String membership;
    String job;
    String org_name;
    External_Member(String name, int customerID, String job, String org_name) {
        super(name, customerID);
        this.name = name;
        this.customerID = customerID;
        this.membership = "Regular";
        this.job = job;
        this.org_name = org_name;
    }

    public void Info() {
        System.out.printf("Name         : %s%n", this.name);
        System.out.printf("Customer ID  : %d%n", this.customerID);
        System.out.printf("Membership   : %s%n", this.membership);
        System.out.printf("Job          : %s%n", this.job);
        System.out.printf("Organization : %s%n", this.org_name);
        System.out.println("---------------------------------\n");
    }
    public String toString() {
        return super.toString() + "\nJob: " + this.job + "\nOrganization Name: " + this.org_name;
    }

}

interface Payment {
    double STUDENT_FEE = 5.0;
    double EXTERNAL_MEMBER_FEE = 10.0;
    double calculate();

}
class Movies {
    int movieID;
    String name;
    boolean isAvailable;

    Movies(int movieID, String name) {
        this.movieID = movieID;
        this.name = name;
        this.isAvailable = true;
    }

    public String getMovieID() {
        return String.valueOf(this.movieID);
    }

    public void show() {
        String availability = isAvailable ? "Available" : "Unavailable";

        System.out.printf("%-30s  %-12d  %-15s%n", this.name, this.movieID, availability);
    }

    public void showWithoutStatus() {
     // case 8
        System.out.printf(" %-25s  %-10d %n", this.name, this.movieID);
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    public boolean getAvailability() {
        return this.isAvailable;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Movies> movies = new ArrayList<>();
        // In my example, the 1000s = Drama, 2000s = Action, 3000s = Sci-Fi.
        movies.add(new Movies(1001, "The Godfather"));
        movies.add(new Movies(1002, "The Dark Knight"));
        movies.add(new Movies(2001, "The Lord of the Rings"));
        movies.add(new Movies(2002, "The Shawshank Redemption"));
        movies.add(new Movies(3001, "Avengers: Infinity War"));
        movies.add(new Movies(3002, "The Matrix"));
        movies.add(new Movies(3003, "Star Wars"));
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Thomas", 2470211,  "Vanier", 80));
        
        ArrayList<External_Member> external_members = new ArrayList<>();
        external_members.add(new External_Member("Mister", 64512, "Engineer", "Google"));
        ArrayList<MovieRental> movie_rentals = new ArrayList<>();
        System.out.println("MOVIE RENTAL SYSTEM:");
        while (true) {
            System.out.print("\n1. Add Student\n2. Add External Member\n3. Add a movie\n4. List Students\n5. List External Members\n6. List Movies\n7. Rent a movie\n8. Return a movie\n9. Exit\n> ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    boolean flag = false;

                            System.out.print("Enter student's name\n> ");
                            String name = input.next();
                            while (!flag) {
                                try {
                            System.out.print("Enter Student ID\n> ");
                            int customerID = input.nextInt();
                            if (students.stream().anyMatch(s -> s.customerID == customerID)) {
                                throw new Exception("Student with that ID already exists. Please try again.");
                            }
                            System.out.print("Enter student's school name\n> ");
                            String schoolName = input.next();
                            System.out.print("Enter student's grade\n> ");
                            int grade = input.nextInt();
                            Student student = new Student(name, customerID, schoolName, grade);
                            students.add(student);
                            System.out.println("Student added.");
                            flag = true;

                                } catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                    }
                    break;
                case 2:
                    boolean flag2 = false;


                            System.out.print("Enter member's name\n> ");
                            String client_name = input.next();
                            while (!flag2) {
                                try {
                            System.out.print("Enter member's ID\n> ");
                            int client_id = input.nextInt();
                            if (external_members.stream().anyMatch(e -> e.customerID == client_id)) {
                                throw new Exception("Member with that ID already exists. Please try again.");
                            }
                            System.out.println("Enter member's job\n>");
                            String client_job = input.next();
                            System.out.print("Enter member's organization name\n> ");
                            String client_org_name = input.next();
                            External_Member external_member = new External_Member(client_name, client_id, client_job, client_org_name);
                            external_members.add(external_member);
                            System.out.println("Regular member added.");
                            flag2 = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    boolean flag3 = false;
                        input.nextLine();
                        System.out.print("Enter the movie name that you want to add\n> ");
                        String movieAddName = input.nextLine();
                        while (!flag3) {
                        try {
                            System.out.print("Enter the movie ID\n> ");
                            int movie_id = input.nextInt();
                            // check if the movie ID already exists in the list
                            if (movies.stream().anyMatch(m -> m.getMovieID().equals(String.valueOf(movie_id)))) {
                                throw new Exception("Movie with that ID already exists. Please try again.");
                            } else {
                                Movies movie = new Movies(movie_id, movieAddName);
                                movies.add(movie);
                                System.out.println("Movie added.");
                                flag3 = true;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 4:
                    for (Student s : students) {
                        System.out.println("\n--- Student " + (students.indexOf(s) + 1) + " Details ---");
                        s.Info();
                    }
                    if (students.isEmpty()) {
                        System.out.println("No students added.");
                    }
                    break;
                case 5:
                    for (External_Member e : external_members) {
                        System.out.println("\n--- Member " + (external_members.indexOf(e) + 1) + " Details ---");
                        e.Info();
                    }
                    if (external_members.isEmpty()) {
                        System.out.println("No regular members added.");
                    }
                    break;
                case 6:
                    System.out.println("\n--- Movie List ---");
                    System.out.printf("%-30s  %-12s  %-15s%n", "Movie Name", "Movie ID", "Status");
                    System.out.println("---------------------------------------------------------------");
                    for (Movies m : movies) {
                        m.show(); // Prints movies with availability details
                    }
                    System.out.println("---------------------------------------------------------------\n");
                    break;
                case 7:
                    Person renter = null; 
                    Movies movieToRent = null;
                    int nights = 0; 
                    while (renter == null) {
                        try {
                            System.out.print("Student or regular member rent? (Type 'Student' or 'Member')\n> ");
                            String choice2 = input.next();
                            if (choice2.equalsIgnoreCase("Student")) {
                                for (Student s : students) { //just print regular list here
                                    System.out.println("\n--- Student " + (students.indexOf(s) + 1) + " Details ---");
                                    s.Info();
                                }

                                System.out.print("Enter student's ID\n> ");
                                int studentID = input.nextInt();
                                for (Student s : students) {
                                    if (s.customerID == studentID) {
                                        renter = s;
                                        break;
                                    }
                                }
                                if (renter == null) {
                                    System.out.println("Student not found. Please try again.");
                                }
                            } else if (choice2.equalsIgnoreCase("Member")) {
                                for (External_Member e : external_members) {
                                    e.Info();
                                }
                               
                                System.out.print("Enter member's name\n> ");
                                String clientName = input.next();
                                for (External_Member e : external_members) {
                                    if (e.name.equalsIgnoreCase(clientName)) {
                                        renter = e;
                                        break;
                                    }
                                }
                                if (renter == null) {
                                    System.out.println("Member not found. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid choice. Please type 'Student' or 'Member'.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred. Please try again.");
                        }
                    }

                    while (movieToRent == null) {
                        try {
                            System.out.println("\nAvailable Movies:");
                            for (Movies m : movies) {
                                m.show();
                            }
                            input.nextLine();
                            System.out.print("Enter movie name to rent\n> ");
                            String movie_name = input.nextLine();

                            for (Movies m : movies) {
                                if (m.name.equalsIgnoreCase(movie_name)) {
                                    movieToRent = m;
                                    break;
                                }
                            }
                            if (movieToRent == null) {
                                System.out.println("Movie not found. Please try again.");
                            } else if (!movieToRent.getAvailability()) {
                                System.out.println("Sorry, this movie is currently unavailable. Please select another movie.");
                                movieToRent = null;
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred. Please try again.");
                        }
                    }

                    while (nights <= 0) {
                        try {
                            System.out.print("Enter number of nights rented\n> ");
                            nights = input.nextInt();
                            if (nights <= 0) {
                                System.out.println("Invalid input. Number of nights must be positive.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            input.next();
                        }
                    }
                    String membershipType = renter.membership;
                    MovieRental rental = new MovieRental(renter.customerID, membershipType, Integer.parseInt(movieToRent.getMovieID()), nights, false);
                    movie_rentals.add(rental);
                    movieToRent.setAvailability(false);
                    System.out.println("Movie rented successfully!");
                    System.out.printf("Renter: %s%nMovie: %s%nNights: %d%n", renter.name, movieToRent.name, nights);
                    rental.Info(false); // this only to not show the fee just yet

                    break;
                case 8:
                    System.out.print("Was the renter a Student or a regular member? (Type 'Student' or 'Member')\n> ");
                    String inputRenterType = input.next();
                    ArrayList<Person> rentersList;

                    if (inputRenterType.equalsIgnoreCase("Student")) {
                        rentersList = new ArrayList<>(students);
                    } else if (inputRenterType.equalsIgnoreCase("Member")) {
                        rentersList = new ArrayList<>(external_members);
                    } else {
                        System.out.println("Invalid input. Please type 'Student' or 'Member'.");
                        break; // Exit
                    }

                    System.out.println("\n--- Movies Currently Rented by " + inputRenterType + " ---");
                    System.out.printf("%-30s  %-12s%n", "Movie Name", "Movie ID");
                    System.out.println("-----------------------------------------------");

                    ArrayList<MovieRental> validRentals = new ArrayList<>();
                    for (MovieRental rental1 : movie_rentals) {
                        for (Person renterPerson : rentersList) {
                            if (rental1.customerID == renterPerson.customerID && !rental1.rentable) {
                                validRentals.add(rental1);
                                for (Movies movie : movies) {
                                    if (Integer.parseInt(movie.getMovieID()) == rental1.movieID) {
                                        movie.showWithoutStatus();
                                    }
                                }
                            }
                        }
                    }

                    if (validRentals.isEmpty()) {
                        System.out.println("No movies are currently rented by this renter type.");
                        break; // Exit
                    }
                    System.out.println("-----------------------------------------------");

                    boolean isReturned = false;
                    while (!isReturned) {
                        input.nextLine();
                        System.out.print("Enter the movie name you want to return: ");
                        String movie_name_return = input.nextLine();

                        for (MovieRental rental2 : validRentals) {
                            for (Movies movie : movies) {
                                if (movie.name.equalsIgnoreCase(movie_name_return) &&
                                        Integer.parseInt(movie.getMovieID()) == rental2.movieID &&
                                        !rental2.rentable) {

                                    rental2.rentable = true; // returned
                                    movie.setAvailability(true); // is available again
                                    System.out.println("Movie '" + movie.name + "' has been successfully returned.");
                                    rental2.Info(true); // shows fee
                                    isReturned = true;
                                    break;
                                }
                            }
                            if (isReturned) break;
                        }

                        if (!isReturned) {
                            System.out.println("The requested movie could not be returned. Verify the movie name and try again.");
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
