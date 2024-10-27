import java.util.*;

public interface DatabaseOperations {
    public void readFromFile();
    public boolean contains(String key);
    public void deleteRecord(String key);
    public void saveToFile();
}
