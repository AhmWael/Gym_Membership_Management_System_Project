import java.util.ArrayList;

public class MemberDatabase implements DatabaseOperations{
    private ArrayList<Member> records;
    private String filename;

    @Override
    public void readFromFile() {

    }
    @Override
    public Member createRecordFrom(String line) {
        return null;
    }
    @Override
    public ArrayList<Member> returnAllRecords() {
        return null;
    }
    @Override
    public boolean contains(String key) {
        return false;
    }
    @Override
    public Member getRecord(String key) {
        return null;
    }
    @Override
    public void insertRecord(Member record) {

    }
    @Override
    public void deleteRecord(String key) {

    }
    @Override
    public void saveToFile(String filename) {

    }
}
