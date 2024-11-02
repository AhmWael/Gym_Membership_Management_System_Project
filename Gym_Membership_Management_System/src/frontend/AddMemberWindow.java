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

    //private final MainForm mainForm;

    public AddMemberWindow(TrainerRole trainer) {
        setContentPane(AddMemberPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idTF.getText().isEmpty() || nameTF.getText().isEmpty()
                        || emailTF.getText().isEmpty() || memTypeTF.getText().isEmpty()
                        || phoneTF.getText().isEmpty() || statusTF.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfMembers().contains(idTF.getText())) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + idTF.getText() + " already exists!");
                } else {
                    setVisible(false);
                    trainer.addMember(idTF.getText(), nameTF.getText()
                            , emailTF.getText(), memTypeTF.getText()
                            , phoneTF.getText(), statusTF.getText());
                    JOptionPane.showMessageDialog(null, "The member with ID: " + idTF.getText() + " added successfully");
                }
            }
        });
    }


}
