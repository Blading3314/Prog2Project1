/** Abstract class Person. It is the parent of Student and External_Member.
 * This class defines common attributes and methods for all persons in the system.
 * It is declared abstract because it represents a generic concept of a person and
 * cannot be instantiated on its own. */
public abstract class Person {
    String name;
    int customerID;
    String membership;
    /** Person class constructor and attribute initializer.*/
    Person(String name, int customerID) {
        this.name = name;
        this.customerID = customerID;
    }
    /**
     * Returns a string representation of the person's details.
     *
     * @return a formatted string containing name, customer ID, and membership type (appears in file).
     */
    public String toString() {
        return "Name: " + this.name + "\nCustomer ID: " + this.customerID + "\nMembership: " + this.membership;
    }
    /** Displays detailed information about the person.
     * Subclasses must implement this to print their specific attributes.
     */
    abstract public void Info();
}