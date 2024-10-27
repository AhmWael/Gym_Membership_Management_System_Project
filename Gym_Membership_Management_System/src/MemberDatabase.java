import java.util.*;
import java.io.*;

public class MemberDatabase implements DatabaseOperations, MemberDatabaseInterface{
    private ArrayList<Member> records;
    private String filename;

    @Override
    public void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                break;
            }
        }
    }
    @Override
    public void saveToFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Member record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
