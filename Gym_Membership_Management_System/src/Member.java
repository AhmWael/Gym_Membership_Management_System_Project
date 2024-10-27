import java.util.*;

public class Member implements StorableData{
    private String memberID;
    private String name;
    private String membershipType;
    private String email;
    private String phoneNumber;
    private String status;

    public Member(String memberID, String name, String membershipType, String email, String phoneNumber, String status){
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String lineRepresentation() {
        return String.join(",",
            this.memberID,
            this.name,
            this.membershipType,
            this.email,
            this.phoneNumber,
            this.status);

    }
    public String getSearchKey(){
        return this.memberID;
    }

}