package frontend;
import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberWindow extends JFrame{
    private JTextField idTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField memTypeTF;
    private JTextField phoneTF;
    private JTextField statusTF;
    private JButton addButton;
    private JPanel AddMemberPanel;
    private JButton exitButton;
    private JButton backButton;

    private final TrainerRoleWindow mainForm;

    public AddMemberWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(AddMemberPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Add Member");
        setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = idTF.getText();
                String name = nameTF.getText();
                String email = emailTF.getText();
                String memType = memTypeTF.getText();
                String phone = phoneTF.getText();
                String status = statusTF.getText();

                if(ID.isEmpty() || name.isEmpty() || email.isEmpty() || memType.isEmpty() || phone.isEmpty() || status.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfMembers().stream().anyMatch(member -> member.getSearchKey().equals(ID))) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + ID + " already exists!");
                } else {
                    setVisible(false);
                    trainer.addMember(ID, name, email, memType, phone, status);
                    JOptionPane.showMessageDialog(null, "The member with ID: " + ID + " added successfully");
                    dispose();
                    mainForm.setVisible(true);

                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idTF.getText().isEmpty() && nameTF.getText().isEmpty() && emailTF.getText().isEmpty() && memTypeTF.getText().isEmpty() && phoneTF.getText().isEmpty() && statusTF.getText().isEmpty()){
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
        getRootPane().setDefaultButton(addButton);
    }


}
