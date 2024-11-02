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
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfClasses().stream().noneMatch(Class -> Class.getSearchKey().equals(classID))) {
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " does not exist!");
                } else if (trainer.getListOfMembers().stream().noneMatch(Member -> Member.getSearchKey().equals(memID))) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " does not exist!");
                }
                else{
                    if(trainer.cancelRegistration(memID, classID)){
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "The registration for member with ID: " + memID + " for class with ID: " + classID + " has been cancelled successfully");
                        dispose();
                        mainForm.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "The cancellation was not successful");
                }
            }
        });
    }
}
