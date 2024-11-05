package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMembersWindow extends JFrame {
    private JTable membersTable;
    private JPanel ViewMembersPanel;
    private JButton backButton;
    private final TrainerRoleWindow mainForm;

    public ViewMembersWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(ViewMembersPanel);
        ViewMembersPanel.setLayout(new BorderLayout());
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("View Members");
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Name", "Email", "Membership Type", "Phone Number", "Status"};
        String[][] data = new String[trainer.getListOfMembers().size()][6];
        for (int i = 0; i < trainer.getListOfMembers().size(); i++) {
            String dataLine = trainer.getListOfMembers().get(i).lineRepresentation();
            String[] dataSeparated = dataLine.split(",");
            data[i] = dataSeparated;
        }
        membersTable = new JTable(data, columnNames);
        ViewMembersPanel.add(new JScrollPane(membersTable));
        ViewMembersPanel.add(backButton, BorderLayout.SOUTH);
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                mainForm.setVisible(true);
            }
        });
    }
}
