import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DigitalClock extends JFrame {
    private JLabel clockLabel;
    private JTextField alarmField;
    private JButton setAlarmButton;
    private String alarmTime = "";

    public DigitalClock() {
        setTitle("Digital Clock with Alarm");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Clock Label
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 32));
        add(clockLabel);

        // Alarm input
        add(new JLabel("Set Alarm (HH:mm):"));
        alarmField = new JTextField(10);
        add(alarmField);

        // Button
        setAlarmButton = new JButton("Set Alarm");
        add(setAlarmButton);

        setAlarmButton.addActionListener(e -> {
            alarmTime = alarmField.getText();
            JOptionPane.showMessageDialog(this, "Alarm set for " + alarmTime);
        });

        // Start clock
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateClock();
            }
        }, 0, 1000);
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        clockLabel.setText(currentTime);

        // Check alarm
        if (alarmTime.equals(currentTime.substring(0, 5))) {
            JOptionPane.showMessageDialog(this, "⏰ Alarm! It's " + alarmTime);
            alarmTime = ""; // reset alarm
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DigitalClock().setVisible(true);
        });
    }
}
