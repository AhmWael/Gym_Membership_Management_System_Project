public class Trainer implements StorableData{
    private String trainerID;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;

    public Trainer(String trainerID, String name, String email, String speciality, String phoneNumber) {
        this.trainerID = trainerID;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation(){
        return String.join(",",
                this.trainerID,
                this.name,
                this.email,
                this.speciality,
                this.phoneNumber);
    }

    public String getSearchKey(){
        return this.trainerID;
    }
}
