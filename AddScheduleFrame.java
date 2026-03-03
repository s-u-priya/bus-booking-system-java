import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddScheduleFrame extends JFrame {

    JTextField busField, routeField, dateField, timeField, fareField;

    public AddScheduleFrame() {

        setTitle("Add Schedule");
        setSize(400,300);
        setLayout(new GridLayout(6,2,10,10));
        setLocationRelativeTo(null);

        busField = new JTextField();
        routeField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        fareField = new JTextField();

        JButton addBtn = new JButton("Add");

        add(new JLabel("Bus ID:"));
        add(busField);
        add(new JLabel("Route ID:"));
        add(routeField);
        add(new JLabel("Date (YYYY-MM-DD):"));
        add(dateField);
        add(new JLabel("Time (HH:MM:SS):"));
        add(timeField);
        add(new JLabel("Fare:"));
        add(fareField);
        add(addBtn);

        addBtn.addActionListener(e -> addSchedule());

        setVisible(true);
    }

    private void addSchedule() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO schedules(bus_id,route_id,travel_date,departure_time,fare) VALUES(?,?,?,?,?)"
            );

            ps.setInt(1, Integer.parseInt(busField.getText()));
            ps.setInt(2, Integer.parseInt(routeField.getText()));
            ps.setString(3, dateField.getText());
            ps.setString(4, timeField.getText());
            ps.setDouble(5, Double.parseDouble(fareField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Schedule Added");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}