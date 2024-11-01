import java.util.*;
import java.io.*;

public class MemberDatabase extends AbstractDatabase{

    public MemberDatabase(String filename){
        super(filename);
    }

    @Override
    public Member createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Member(data[0], data[1], data[2], data[3], data[4], data[5]);
    }
}
