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

    private final TrainerRoleWindow mainForm;

    public AddMemberWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(AddMemberPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
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
                else if (trainer.getListOfMembers().contains(ID)) {
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
    }


}
