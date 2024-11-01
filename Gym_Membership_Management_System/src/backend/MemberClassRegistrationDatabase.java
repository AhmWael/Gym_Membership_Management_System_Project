package backend;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberClassRegistrationDatabase extends AbstractDatabase {

    public MemberClassRegistrationDatabase(String filename) {
        super(filename);
    }

    @Override
    public MemberClassRegistration createRecordFrom(String line) {
        String[] data = line.split(",");
        return new MemberClassRegistration(data[0], data[1], LocalDate.parse(data[2]), data[3]);
    }
}
