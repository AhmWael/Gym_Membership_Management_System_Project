package frontend;
import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerRoleWindow extends JFrame {

    private JButton addMemberButton;
    private JButton viewMembersButton;
    private JButton addClassButton;
    private JButton viewClassesButton;
    private JButton registerMemberForClassButton;
    private JButton cancelRegistrationButton;
    private JButton viewRegistrationsButton;
    private JButton logoutButton;
    private JPanel trainerRolePanel;

    private TrainerRole trainer;

    public TrainerRoleWindow(TrainerRole trainer) {
        this.trainer = trainer;
        setContentPane(trainerRolePanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setVisible(true);

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddMemberWindow(TrainerRoleWindow.this, trainer);
            }
        });
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ViewMembersWindow(TrainerRoleWindow.this, trainer);
            }
        });
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddClassWindow(TrainerRoleWindow.this, trainer);
            }
        });
        viewClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new ViewClassesWindow();
            }
        });
        registerMemberForClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new RegisterMemberForClassWindow();
            }
        });
        cancelRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new CancelRegistrationWindow();
            }
        });
        viewRegistrationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new ViewRegistrationsWindow();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TrainerLogin();
            }
        });
    }

}
