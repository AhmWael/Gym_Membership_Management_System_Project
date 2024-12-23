package backend;

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

    public ArrayList<StorableData> getListOfMembers() {
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
        ArrayList<Class> classes = new ArrayList<Class>();
        for (StorableData record : classDatabase.returnAllRecords()) {
            classes.add((Class) record);
        }
        return classes;
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        Class classRecord = (Class) classDatabase.getRecord(classID);
        Member memberRecord = (Member) memberDatabase.getRecord(memberID);


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
            ((Class) classDatabase.getRecord(classID)).setAvailableSeats(availableSeats - 1);
            return true;
        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        Member memberRecord = (Member) memberDatabase.getRecord(memberID);
        Class classRecord = (Class) classDatabase.getRecord(classID);
        MemberClassRegistration registration = (MemberClassRegistration) registrationDatabase.getRecord(memberID + classID);

        if (memberRecord == null || classRecord == null || registration == null) {
            return false;
        } else if (registration.getRegistrationDate().isAfter(LocalDate.now().minusDays(3))) {
            int availableSeats = classRecord.getAvailableSeats();
            registrationDatabase.deleteRecord(registration.getSearchKey());
            //registration.setRegistrationStatus("canceled");
            ((Class) classDatabase.getRecord(classID)).setAvailableSeats(availableSeats + 1);
            System.out.println("Registration canceled successfully\nRegistration refunded");
            return true;
        }
        return false;
    }

    public ArrayList<StorableData> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
    }
}
