import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberClassRegistrationDatabase implements DatabaseOperations {
    ArrayList<MemberClassRegistration> records;
    String filename;

    public MemberClassRegistrationDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    @Override
    public void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                records.add(createRecordFromLine(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemberClassRegistration createRecordFromLine(String line) {
        String[] data = line.split(",");
        return new MemberClassRegistration(data[0], data[1], data[2], LocalDate.parse(data[3]));
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
        if (!contains(record.getSearchKey())) return;
        records.add(record);
    }

    public void deleteRecord(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                records.remove(record);
                return;
            }
        }
    }

    @Override
    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (MemberClassRegistration record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
