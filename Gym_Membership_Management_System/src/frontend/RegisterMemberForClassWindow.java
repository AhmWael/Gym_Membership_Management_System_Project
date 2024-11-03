package frontend;

import backend.TrainerRole;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class RegisterMemberForClassWindow extends JFrame {
    private JPanel RegisterMemberForClassPanel;
    private JTextField memIDTF;
    private JTextField classIDTF;
    private JButton registerButton;
    private JButton exitButton;
    private JButton backButton;
    private JTextField dateTF;
    private final TrainerRoleWindow mainForm;
    private JDatePickerImpl datePicker;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RegisterMemberForClassWindow(TrainerRoleWindow mainForm, TrainerRole trainer) {
        this.mainForm = mainForm;
        setContentPane(RegisterMemberForClassPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Register Member");
        setVisible(true);
        dateTF.setText(dateFormat.format(java.sql.Date.valueOf(LocalDate.now())));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setVisible(false);

        dateTF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                datePicker.setVisible(true);
                int result = JOptionPane.showConfirmDialog(RegisterMemberForClassWindow.this, datePicker,
                        "Select Date", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                    if (selectedDate != null) {
                        dateTF.setText(dateFormat.format(selectedDate));
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memID = memIDTF.getText();
                String classID = classIDTF.getText();
                Date selectedDate = (Date) datePicker.getModel().getValue();

                if(memID.isEmpty() || classID.isEmpty())
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                else if (trainer.getListOfMembers().stream().noneMatch(member -> member.getSearchKey().equals(memID))) {
                    JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " does not exist!");
                } else if (trainer.getListOfClasses().stream().noneMatch(Class -> Class.getSearchKey().equals(classID))) {
                    JOptionPane.showMessageDialog(null, "The class with ID: " + classID + " does not exist!");
                } else {
                    LocalDate registrationDate = LocalDate.parse(dateTF.getText());

                    if(trainer.registerMemberForClass(memID, classID, registrationDate))
                    {
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "The member with ID: " + memID + " registered successfully for class with ID: " + classID);
                        dispose();
                        mainForm.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Registration failed! No available seats");
                        classIDTF.setText("");
                        memIDTF.setText("");
                        dateTF.setText(dateFormat.format(java.sql.Date.valueOf(LocalDate.now())));
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                mainForm.setVisible(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Unsaved changes will be lost! Do you wish to continue?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        getRootPane().setDefaultButton(registerButton);

    }
    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value instanceof GregorianCalendar) {
                Date date = ((GregorianCalendar) value).getTime(); // Convert to Date
                return dateFormatter.format(date);
            }
            return "";
        }
    }
}
