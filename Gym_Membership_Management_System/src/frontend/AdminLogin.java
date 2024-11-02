package frontend;

import javax.swing.*;

public class AdminLogin extends JFrame implements constants.LoginCredentials {
    private JPanel TrainerLoginPanel;
    private JTextField usernameTF;
    private JPasswordField passTF;
    private JButton loginButton;

    public AdminLogin() {
        setVisible(true);
        setSize(320, 240);
        setTitle("Admin Login");
        setLocationRelativeTo(null);
        setContentPane(TrainerLoginPanel);

        loginButton.addActionListener(e -> {
            String username = usernameTF.getText();
            String password = new String(passTF.getPassword());
            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                System.out.println("Login Successful");
                setVisible(false);
                new AdminRole();
            } else {
                System.out.println("Login Failed");
                JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}