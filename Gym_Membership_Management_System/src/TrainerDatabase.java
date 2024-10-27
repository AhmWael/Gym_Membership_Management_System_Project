import java.util.*;

public class TrainerDatabase implements DatabaseOperations, TrainerDatabaseInterface {
    private ArrayList<Trainer> records;
    private String filename;

    @Override
    public void readFromFile(){

    }
    @Override
    public Trainer createRecordFrom(String line) {
        return null;
    }
    @Override
    public ArrayList<Trainer> returnAllRecords(){
        return null;
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
