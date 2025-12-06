/** This exception class executes if a user entered an exising ID
 * for movies, students or members.
 *
 */

public class DuplicateID extends RuntimeException {
    public DuplicateID(String message) {
        super(message);
    }
}
