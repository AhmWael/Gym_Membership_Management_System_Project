/*
* By:
* Ahmed Wael Mohamed Gaber 8836
* Moatassem Khaled Mohamed 9183
* GitHub repository link: https://github.com/AhmWael/Gym_Membership_Management_System_Project
*/

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
            new AdminLogin(this);
        });

        TrainerButton.addActionListener(e -> {
            System.out.println("Trainer Role");
            setVisible(false);
            new TrainerLogin();
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            Font customFont = new Font("Arial", Font.PLAIN, 14);  // Customize as needed
//            UIManager.put("Label.font", customFont);
//            UIManager.put("Button.font", customFont);
//            UIManager.put("TextField.font", customFont);
//            UIManager.put("TextArea.font", customFont);
//            UIManager.put("Table.font", customFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainWindow win = new MainWindow();
    }
}
