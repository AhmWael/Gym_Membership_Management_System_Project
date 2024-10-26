import java.util.*;

public interface DatabaseOperations {
    public void readFromFile();
    public Trainer createRecordFrom(String line);
    public ArrayList<Trainer> returnAllRecords();
    public boolean contains(String key);
    public Trainer getRecord(String key);
    public void insertRecord(Trainer record);
    public void deleteRecord(String key);
    public void saveToFile(String filename);
}
