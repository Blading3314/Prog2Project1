/** A regular client class. This class receives a client's info such as his/her job and organization name. */
public class ExternalMember extends Person {
    public String job;
    String org_name;
    public ExternalMember(String name, int customerID, String job, String org_name) {
        super(name, customerID);
        this.name = name;
        this.customerID = customerID;
        this.membership = "Regular";
        this.job = job;
        this.org_name = org_name;
    }
    /** Show external member's info.*/
    public void Info() {
        System.out.printf("Name         : %s%n", this.name);
        System.out.printf("Customer ID  : %d%n", this.customerID);
        System.out.printf("Membership   : %s%n", this.membership);
        System.out.printf("Job          : %s%n", this.job);
        System.out.printf("Organization : %s%n", this.org_name);
        System.out.println("---------------------------------\n");
    }
    /** This method returns a string representation of the object.
     * @return a formatted string containing name, customer ID, membership type, job, and organization name (appears in file). */
    public String toString() {
        return super.toString() + "\nJob: " + this.job + "\nOrganization Name: " + this.org_name;
    }

}
