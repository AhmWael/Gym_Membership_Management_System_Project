package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import backend.StorableData;
import backend.TrainerDatabase;
import backend.Trainer;
import constants.FileNames;

public class ViewTrainers extends JFrame {
    private JPanel viewTrainersPanel;
    private JTable trainersTable;

    public ViewTrainers() {
        setVisible(true);
        setSize(640, 480);
        setTitle("View Trainers");
        setLocationRelativeTo(null);
        setContentPane(viewTrainersPanel);

        TrainerDatabase trainerDB = new TrainerDatabase(FileNames.TRAINER_FILENAME);
        String[] columnNames = {"ID", "Name", "Email", "Speciality", "Phone Number"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.addRow(columnNames);
        for (StorableData trainer : trainerDB.returnAllRecords().toArray(new StorableData[0])) {
            Object[] rowData = trainer.lineRepresentation().split(",");
            model.addRow(rowData);
        }
        trainersTable.setModel(model);
    }
}
