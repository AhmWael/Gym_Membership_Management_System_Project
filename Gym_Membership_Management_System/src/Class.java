import java.util.StringJoiner;

public class Class implements StorableData{
    private String classID;
    private String className;
    private String trainerID;
    private int availableSeats;
    private int duration;

    public Class(String classID, String className, String trainerID, int duration, int availableSeats) {
        this.classID = classID;
        this.className = className;
        this.trainerID = trainerID;
        this.availableSeats = availableSeats;
        this.duration = duration;
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
                String.valueOf(duration),
                String.valueOf(availableSeats));
    }

    @Override
    public String getSearchKey() {
        return classID;
    }
}
