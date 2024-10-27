import java.util.*;

public interface TrainerDatabaseInterface {
    public Trainer createRecordFrom(String line);
    public ArrayList<Trainer> returnAllRecords();
    public Trainer getRecord(String key);
    public void insertRecord(Trainer record);
}
