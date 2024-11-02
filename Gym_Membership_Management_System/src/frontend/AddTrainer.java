package frontend;

import javax.swing.*;
import backend.AdminRole;
import backend.Trainer;
import backend.TrainerDatabase;
import constants.FileNames;

public class AddTrainer extends JFrame {
    private JPanel AddTrainerPanel;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    public AddTrainer() {
        setVisible(true);
        setSize(320, 240);
        setTitle("Add Trainer");
        setLocationRelativeTo(null);
        setContentPane(AddTrainerPanel);

        button1.addActionListener(e -> {
            String ID = textField1.getText();
            String name = textField2.getText();
            String email = textField3.getText();
            String specialty = textField4.getText();
            String phoneNumebr = textField5.getText();


            if (!ID.isEmpty() && !name.isEmpty() && !email.isEmpty() && !specialty.isEmpty() && !phoneNumebr.isEmpty()) {
                TrainerDatabase trainerDB = new TrainerDatabase(FileNames.TRAINER_FILENAME);
                if (trainerDB.contains(ID)) {
                    JOptionPane.showMessageDialog(null, "The trainer with ID = " + ID + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                } else (new AdminRole()).addTrainer(ID, name, email, specialty, phoneNumebr);
            } else {
                JOptionPane.showMessageDialog(null, "Some fields are empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
