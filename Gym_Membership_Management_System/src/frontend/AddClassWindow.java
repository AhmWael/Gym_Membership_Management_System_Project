package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassWindow extends JFrame {
    private JPanel AddClassPanel;
    private JTextField classIDTF;
    private JTextField classNameTF;
    private JTextField trainerIDTF;
    private JTextField durationTF;
    private JTextField maxPartiTF;
    private JButton addButton;
    private final TrainerRoleWindow mainForm;

    public AddClassWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(AddClassPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classID = classIDTF.getText();
                String className = classNameTF.getText();
                String trainerID = trainerIDTF.getText();
                String duration = durationTF.getText();
                String maxParti = maxPartiTF.getText();

                if(classID.isEmpty() || className.isEmpty() || trainerID.isEmpty() || duration.isEmpty() || maxParti.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfClasses().contains(classID)) {
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " already exists!");
                } else {
                    setVisible(false);
                    trainer.addClass(classID, className, trainerID, Integer.parseInt(duration), Integer.parseInt(maxParti));
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " added successfully");
                    dispose();
                    mainForm.setVisible(true);
                }
            }
        });
    }
}
