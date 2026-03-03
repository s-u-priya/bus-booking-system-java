import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    int userId;

    public AdminDashboard(int userId) {

        this.userId = userId;

        setTitle("Admin Dashboard");
        setSize(400,400);
        setLayout(new GridLayout(8,1,10,10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addBus = new JButton("Add Bus");
        JButton addRoute = new JButton("Add Route");
        JButton addSchedule = new JButton("Add Schedule");
        JButton viewBookings = new JButton("View All Bookings");
        JButton viewUsers = new JButton("View All Users");
        JButton viewSchedules = new JButton("View Schedules");
        JButton logout = new JButton("Logout");

        add(addBus);
        add(addRoute);
        add(addSchedule);
        add(viewBookings);
        add(viewUsers);
        add(viewSchedules);
        add(logout);

        addBus.addActionListener(e -> new AddBusFrame());
        addRoute.addActionListener(e -> new AddRouteFrame());
        addSchedule.addActionListener(e -> new AddScheduleFrame());
        viewBookings.addActionListener(e -> new ViewAllBookingsFrame());
        viewUsers.addActionListener(e -> new ViewAllUsersFrame());
        viewSchedules.addActionListener(e -> new ViewSchedulesFrame());
        logout.addActionListener(e -> {
            dispose();
            new MainFrame();
        });

        setVisible(true);
    }
}