import java.util.*;
import java.io.*;

public class TrainerDatabase implements DatabaseOperations, TrainerDatabaseInterface {
    private ArrayList<Trainer> records;
    private String filename;

    @Override
    public void readFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                records.add(new Trainer(data[0], data[1], data[2], data[3], data[4]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Trainer createRecordFrom(String line) {
        String data[] = line.split(",");
        return new Trainer(data[0], data[1], data[2], data[3], data[4]);
    }
    @Override
    public ArrayList<Trainer> returnAllRecords(){
        return records;
    }
    @Override
    public boolean contains(String key){

        return false;
    }
    @Override
    public Trainer getRecord(String key){
        return null;
    }
    @Override
    public void insertRecord(Trainer record){

    }
    @Override
    public void deleteRecord(String key){

    }
    @Override
    public void saveToFile(String filename){

    }

}
