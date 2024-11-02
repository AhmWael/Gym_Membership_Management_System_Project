package frontend;

import javax.swing.*;

public class AdminRole extends JFrame {
    private JPanel AdminRolePanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public AdminRole() {
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
            System.out.println("Add Member");
//            new AddMember();
        });

        button3.addActionListener(e -> {
            System.out.println("View Members");
//            new ViewMembers();
        });

        button4.addActionListener(e -> {
            System.out.println("View Trainers");
//            new ViewTrainers();
        });
    }
}
