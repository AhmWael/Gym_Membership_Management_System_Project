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
}
