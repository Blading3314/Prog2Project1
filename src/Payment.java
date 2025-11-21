/**
 * Interface representing a payment calculation for movie rentals.
 * Any class implementing this interface must provide a method to calculate the rental fee.
 */
public interface Payment {

    /** Base rental fee for a student for the first week is 5 dollars. */
    double STUDENT_FEE = 5.0;

    /** Base rental fee for an external member (or just a client) for the first week is 10 dollars. */
    double EXTERNAL_MEMBER_FEE = 10.0;

    /**
     * Calculates the total rental fee for a movie based on the membership type
     * and the number of days the movie has been rented.
     * @return the total rental fee as a double
     */
    double calculate();
}
