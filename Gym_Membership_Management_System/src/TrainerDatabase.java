import java.util.*;
import java.io.*;

public class TrainerDatabase implements DatabaseOperations, TrainerDatabaseInterface {
    private ArrayList<Trainer> records;
    private String filename;

    @Override
    public void readFromFile(){
        try {
            System.out.println("Reading from file");
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line!= null) {
                String[] data = line.split(",");
                records.add(new Trainer(data[0], data[1], data[2], data[3], data[4]));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error reading file \"" + filename + "\"\nPlease try again later\nError: " + e);
        }
    }
    @Override
    public Trainer createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Trainer(data[0], data[1], data[2], data[3], data[4]);
    }
    @Override
    public ArrayList<Trainer> returnAllRecords(){
        return records;
    }
    @Override
    public boolean contains(String key){
        for (Trainer record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Trainer getRecord(String key){
        for (Trainer record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }
    @Override
    public void insertRecord(Trainer record){
        if(!contains(record.getSearchKey())){
            records.add(record);
            System.out.println("Trainer added successfully with ID: " + record.getSearchKey());
        }
        else {
            System.out.println("Trainer already exists");
        }
    }
    @Override
    public void deleteRecord(String key){
        for (Trainer record : records) {
            if (record.getSearchKey().equals(key)) {
                records.remove(record);
                System.out.println("Trainer removed successfully with ID: " + key);
                break;
            }
        }
    }
    @Override
    public void saveToFile(){
        try {
            System.out.println("Saving to file...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Trainer record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
            System.out.println("Trainer database saved successfully to file.");
        }
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error saving to file\nPlease try again later\nError: " + e);
        }
    }

}
