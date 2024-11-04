package frontend;

import javax.swing.*;
import backend.AdminRole;
import backend.Trainer;
import backend.TrainerDatabase;
import constants.FileNames;

public class AddTrainer extends JFrame {
    private JPanel AddTrainerPanel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField specialtyField;
    private JTextField phoneNumberField;
    private JButton addButton;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel specialtyLabel;
    private JLabel phoneNumberLabel;

    public AddTrainer() {
        setVisible(true);
        setSize(320, 240);
        setTitle("Add Trainer");
        setLocationRelativeTo(null);
        setContentPane(AddTrainerPanel);

        addButton.addActionListener(e -> {
            String ID = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String specialty = specialtyField.getText();
            String phoneNumber = phoneNumberField.getText();

            if (!ID.isEmpty() && !name.isEmpty() && !email.isEmpty() && !specialty.isEmpty() && !phoneNumber.isEmpty()) {
                TrainerDatabase trainerDB = new TrainerDatabase(FileNames.TRAINER_FILENAME);
                if (trainerDB.contains(ID)) {
                    JOptionPane.showMessageDialog(null, "The trainer with ID = " + ID + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Trainer trainer1 = new Trainer(ID, name, email, specialty, phoneNumber);
                    trainerDB.insertRecord(trainer1);
                    trainerDB.saveToFile();
                    for (Trainer trainer : trainerDB.returnAllRecords().toArray(new Trainer[0])) {
                        System.out.println(trainer.lineRepresentation());
                    }
                    JOptionPane.showMessageDialog(null, "Trainer with ID = " + ID + " added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Some fields are empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
