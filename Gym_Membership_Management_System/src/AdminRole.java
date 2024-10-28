import java.util.*;

public class AdminRole {
    private TrainerDatabase database;

    public AdminRole() {
        this.database = new TrainerDatabase("Trainers.txt");
    }

    public void addTrainer(String trainerID, String name, String email, String speciality, String phoneNumber) {
        if(database.contains(trainerID)) {
            System.out.println("Trainer already exists");
        } else {
            Trainer trainer = new Trainer(trainerID, name, email, speciality, phoneNumber);
            database.insertRecord(trainer);
            System.out.println("Trainer added successfully with ID: " + trainerID);
        }
    }
    public ArrayList<Trainer> getListOfTrainers() {
        return database.returnAllRecords();
    }
    public void removeTrainer(String key) {
        database.deleteRecord(key);
    }
    public void logout() {
        database.saveToFile();
        System.out.println("Logged out successfully");
    }
}
