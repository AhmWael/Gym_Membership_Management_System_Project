import java.time.LocalDate;
import java.util.ArrayList;

public class TrainerRole {
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole() {
        this.memberDatabase = new MemberDatabase("Members.txt");
        this.classDatabase = new ClassDatabase("Classes.txt");
        this.registrationDatabase = new MemberClassRegistrationDatabase("Registrations.txt");
    }

    public void addMember(String MemberID, String name, String membershipType, String email, String phoneNumber, String status) {
        if (!memberDatabase.contains(MemberID)) {
            Member member = new Member(MemberID, name, membershipType, email, phoneNumber, status);
            memberDatabase.insertRecord(member);
        }
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        if (!classDatabase.contains(classID)) {
            Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
            classDatabase.insertRecord(newClass);
        }
        else{
            System.out.println("Class already exists");
        }
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        Class classRecord = classDatabase.getRecord(classID);
        Member memberRecord = memberDatabase.getRecord(memberID);

        if (classRecord == null || memberRecord == null) {
            return false;
        }
        int availableSeats = classRecord.getAvailableSeats();
        if (availableSeats < 1) {
            System.out.println("No available seats");
            return false;
        }

        MemberClassRegistration registration = new MemberClassRegistration(memberID, classID, registrationDate, "Active");
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
        MemberClassRegistration registration = registrationDatabase.getRecord(memberID + "-" + classID);

        if (memberRecord == null || classRecord == null || registration == null) {
            return false;
        } else if (registration.getRegistrationDate().isAfter(LocalDate.now().minusDays(3))) {
            int availableSeats = classRecord.getAvailableSeats();
            registration.setRegistrationStatus("canceled");
            classDatabase.getRecord(classID).setAvailableSeats(availableSeats + 1);
            System.out.println("Registration canceled successfully\nRegistration refunded");
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
