import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewSchedulesFrame extends JFrame {

    JTextArea area;

    public ViewSchedulesFrame() {

        setTitle("Schedules");
        setSize(500,400);
        setLocationRelativeTo(null);

        area = new JTextArea();
        add(new JScrollPane(area));

        loadSchedules();

        setVisible(true);
    }

    private void loadSchedules() {

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT schedule_id,source,destination,travel_date,departure_time,fare " +
                "FROM schedules JOIN routes ON schedules.route_id=routes.route_id"
            );

            while(rs.next()) {

                area.append(
                    "ID: " + rs.getInt(1) +
                    " | " + rs.getString(2) +
                    " -> " + rs.getString(3) +
                    " | Date: " + rs.getDate(4) +
                    " | Time: " + rs.getTime(5) +
                    " | Fare: " + rs.getDouble(6) + "\n"
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}