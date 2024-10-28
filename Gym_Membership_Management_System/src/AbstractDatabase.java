import java.io.*;
import java.util.*;

public abstract class AbstractDatabase implements DatabaseOperations{
    protected ArrayList<Object> records;
    protected String filename;

    public AbstractDatabase(String filename) {
        this.records = new ArrayList<>();
        this.filename = filename;
        this.readFromFile();
    }

    public void readFromFile() {
        try {
            System.out.println("Reading file...");
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file \"" + filename + "\"\nPlease try again later\nError: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            System.out.println("Saving to file...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Object record : records) {
                writer.write(lineRepresentation(record));
                writer.newLine();
            }
            writer.close();
            System.out.println("Database saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file\nPlease try again later\nError: " + e.getMessage());
        }
    }

    public boolean contains(String key) {
        for (Object record : records) {
            if (getSearchKey(record).equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Object getRecord(String key) {
        for (Object record : records) {
            if (getSearchKey(record).equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Object record) {
        if (!contains(getSearchKey(record))) {
            records.add(record);
            System.out.println("Record added successfully with ID: " + getSearchKey(record));
        } else {
            System.out.println("Record already exists");
        }
    }

    public void deleteRecord(String key) {
        for (Object record : records) {
            if (getSearchKey(record).equals(key)) {
                records.remove(record);
                System.out.println("Record deleted successfully with ID: " + key);
                break;
            }
        }
    }

    protected abstract Object createRecordFrom(String line);
    protected abstract String lineRepresentation(Object record);
    protected abstract String getSearchKey(Object record);
}
