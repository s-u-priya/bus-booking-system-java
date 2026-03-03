import javax.swing.*;
import java.sql.*;

public class ViewAllBookingsFrame extends JFrame {

    JTextArea area;

    public ViewAllBookingsFrame() {

        setTitle("All Bookings");
        setSize(400,400);
        setLocationRelativeTo(null);

        area = new JTextArea();
        add(new JScrollPane(area));

        loadBookings();

        setVisible(true);
    }

    private void loadBookings() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM bookings");

            while(rs.next()) {
                area.append("Booking ID: " + rs.getInt(1) +
                        " | User ID: " + rs.getInt(2) +
                        " | Seat: " + rs.getInt(4) + "\n");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}