import java.util.*;
import java.io.*;
import java.time.LocalDate;
/** This is the main class of the movie rental system. This is where the program will run.*/
public class Main {
    public static void main(String[] args) {
        AppStart.main(args);

        File studentFile = new File("Prog2Project1-main\\data\\Students.txt");
        File memberFile = new File("Prog2Project1-main\\data\\Members.txt");
        File movieFile = new File("Prog2Project1-main\\data\\Movies.txt");
        File rentalFile = new File("Prog2Project1-main\\data\\Rentals.txt");
        Scanner input = new Scanner(System.in);

        ArrayList<Movie> movies = new ArrayList<>();

        ArrayList<Student> students = new ArrayList<>();

        ArrayList<ExternalMember> external_members = new ArrayList<>();

        ArrayList<MovieRental> movie_rentals = new ArrayList<>();

        if (studentFile.exists()) {
            try {
                Scanner studentFileScanner = new Scanner(studentFile);

                String name = null;
                int id = 0;
                String schoolName = null;
                int grade = 0;

                while (studentFileScanner.hasNextLine()) /* loop until no more lines */{
                    String line = studentFileScanner.nextLine();

                    if (line.startsWith("Name: ")) {
                        name = line.substring(6); // take name after "Name: (6 characters)"
                    }
                    else if (line.startsWith("Customer ID: ")) {
                        id = Integer.parseInt(line.substring(13));
                    }
                    else if (line.startsWith("Membership: ")) {
                        // skipping, because constructor does it already (this.membership = "Student")
                    }
                    else if (line.startsWith("Grade: ")) {
                        grade = Integer.parseInt(line.substring(7)); //convert to int
                    }
                    else if (line.startsWith("School Name: ")) {
                        schoolName = line.substring(13);
                    }
                    else if (line.startsWith("---")) {
                        /* end of student's info when there's "---"
                        at the bottom of the line
                         */
                        students.add(new Student(name, id, schoolName, grade));
                    }
                }
                studentFileScanner.close();

            } catch (Exception e) {
                System.out.println("No recent students file found.");
            }
        }
        // almost the same logic as students
        if (memberFile.exists()) {
            try{
                Scanner memberFileScanner = new Scanner(memberFile);
                String name = null;
                int id = 0;
                String job = null;
                String org_name = null;
                while (memberFileScanner.hasNextLine()) {
                    String line = memberFileScanner.nextLine();
                    if (line.startsWith("Name: ")){
                        name = line.substring(6);
                    }
                    else if (line.startsWith("Customer ID: ")){
                        id = Integer.parseInt(line.substring(13));
                    }
                    else if (line.startsWith("Job: ")){
                        job = line.substring(5);
                    }
                    else if (line.startsWith("Organization Name: ")){
                        org_name = line.substring(19);
                    }
                    else if(line.contains("---")){
                        external_members.add(new ExternalMember(name, id, job, org_name));
                    }
                }
                memberFileScanner.close();

            }
            catch(Exception e){
                System.out.println("No recent members file found.");
            }
        }

        if (movieFile.exists()) {
            try {
                Scanner movieFileScanner = new Scanner(movieFile);
                String movieName;
                int movieID;
                while (movieFileScanner.hasNextLine()) {
                    String line = movieFileScanner.nextLine();

                    if (line.startsWith("---") || line.isEmpty()) {
                        continue;
                    }
                    String[] parts = line.split(" - Movie ID: ");
                    if (parts.length == 2) {
                        movieName = parts[0].trim();
                        String[] idAndStatus = parts[1].split(" - Status: ");
                        movieID = Integer.parseInt(idAndStatus[0].trim());
                        Movie m = new Movie(movieID, movieName);
                        movies.add(m);
                    }
                }
                movieFileScanner.close();
            } catch (Exception e) {
                System.out.println("No recent movies file found.");
            }
        }

        if (rentalFile.exists()) {
            try {
                Scanner rentalFileScanner = new Scanner(rentalFile);

                int customerID = 0, mID = 0, nights = 0;
                String membership = "";
                LocalDate beginD = null, returnD = null;
                boolean rentableFlag = true;

                while (rentalFileScanner.hasNextLine()) {
                    String line = rentalFileScanner.nextLine();

                    if (line.startsWith("Customer ID: "))
                        customerID = Integer.parseInt(line.substring(13));
                    else if (line.startsWith("Membership: "))
                        membership = line.substring(12);
                    else if (line.startsWith("Movie ID: "))
                        mID = Integer.parseInt(line.substring(10));
                    else if (line.startsWith("Nights Rented: "))
                        nights = Integer.parseInt(line.substring(15));
                    else if (line.startsWith("Begin Date: "))
                        beginD = LocalDate.parse(line.substring(12));
                    else if (line.startsWith("Return Date: "))
                        returnD = LocalDate.parse(line.substring(13));
                    else if (line.startsWith("Status: "))
                        rentableFlag = line.substring(8).equals("Available");
                    else if (line.startsWith("---")) {

                        MovieRental r = new MovieRental(customerID, membership, mID, nights, rentableFlag);
                        r.beginDate = beginD;
                        r.returnDate = returnD;
                        // If the user opens the program after the date expected to return,
                        // the isAfter() method will automatically return the movie.
                        if (LocalDate.now().isAfter(returnD)) {
                            r.rentable = true;
                        }

                        movie_rentals.add(r);
                    }
                }
                rentalFileScanner.close();
            } catch (Exception e) {
                System.out.println("No recent rentals file found.");
            }
        }

        System.out.println("MOVIE RENTAL SYSTEM:");
        while (true) {
            System.out.print("\n1. Add Student\n2. Add External Member\n3. Add a movie\n4. List all Students\n5. List all External Members\n6. List Movies\n7. Rent a movie\n8. Return a movie\n9. Exit\n> ");
            int choice = input.nextInt();
            switch (choice) {

                case 1: //Add Student
                    boolean flag = false;
                    input.nextLine();
                    System.out.print("Enter student's name\n> ");
                    String name = input.nextLine();
                    while (!flag) {
                        try {
                            System.out.print("Enter Student ID\n> ");
                            int customerID = input.nextInt();
                            if (students.stream().anyMatch(s -> s.customerID == customerID)) { //verify if ID is already taken
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

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 2: //Add External Member
                    boolean flag2 = false;
                    input.nextLine();
                    System.out.print("Enter member's name\n> ");
                    String client_name = input.nextLine();
                    while (!flag2) {
                        try {
                            System.out.print("Enter member's ID\n> ");
                            int client_id = input.nextInt();
                            if (external_members.stream().anyMatch(e -> e.customerID == client_id)) {
                                throw new Exception("Member with that ID already exists. Please try again.");
                            }
                            System.out.print("Enter member's job\n> ");
                            String client_job = input.next();
                            System.out.print("Enter member's organization name\n> ");
                            String client_org_name = input.next();
                            ExternalMember external_member = new ExternalMember(client_name, client_id, client_job, client_org_name);
                            external_members.add(external_member);
                            System.out.println("Regular member added.");
                            flag2 = true;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 3: // Add a movie
                    boolean flag3 = false;
                    input.nextLine();

                    while (!flag3) {
                        try {
                            System.out.print("Enter the movie name that you want to add\n> ");
                            String movieAddName = input.nextLine();

                            if (movieAddName.isEmpty()) {
                                throw new Exception("Movie name cannot be empty. Please try again.");
                            }
                            // verify if movie name is already taken
                            if (movies.stream().anyMatch(m -> m.name.equalsIgnoreCase(movieAddName))) {
                                throw new Exception("Movie with that name already exists. Please try again.\n");
                            }

                            System.out.print("Enter the movie ID\n> ");
                            int movie_id = input.nextInt();
                            input.nextLine();

                            // verify if movie ID is already taken
                            if (movies.stream().anyMatch(m -> m.getMovieID().equals(String.valueOf(movie_id)))) {
                                throw new Exception("Movie with that ID already exists. Please try again.\n");
                            }

                            movies.add(new Movie(movie_id, movieAddName));
                            System.out.println("Movie added.");
                            flag3 = true;

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid ID. Please enter a number.");
                            input.nextLine();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 4: //List all Students
                    for (Student s : students) {
                        System.out.println("\n--- Student " + (students.indexOf(s) + 1) + " Details ---");
                        s.Info();
                    }
                    if (students.isEmpty()) {
                        System.out.println("No students added.");
                    }
                    break;
                case 5: //List all External Members
                    for (ExternalMember e : external_members) {
                        System.out.println("\n--- Member " + (external_members.indexOf(e) + 1) + " Details ---");
                        e.Info();
                    }
                    if (external_members.isEmpty()) {
                        System.out.println("No regular members added.");
                    }
                    break;
                case 6: //List Movies
                    if (movies.isEmpty()) {
                        System.out.println("No movies added.");
                        break;
                    }
                    else {
                        System.out.println("\n--- Movie List ---");
                        System.out.printf("%-30s  %-12s  %-15s%n", "Movie Name", "Movie ID", "Status");
                        System.out.println("---------------------------------------------------------------");
                        for (Movie m : movies) {
                            boolean available = isAvailable(m.movieID, movie_rentals);
                            m.show(available); // Prints movies with availability details
                        }
                        System.out.println("---------------------------------------------------------------\n");
                    }
                    break;
                case 7: //Rent a movie
                    if (movies.isEmpty()) {
                        System.out.println("No movies added.");
                        break;
                    } // verify if there are any movies available
                    else if (movie_rentals.stream().anyMatch(r -> r.rentable)) {
                        System.out.println("Sorry, there are no movies available right now. Please try again later.");
                        break;
                    }
                    Person renter = null;
                    Movie movieToRent = null;
                    int nights = 0;
                    while (renter == null) {
                        try { // verify if there are students or regular members
                            if (students.isEmpty() && external_members.isEmpty()) {
                                throw new Exception("No students or regular members added. Add a student or regular member before renting a movie.");
                            }
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
                                for (ExternalMember e : external_members) {
                                    System.out.println("\n--- Member " + (external_members.indexOf(e) + 1) + " Details ---");
                                    e.Info();
                                }

                                System.out.print("Enter member's ID\n> ");
                                int clientID = input.nextInt();
                                for (ExternalMember e : external_members) {
                                    if (e.customerID == clientID) {
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
                            System.out.println("\n--- Available Movies ---");
                            System.out.printf("%-30s %-12s %-12s%n", "Movie Name", "Movie ID", "Status");
                            System.out.println("-------------------------------------------------------------");
                            for (Movie m : movies) {
                                boolean available = isAvailable(m.movieID, movie_rentals);
                                m.show(available);
                            }
                            System.out.println("-------------------------------------------------------------");
                            input.nextLine();
                            System.out.print("Enter movie name to rent\n> ");
                            String movie_name = input.nextLine();

                            for (Movie m : movies) {
                                if (m.name.toLowerCase().contains(movie_name.toLowerCase())) {
                                    movieToRent = m;
                                    break;
                                }
                            }
                            if (movieToRent == null) {
                                throw new Exception("Movie not found. Press 'Enter' to try again.");
                            } else if (!isAvailable(movieToRent.movieID, movie_rentals)) {
                                throw new Exception("Sorry, this movie is currently rented. Press 'Enter' to select another movie.");
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            movieToRent = null;
                        }
                    }

                    while (nights <= 0) {
                        try {
                            System.out.println("Began rental on: " + java.time.LocalDate.now());
                            System.out.print("Enter number of nights rented\n> ");
                            nights = input.nextInt();
                            if (nights <= 0) {
                                System.out.println("Invalid input. Number of nights must be valid.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            input.next();
                        }
                    }
                    String membershipType = renter.membership;
                    MovieRental rental = new MovieRental(renter.customerID, membershipType, Integer.parseInt(movieToRent.getMovieID()), nights, false);
                    movie_rentals.add(rental);
                    rental.Info(false); // this only to not show the fee just yet
                    System.out.println("Movie rented successfully.");

                    break;
                case 8: // Return a movie
                    if (movie_rentals.isEmpty()) {
                        System.out.println("Nobody rented movies.");
                        break;
                    }
                    else if (students.isEmpty() && external_members.isEmpty()) {
                        System.out.println("No students or regular members added. Add a student or regular member before renting a movie.");
                        break;
                    }

                    System.out.print("Was the renter a Student or a regular member? (Type 'Student' or 'Member')\n> ");
                    String inputRenterType = input.next();

                    ArrayList<Person> rentersList = new ArrayList<>();
                    if (inputRenterType.equalsIgnoreCase("Student")) {

                        for (Student s : students) {
                            for (MovieRental r : movie_rentals) {
                                if (r.customerID == s.customerID && !r.rentable) {
                                    rentersList.add(s);
                                    break;
                                }
                            }
                        }

                    }
                    else if (inputRenterType.equalsIgnoreCase("Member")) {
                        for (ExternalMember m : external_members) {
                            for (MovieRental r : movie_rentals) {
                                if (r.customerID == m.customerID && !r.rentable) {
                                    rentersList.add(m);
                                    break;
                                }
                            }
                        }

                    }
                    else {
                        System.out.println("Invalid input. Please type 'Student' or 'Member'.");
                        break;
                    }

                    if (rentersList.isEmpty()) {
                        System.out.println("No renters of this type currently have movies.");
                        break;
                    }

                    System.out.println("\n--- Renters Who Have Active Rentals ---");
                    System.out.printf("%-20s %-12s%n", "Name", "Customer ID");
                    System.out.println("-------------------------------------");
                    for (Person p : rentersList) {
                        System.out.printf("%-20s %-12d%n", p.name, p.customerID);
                    }
                    System.out.println("-------------------------------------");

                    System.out.print("Enter the renter's Customer ID\n> ");
                    int renterId = input.nextInt();
                    Person selectedRenter = null;

                    for (Person p : rentersList) {
                        if (p.customerID == renterId) {
                            selectedRenter = p;
                            break;
                        }
                    }

                    if (selectedRenter == null) {
                        System.out.println("Renter with that ID not found.");
                        break;
                    }
                    ArrayList<MovieRental> validRentals = new ArrayList<>();
                    for (MovieRental rental1 : movie_rentals) {
                        if (rental1.customerID == selectedRenter.customerID && !rental1.rentable) {
                            validRentals.add(rental1);
                        }
                    }

                    if (validRentals.isEmpty()) {
                        System.out.println("This renter has no movies currently rented.");
                        break;
                    }

                    System.out.println("\n" + selectedRenter.name + " has currently rented " + validRentals.size() + " movie(s):");
                    System.out.printf("%-30s  %-12s%n", "Movie Name", "Movie ID");
                    System.out.println("-----------------------------------------------");
                    for (MovieRental rental1 : validRentals) {
                        for (Movie movie : movies) {
                            if (Integer.parseInt(movie.getMovieID()) == rental1.movieID) {
                                movie.showWithoutStatus();
                            }
                        }
                    }
                    System.out.println("-----------------------------------------------");

                    boolean isReturned = false;

                    while (!isReturned) {
                        input.nextLine();
                        System.out.print("Enter the movie name you want to return: ");
                        String movie_name_return = input.nextLine();

                        for (MovieRental rental2 : validRentals) {
                            for (Movie movie : movies) {
                                if (movie.name.toLowerCase().contains(movie_name_return.toLowerCase()) &&
                                        Integer.parseInt(movie.getMovieID()) == rental2.movieID &&
                                        !rental2.rentable) {

                                    validRentals.remove(rental2);
                                    movie_rentals.remove(rental2);

                                    System.out.println("Movie '" + movie.name + "' has been successfully returned.");
                                    rental2.Info(true); // show fee

                                    isReturned = true;
                                    break;
                                }
                            }
                            if (isReturned) break;
                        }

                        if (!isReturned) {
                            System.out.println("The requested movie could not be returned. Press 'Enter' to try again.");
                        }
                    }

                    break;

                case 9: //Exit
                    System.out.println("Logging out...");
                    try {
                        FileWriter studentWriter = new FileWriter(studentFile);
                        FileWriter memberWriter = new FileWriter(memberFile);
                        FileWriter movieWriter = new FileWriter(movieFile);
                        FileWriter rentalWriter = new FileWriter(rentalFile);
                        for (Student s : students) {
                            studentWriter.write(s.toString() + "\n---\n");
                        }
                        studentWriter.close();
                        for (ExternalMember e : external_members) {
                            memberWriter.write(e.toString() + "\n---\n");
                        }
                        memberWriter.close();
                        for (Movie m : movies) {
                            boolean available = Main.isAvailable(m.movieID, movie_rentals);
                            movieWriter.write(m.toString(available) + "\n---\n");
                        }
                        movieWriter.close();
                        for (MovieRental rental1 : movie_rentals) {
                            rentalWriter.write(
                                    "Customer ID: " + rental1.customerID + "\n" +
                                            "Membership: " + rental1.membership + "\n" +
                                            "Movie ID: " + rental1.movieID + "\n" +
                                            "Nights Rented: " + rental1.nights_rented + "\n" +
                                            "Begin Date: " + rental1.beginDate + "\n" +
                                            "Return Date: " + rental1.returnDate + "\n" +
                                            "Status: " + (rental1.rentable ? "Available" : "Rented") + "\n" +
                                            "---\n"
                            );
                        }
                        rentalWriter.close();
                        System.out.println("Files saved.");
                    }
                    catch (IOException e) {
                        System.out.println("Error saving files");
                    }
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.\n");
                    break;
            }
        }
    }
    /** Helper function to check if a movie is available for rental or not*/
    private static boolean isAvailable(int movieID, ArrayList<MovieRental> rentals) {
        for (MovieRental r : rentals) {
            if (r.movieID == movieID && !r.rentable) {
                return false; //is rented
            }
        }
        return true; // not rented
    }
}
