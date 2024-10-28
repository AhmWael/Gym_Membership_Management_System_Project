import java.io.*;
import java.util.ArrayList;


public class ClassDatabase extends AbstractDatabase {

    public ClassDatabase(String filename){
        super(filename);
    }

    @Override
    public Class createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Class(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
    }

    @Override
    protected String lineRepresentation(Object record) {
        return ((Class) record).lineRepresentation();
    }

    @Override
    protected String getSearchKey(Object record) {
        return ((Class) record).getSearchKey();
    }

    public ArrayList<Class> returnAllRecords() {
        ArrayList<Class> classes = new ArrayList<>();
        for (Object record : records) {
            classes.add((Class) record);
        }
        return classes;
    }


}
