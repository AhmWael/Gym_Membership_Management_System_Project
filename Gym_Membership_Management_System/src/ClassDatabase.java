import java.io.*;
import java.util.ArrayList;


public class ClassDatabase implements DatabaseOperations {
    private ArrayList<Class> records;
    private String filename;

    public ClassDatabase(String filename){
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

    public Class createRecordFromLine(String line) {
        String[] data = line.split(",");
        return new Class(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
    }

    public ArrayList<Class> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (Class record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Class getRecord(String key) {
        for (Class record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Class record) {
        if (contains(record.getSearchKey())) return;
        records.add(record);
    }

    public void deleteRecord(String key) {
        for (Class record : records) {
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
            for (Class record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
