package frontend;

import javax.swing.*;
import java.util.Arrays;

public class AdminLogin extends JFrame implements constants.LoginCredentials {
    private JPanel AdminLoginPanel;
    private JTextField usernameTF;
    private JPasswordField passTF;
    private JButton loginButton;
    private JButton cancelButton;

    public AdminLogin(MainWindow parent) {
        setSize(640, 480);
        setTitle("Admin Login");
        setLocationRelativeTo(null);
        setContentPane(AdminLoginPanel);
        setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameTF.getText();
            if (username.equals(ADMIN_USERNAME) && Arrays.equals(passTF.getPassword(), ADMIN_PASSWORD.toCharArray())) {
                System.out.println("Login Successful");
                setVisible(false);
                passTF.setText("");
                new AdminRoleWindow(this);
            } else {
                System.out.println("Login Failed");
                JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                passTF.setText("");
            }
        });

        cancelButton.addActionListener(e -> {
            System.out.println("Cancel");
            dispose();
            parent.setVisible(true);
        });
        getRootPane().setDefaultButton(loginButton);
    }
}
