import java.time.LocalDate;

/** MovieRental class. Access information about a movie rental.*/
public class MovieRental implements Payment {
    public int customerID;
    public String membership;
    public int movieID;
    public int nights_rented;
    public boolean rentable;
    public LocalDate beginDate;
    public LocalDate returnDate;
    /** MovieRental class constructor and attribute initializer.*/
    MovieRental(int customerID, String membership, int movieID, int nights_rented, boolean rentable) {
        this.customerID = customerID;
        this.membership = membership;
        this.movieID = movieID;
        this.nights_rented = nights_rented;
        this.rentable = rentable;
        this.beginDate = LocalDate.now();
        this.returnDate = beginDate.plusDays(nights_rented);
    }
    /** A getter method for a movie's availability.
     * Returns a condition ? valueIfTrue: valueIfFalse*/
    public String getMovieStatus() {
        return this.rentable ? "Available" : "Rented";
    }
    /** This method displays the rental information.*/
    public void Info(boolean showFee) {
        System.out.println("\n--- Movie Rental Information ---");
        System.out.printf("Customer ID     : %d%n", this.customerID);
        System.out.printf("Movie ID        : %d%n", this.movieID);
        System.out.printf("Nights Rented   : %d%n", this.nights_rented);
        System.out.printf("Begin Date      : %s%n", this.beginDate);
        System.out.printf("Return Date     : %s%n", this.returnDate);
        System.out.printf("Status          : %s%n", getMovieStatus());
        if (showFee) {
            System.out.printf("Rental Fee      : $%.2f%n", calculate());
        }
        System.out.println("---------------------------------");
    }



    /** This method calculates the rental fee based on the membership type.
     * If a student rents for more than 7 days, they get charged $1 extra per night.
     * If a regular member rents for more than 7 days, they get charged $2 extra per night.**/
    @Override
    public double calculate() {
        int additionalNights = 0;
        if (this.nights_rented > 7) {
            additionalNights = this.nights_rented - 7; // extra fees verifier
        }
        if (this.membership.equalsIgnoreCase("Student")) {
            return STUDENT_FEE + (additionalNights);  // $1 per extra night for students
        } else {
            return EXTERNAL_MEMBER_FEE + (additionalNights * 2); // $2 per extra night for members
        }

    }
}
