package backend;

import java.util.*;

public class TrainerDatabase extends AbstractDatabase {

    public TrainerDatabase(String filename){
        super(filename);
    }

    @Override
    public Trainer createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Trainer(data[0], data[1], data[2], data[3], data[4]);
    }
}
