package backend;

import java.util.*;

public class AdminRole {
    private TrainerDatabase database;

    public AdminRole() {
        this.database = new TrainerDatabase("Trainers.txt");
    }

    public void addTrainer(String trainerID, String name, String email, String speciality, String phoneNumber) {
        Trainer trainer = new Trainer(trainerID, name, email, speciality, phoneNumber);
        database.insertRecord(trainer);
    }
    public ArrayList<StorableData> getListOfTrainers() {
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
