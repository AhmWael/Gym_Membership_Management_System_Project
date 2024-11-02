package frontend;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JPanel MainWindowPanel;
    private JButton AdminButton;
    private JButton TrainerButton;

    public MainWindow() {
        setVisible(true);
        setSize(new Dimension(320, 240));
        setTitle("Gym System");
        setContentPane(MainWindowPanel);
        setLocationRelativeTo(null);


        AdminButton.addActionListener(e -> {
            System.out.println("Admin Role");
            setVisible(false);
            new AdminLogin();
        });

        TrainerButton.addActionListener(e -> {
            System.out.println("Trainer Role");
            setVisible(false);
            new TrainerLogin();
        });
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }
}
