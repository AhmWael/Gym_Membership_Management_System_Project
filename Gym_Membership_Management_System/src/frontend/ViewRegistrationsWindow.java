package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRegistrationsWindow extends JFrame{
    private JTable registrationsTable;
    private JPanel ViewRegistrationsPanel;
    private JButton backButton;
    private final TrainerRoleWindow mainForm;

    public ViewRegistrationsWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(ViewRegistrationsPanel);
        ViewRegistrationsPanel.setLayout(new BorderLayout());
        setSize(640, 480);
        setLocationRelativeTo(null);
        setTitle("View Registrations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Member ID", "Class ID", "Registrations Date"};
        String[][] data = new String[trainer.getListOfRegistrations().size()][3];
        for (int i = 0; i < trainer.getListOfRegistrations().size(); i++) {
            String dataLine = trainer.getListOfRegistrations().get(i).lineRepresentation();
            String[] dataSeparated = dataLine.split(",");
            data[i] = dataSeparated;
        }
        registrationsTable = new JTable(data, columnNames);
        ViewRegistrationsPanel.add(new JScrollPane(registrationsTable));
        ViewRegistrationsPanel.add(backButton, BorderLayout.SOUTH);
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
