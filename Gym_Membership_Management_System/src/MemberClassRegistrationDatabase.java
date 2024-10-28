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

    @Override
    protected String lineRepresentation(Object record) {
        return ((MemberClassRegistration) record).lineRepresentation();
    }

    @Override
    protected String getSearchKey(Object record) {
        return ((MemberClassRegistration) record).getSearchKey();
    }

    public ArrayList<MemberClassRegistration> returnAllRecords() {
        ArrayList<MemberClassRegistration> registrations = new ArrayList<>();
        for (Object record : records) {
            registrations.add((MemberClassRegistration) record);
        }
        return registrations;
    }





}
