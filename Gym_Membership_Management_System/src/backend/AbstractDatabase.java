package backend;

import java.io.*;
import java.util.*;

public abstract class AbstractDatabase{
    protected ArrayList<StorableData> records;
    protected String filename;

    public AbstractDatabase(String filename) {
        this.records = new ArrayList<StorableData>();
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
            for (StorableData record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
            System.out.println("Database saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file\nPlease try again later\nError: " + e.getMessage());
        }
    }

    public boolean contains(String key) {
        for (StorableData record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public StorableData getRecord(String key) {
        for (StorableData record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(StorableData record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            System.out.println("Record added successfully with ID: " + record.getSearchKey());
        } else {
            System.out.println("Record already exists");
        }
    }

    public void deleteRecord(String key) {
        for (StorableData record : records) {
            if (record.getSearchKey().equals(key)) {
                records.remove(record);
                System.out.println("Record deleted successfully with ID: " + key);
                break;
            }
        }
    }

    public ArrayList<StorableData> returnAllRecords() {
        return records;
    }

    public abstract StorableData createRecordFrom(String line);

}
