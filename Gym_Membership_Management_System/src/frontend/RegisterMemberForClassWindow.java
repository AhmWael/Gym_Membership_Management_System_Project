package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class RegisterMemberForClassWindow extends JFrame {
    private JPanel RegisterMemberForClassPanel;
    private JTextField memIDTF;
    private JTextField classIDTF;
    private JButton registerButton;
    private JButton exitButton;
    private JButton backButton;
    private final TrainerRoleWindow mainForm;

    public RegisterMemberForClassWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(RegisterMemberForClassPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Register Member");
        setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memID = memIDTF.getText();
                String classID = classIDTF.getText();
                if(memID.isEmpty() || classID.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfMembers().stream().noneMatch(member -> member.getSearchKey().equals(memID))) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " does not exist!");
                } else if (trainer.getListOfClasses().stream().noneMatch(Class -> Class.getSearchKey().equals(classID))) {
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " does not exist!");
                } else {
                    setVisible(false);
                    trainer.registerMemberForClass(memID, classID, LocalDate.now());
                    JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " registered successfully for class with ID: " + classID);
                    dispose();
                    mainForm.setVisible(true);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                mainForm.setVisible(true);
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
    }
}
