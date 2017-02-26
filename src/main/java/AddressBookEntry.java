import java.util.Date;

public class AddressBookEntry {
    private String name;
    private String gender;
    private Date dob;

    public AddressBookEntry(String name, String gender, Date dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

}