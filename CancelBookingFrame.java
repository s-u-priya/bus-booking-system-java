import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CancelBookingFrame extends JFrame {

    JTextField bookingField;

    public CancelBookingFrame() {

        setTitle("Cancel Booking");
        setSize(300,200);
        setLayout(new GridLayout(3,2,10,10));
        setLocationRelativeTo(null);

        bookingField = new JTextField();
        JButton cancelBtn = new JButton("Cancel");

        add(new JLabel("Booking ID:"));
        add(bookingField);
        add(cancelBtn);

        cancelBtn.addActionListener(e -> cancelBooking());

        setVisible(true);
    }

    private void cancelBooking() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement select = con.prepareStatement(
                "SELECT schedule_id,seat_number FROM bookings WHERE booking_id=?"
            );

            select.setInt(1, Integer.parseInt(bookingField.getText()));

            ResultSet rs = select.executeQuery();

            if(rs.next()) {

                int scheduleId = rs.getInt(1);
                int seatNumber = rs.getInt(2);

                PreparedStatement delete = con.prepareStatement(
                        "DELETE FROM bookings WHERE booking_id=?"
                );
                delete.setInt(1, Integer.parseInt(bookingField.getText()));
                delete.executeUpdate();

                PreparedStatement update = con.prepareStatement(
                        "UPDATE seats SET is_booked=FALSE WHERE schedule_id=? AND seat_number=?"
                );
                update.setInt(1, scheduleId);
                update.setInt(2, seatNumber);
                update.executeUpdate();

                JOptionPane.showMessageDialog(this,"Booking Cancelled");

            } else {
                JOptionPane.showMessageDialog(this,"Invalid Booking ID");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}