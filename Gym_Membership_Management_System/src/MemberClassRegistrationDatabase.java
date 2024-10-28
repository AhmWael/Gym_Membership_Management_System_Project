import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberClassRegistrationDatabase implements DatabaseOperations {
    ArrayList<MemberClassRegistration> records;
    String filename;

    public MemberClassRegistrationDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        this.readFromFile();
    }

    @Override
    public void readFromFile() {
        try {
            System.out.println("Reading file...");
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                records.add(createRecordFromLine(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file \"" + filename + "\"\nPlease try again later\nError: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    public MemberClassRegistration createRecordFromLine(String line) {
        String[] data = line.split(",");
        return new MemberClassRegistration(data[0], data[1], LocalDate.parse(data[2]), data[3]);
    }

    public ArrayList<MemberClassRegistration> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public MemberClassRegistration getRecord(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(MemberClassRegistration record) {
        if (contains(record.getSearchKey())) {
            System.out.println("Member Class Registration already exists");
            return;
        }
        records.add(record);
        System.out.println("Member Class Registration added successfully with ID: " + record.getSearchKey());
    }

    public void deleteRecord(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                records.remove(record);
                System.out.println("Member Class Registration deleted successfully with ID: " + key);
                return;
            }
        }
    }

    @Override
    public void saveToFile() {
        try {
            System.out.println("Saving to file...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (MemberClassRegistration record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
            System.out.println("Member Class Registration database saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file \"" + filename + "\"\nPlease try again later\nError: " + e.getMessage());
            //e.printStackTrace();
        }
    }
}
