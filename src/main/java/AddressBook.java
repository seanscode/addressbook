import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AddressBook {

    private static DateFormat DD_MM_YY = new SimpleDateFormat("dd/MM/yy");
    private List<AddressBookEntry> entries = new ArrayList<>();

    public AddressBook(File file) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            final String[] splitEntry = line.split(",");

            AddressBookEntry entry = new AddressBookEntry(splitEntry[0], splitEntry[1].trim(), DD_MM_YY.parse(splitEntry[2].trim()));
            entries.add(entry);

        }
    }

    public long getDaysBetween(String name1, String name2) {
        long timeBetween =  getDobByName(name2).getTime()- getDobByName(name1).getTime();
        return TimeUnit.DAYS.convert(timeBetween, TimeUnit.MILLISECONDS);
    }


    public long getCountByGender(String gender) {
        return entries.stream().filter(e -> e.getGender().equals(gender)).count();
    }

    public String getNameOfOldest() {
        return entries.stream().min(Comparator.comparing(AddressBookEntry::getDob)).get().getName();
    }

    private Date getDobByName(String name) {
        return entries.stream().filter(e -> e.getName().equals(name)).findFirst().get().getDob();
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        File file = new File("AddressBook");

        final AddressBook addressBook = new AddressBook(file);


        System.out.println("Total Number of Male Entries " + addressBook.getCountByGender("Male"));
        System.out.println("The oldest Person is " + addressBook.getNameOfOldest());
        final long daysDifference = addressBook.getDaysBetween("Bill McKnight", "Paul Robinson");
        System.out.println("Bill is " + daysDifference + " days older than Paul");


    }
}
