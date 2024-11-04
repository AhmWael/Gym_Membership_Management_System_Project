package frontend;

import backend.TrainerDatabase;

import javax.swing.*;

public class RemoveTrainer extends JFrame {
    private JTextField textField1;
    private JButton button1;
    private JPanel RemoveTrainerPanel;

    public RemoveTrainer() {
        setContentPane(RemoveTrainerPanel);
        setSize(320, 160);
        setLocationRelativeTo(null);
        setVisible(true);

        button1.addActionListener(e -> {
            String trainerID = textField1.getText();
            if (trainerID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Trainer ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                TrainerDatabase trainerDB = new TrainerDatabase(constants.FileNames.TRAINER_FILENAME);
                if (trainerDB.contains(trainerID)) {
                    trainerDB.deleteRecord(trainerID);
                    trainerDB.saveToFile();
                    JOptionPane.showMessageDialog(null, "Trainer with ID = " + trainerID + " removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Trainer with ID = " + trainerID + " does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
