package frontend;

import backend.AdminRole;

import javax.swing.*;

public class AdminRoleWindow extends JFrame {
    private JPanel AdminRolePanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public AdminRoleWindow(AdminLogin parent) {
        setVisible(true);
        setSize(320, 480);
        setTitle("Admin Role");
        setLocationRelativeTo(null);
        setContentPane(AdminRolePanel);

        button1.addActionListener(e -> {
            System.out.println("Add Trainer");
            new AddTrainer();
        });

        button2.addActionListener(e -> {
            new RemoveTrainer();
        });

        button3.addActionListener(e -> {
            System.out.println("View Trainers");
            new ViewTrainers();
        });

        button4.addActionListener(e -> {
            System.out.println("Logout");
            new AdminRole().logout();
            JOptionPane.showMessageDialog(null, "Logged out successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            parent.setVisible(true);
        });
    }
}
