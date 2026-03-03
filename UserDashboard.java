import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    int userId;

    public UserDashboard(int userId) {

        this.userId = userId;

        setTitle("User Dashboard");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(5,1,10,10));

        JButton viewSchedules = new JButton("View Schedules");
        JButton viewSeats = new JButton("View Seats");
        JButton bookSeat = new JButton("Book Seat");
        JButton cancelBooking = new JButton("Cancel Booking");
        JButton logout = new JButton("Logout");

        add(viewSchedules);
        add(viewSeats);
        add(bookSeat);
        add(cancelBooking);
        add(logout);

        viewSchedules.addActionListener(e -> new ViewSchedulesFrame());
        viewSeats.addActionListener(e -> new ViewSeatsFrame());
        bookSeat.addActionListener(e -> new BookSeatFrame(userId));
        cancelBooking.addActionListener(e -> new CancelBookingFrame());
        logout.addActionListener(e -> {
            dispose();
            new MainFrame();
        });

        setVisible(true);
    }
}