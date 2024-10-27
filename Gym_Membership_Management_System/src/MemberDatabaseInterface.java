import java.util.*;

public interface MemberDatabaseInterface {
    public Member createRecordFrom(String line);
    public ArrayList<Member> returnAllRecords();
    public Member getRecord(String key);
    public void insertRecord(Member record);
}
