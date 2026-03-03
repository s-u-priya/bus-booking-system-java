import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BookSeatFrame extends JFrame {

    JTextField nameField, scheduleField, seatField;
    int userId;

    public BookSeatFrame(int userId) {

        this.userId = userId;

        setTitle("Book Seat");
        setSize(400,300);
        setLayout(new GridLayout(5,2,10,10));
        setLocationRelativeTo(null);

        nameField = new JTextField();
        scheduleField = new JTextField();
        seatField = new JTextField();

        JButton bookBtn = new JButton("Book");

        add(new JLabel("Passenger Name:"));
        add(nameField);
        add(new JLabel("Schedule ID:"));
        add(scheduleField);
        add(new JLabel("Seat Number:"));
        add(seatField);
        add(bookBtn);

        bookBtn.addActionListener(e -> bookSeat());

        setVisible(true);
    }

    private void bookSeat() {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bookings(user_id,schedule_id,seat_number,passenger_name) VALUES(?,?,?,?)"
            );

            ps.setInt(1, userId);
            ps.setInt(2, Integer.parseInt(scheduleField.getText()));
            ps.setInt(3, Integer.parseInt(seatField.getText()));
            ps.setString(4, nameField.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Seat Booked Successfully");

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,"Seat already booked!");
        }
    }
}