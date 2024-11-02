package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.*;

public class ViewClassesWindow extends JFrame {
    private JTable classesTable;
    private JPanel ViewClassesPanel;
    private final TrainerRoleWindow mainForm;

    public ViewClassesWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(ViewClassesPanel);
        ViewClassesPanel.setLayout(new BorderLayout());
        setSize(640, 480);
        setLocationRelativeTo(null);
        setTitle("View Classes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Class ID", "Class Name", "Trainer ID", "Duration", "Max Participants"};
        String[][] data = new String[trainer.getListOfClasses().size()][5];
        for (int i = 0; i < trainer.getListOfClasses().size(); i++) {
            String dataLine = trainer.getListOfClasses().get(i).lineRepresentation();
            String[] dataSeparated = dataLine.split(",");
            data[i] = dataSeparated;
        }
        classesTable = new JTable(data, columnNames);
        ViewClassesPanel.add(new JScrollPane(classesTable));
        setVisible(true);
    }
}
