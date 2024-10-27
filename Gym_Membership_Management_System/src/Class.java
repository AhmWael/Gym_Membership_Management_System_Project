import java.util.StringJoiner;

public class Class implements StorableData{
    private String classID;
    private String className;
    private String trainerID;
    private int availableSeats;
    private int totalSeats;

    public Class(String classID, String className, String trainerID, int availableSeats, int totalSeats) {
        this.classID = classID;
        this.className = className;
        this.trainerID = trainerID;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int value) {
        this.availableSeats = value;
    }

    @Override
    public String lineRepresentation() {
        return String.join(",",
                classID,
                className,
                trainerID,
                String.valueOf(availableSeats),
                String.valueOf(totalSeats));
    }

    @Override
    public String getSearchKey() {
        return classID;
    }
}
