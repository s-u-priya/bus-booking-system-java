import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewSeatsFrame extends JFrame {

    JTextField scheduleField;
    JTextArea area;

    public ViewSeatsFrame() {

        setTitle("View Seat Availability");
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        scheduleField = new JTextField(10);
        JButton loadBtn = new JButton("Load Seats");

        top.add(new JLabel("Schedule ID:"));
        top.add(scheduleField);
        top.add(loadBtn);

        area = new JTextArea();
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        loadBtn.addActionListener(e -> loadSeats());

        setVisible(true);
    }

    private void loadSeats() {

        area.setText("");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT seat_number,is_booked FROM seats WHERE schedule_id=?"
            );

            ps.setInt(1, Integer.parseInt(scheduleField.getText()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                area.append("Seat " + rs.getInt(1) +
                        " : " + (rs.getBoolean(2) ? "BOOKED" : "AVAILABLE") + "\n");
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,"Invalid Schedule ID");
        }
    }
}