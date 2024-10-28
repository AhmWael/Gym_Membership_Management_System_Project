import java.util.*;
import java.io.*;

public class MemberDatabase implements DatabaseOperations, MemberDatabaseInterface{
    private ArrayList<Member> records;
    private String filename;

    public MemberDatabase(String filename){
        this.records = new ArrayList<Member>();
        this.filename = filename;
        this.readFromFile();
    }

    @Override
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
            //e.printStackTrace();
            System.out.println("Error reading file \"" + filename + "\"\nPlease try again later\nError: " + e.getMessage());
        }
    }
    @Override
    public Member createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Member(data[0], data[1], data[2], data[3], data[4], data[5]);
    }
    @Override
    public ArrayList<Member> returnAllRecords() {
        return records;
    }
    @Override
    public boolean contains(String key) {
        for (Member record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Member getRecord(String key) {
        for (Member record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }
    @Override
    public void insertRecord(Member record) {
        if(!contains(record.getSearchKey())){
            records.add(record);
            System.out.println("Member added successfully with ID: " + record.getSearchKey());
        }
        else {
            System.out.println("Member already exists");
        }
    }
    @Override
    public void deleteRecord(String key) {
        for (Member record : records) {
            if (record.getSearchKey().equals(key)) {
                records.remove(record);
                System.out.println("Member deleted successfully with ID: " + key);
                break;
            }
        }
    }
    @Override
    public void saveToFile() {

        try {
            System.out.println("Saving to file...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Member record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
            System.out.println("Member database saved successfully to file.");
        }
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error saving to file\nPlease try again later\nError: " + e.getMessage());
        }
    }
}
