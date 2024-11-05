package frontend;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class AdminLogin extends JFrame implements constants.LoginCredentials {
    private JPanel TrainerLoginPanel;
    private JTextField usernameTF;
    private JPasswordField passTF;
    private JButton loginButton;
    private JButton cancelButton;

    public AdminLogin(MainWindow parent) {
        setVisible(true);
        setSize(640, 480);
        setTitle("Admin Login");
        setLocationRelativeTo(null);
        setContentPane(TrainerLoginPanel);

        loginButton.addActionListener(e -> {
            String username = usernameTF.getText();
            String password = new String(passTF.getPassword());
            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
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
    }
}
