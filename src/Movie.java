/** Movie class. This class holds the movie information such as its name and ID.*/
public class Movie {
    public int movieID;
    public String name;
    /** Movie class constructor and attribute initializer.*/
    Movie(int movieID, String name) {
        this.movieID = movieID;
        this.name = name;
    }
    /** A getter method for movieID and returns a string representation of the object.
     * @return a formatted string containing movie name and movie ID.*/
    public String getMovieID() {
        return String.valueOf(this.movieID);
    }
    /** This method displays the movie information.
     * @param isAvailable a boolean value to indicate the movie status. */
    public void show(boolean isAvailable) {
        String availability = isAvailable ? "Available" : "Rented";
        // Printing a string, left aligned and takes up 30, 12 characters.
        System.out.printf("%-30s  %-12d  %-15s%n", this.name, this.movieID, availability);
    }
    /** This method displays the movie information without the status for case 8.
     * In case 8, I didn't want to show the status just yet.*/
    public void showWithoutStatus() {
        System.out.printf(" %-25s  %-10d %n", this.name, this.movieID);
    }
    /** This method returns a string representation of the object.
     * @return a formatted string containing movie name, movie ID, and availability (appears in file). */
    public String toString(boolean isAvailable) {
        String status = isAvailable ? "Available" : "Rented";
        return this.name + " - Movie ID: " + this.movieID + " - Status: " + status;
    }

}
