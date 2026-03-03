import javax.swing.*;
import java.sql.*;

public class ViewAllUsersFrame extends JFrame {

    JTextArea area;

    public ViewAllUsersFrame() {

        setTitle("All Users");
        setSize(400,400);
        setLocationRelativeTo(null);

        area = new JTextArea();
        add(new JScrollPane(area));

        loadUsers();

        setVisible(true);
    }

    private void loadUsers() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while(rs.next()) {
                area.append(rs.getInt(1) + " | "
                        + rs.getString(2) + " | "
                        + rs.getString(4) + "\n");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}