package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelRegistrationWindow extends JFrame{
    private JTextField memIDTF;
    private JTextField classIDTF;
    private JButton cancelRegistrationButton;
    private JPanel CancelRegistrationPanel;
    private JButton exitButton;
    private JButton backButton;
    private final TrainerRoleWindow mainForm;

    public CancelRegistrationWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(CancelRegistrationPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Cancel Registration");
        setVisible(true);
        cancelRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memID = memIDTF.getText();
                String classID = classIDTF.getText();
                if(memID.isEmpty() || classID.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                else if (trainer.getListOfClasses().stream().noneMatch(Class -> Class.getSearchKey().equals(classID))) {
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (trainer.getListOfMembers().stream().noneMatch(Member -> Member.getSearchKey().equals(memID))) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(trainer.cancelRegistration(memID, classID)){
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "The registration for member with ID: " + memID + " for class with ID: " + classID + " has been cancelled successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        mainForm.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "The cancellation was not successful", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memIDTF.getText().isEmpty() && classIDTF.getText().isEmpty()){
                    setVisible(false);
                    dispose();
                    mainForm.setVisible(true);
                }
                else {
                    int response = JOptionPane.showConfirmDialog(null, "Unsaved changes will be lost! Do you wish to continue?", "Confirm Back", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        setVisible(false);
                        dispose();
                        mainForm.setVisible(true);
                    }
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Unsaved changes will be lost! Do you wish to continue?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        getRootPane().setDefaultButton(cancelRegistrationButton);
    }
}
