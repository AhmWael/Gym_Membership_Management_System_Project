package frontend;
import constants.LoginCredentials;
import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TrainerLogin extends JFrame implements LoginCredentials {
    private JPanel TrainerLoginPanel;
    private JTextField usernameTF;
    private JPasswordField passTF;
    private JButton loginButton;
    private JButton cancelButton;

    public TrainerLogin() {
        setContentPane(TrainerLoginPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Trainer Login");
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameTF.getText().isEmpty() || passTF.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                } else if(usernameTF.getText().equals(TRAINER_USERNAME) && Arrays.equals(passTF.getPassword(), TRAINER_PASSWORD.toCharArray())) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                    setVisible(false);
                    dispose();
                    new TrainerRoleWindow(new TrainerRole());
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                    usernameTF.setText("");
                    passTF.setText("");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new MainWindow();
            }
        });
    }
}
