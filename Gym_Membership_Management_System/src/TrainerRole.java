import java.time.LocalDate;
import java.util.ArrayList;

public class TrainerRole {
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole(MemberDatabase memberDB, ClassDatabase classDB, MemberClassRegistrationDatabase registrationDB) {
        this.memberDatabase = memberDB;
        this.classDatabase = classDB;
        this.registrationDatabase = registrationDB;
    }

    public void addMember(String MemberID, String name, String membershipType, String email, String phoneNumber, String status) {
        Member member = new Member(MemberID, name, membershipType, email, phoneNumber, status);
        if (!memberDatabase.contains(MemberID)) {
            memberDatabase.insertRecord(member);
        }
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        if (!classDatabase.contains(classID)) {
            classDatabase.insertRecord(newClass);
        }
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        Class classRecord = classDatabase.getRecord(classID);
        int availableSeats = classRecord.getAvailableSeats();
        Member memberRecord = memberDatabase.getRecord(memberID);

        if (classRecord == null || memberRecord == null) {
            return false;
        } else if (availableSeats < 1) {
            return false;
        }

        MemberClassRegistration registration = new MemberClassRegistration(memberID, classID, "Active", registrationDate);
        if (!registrationDatabase.contains(registration.getSearchKey())) {
            registrationDatabase.insertRecord(registration);
            classDatabase.getRecord(classID).setAvailableSeats(availableSeats - 1);
            return true;
        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        Member memberRecord = memberDatabase.getRecord(memberID);
        Class classRecord = classDatabase.getRecord(classID);
        int availableSeats = classRecord.getAvailableSeats();
        MemberClassRegistration registration = registrationDatabase.getRecord(memberID + classID);

        if (memberRecord == null || classRecord == null || registration == null) {
            return false;
        } else if (registration.getRegistrationDate().isAfter(LocalDate.now().minusDays(3))) {
            registrationDatabase.deleteRecord(memberID + classID);
            classDatabase.getRecord(classID).setAvailableSeats(availableSeats + 1);
            return true;
        }
        return false;
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
    }
}
