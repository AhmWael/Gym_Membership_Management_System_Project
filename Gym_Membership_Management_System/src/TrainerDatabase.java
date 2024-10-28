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

    @Override
    protected String lineRepresentation(Object record) {
        return ((Trainer) record).lineRepresentation();
    }

    @Override
    protected String getSearchKey(Object record) {
        return ((Trainer) record).getSearchKey();
    }

    public ArrayList<Trainer> returnAllRecords(){
        ArrayList<Trainer> trainers = new ArrayList<>();
        for (Object record : records) {
            trainers.add((Trainer) record);
        }
        return trainers;
    }
}
