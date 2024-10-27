import java.time.LocalDate;

public class MemberClassRegistration implements StorableData {
    private String memberID;
    private String classID;
    private String status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberID, String classID, String status, LocalDate registrationDate) {
        this.memberID = memberID;
        this.classID = classID;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String getSearchKey() {
        return memberID + classID;
    }

    @Override
    public String lineRepresentation() {
        return String.join(",",
                memberID,
                classID,
                registrationDate.toString(),
                status);
    }
}
