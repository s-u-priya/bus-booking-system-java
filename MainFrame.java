import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public MainFrame() {

        setTitle("Bus Booking System");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5,1,10,10));

        JLabel title = new JLabel("BUS BOOKING SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(title);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginBtn);
        add(registerBtn);

        // LOGIN ACTION
        loginBtn.addActionListener(e -> login());

        // REGISTER ACTION
        registerBtn.addActionListener(e -> register());

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT user_id, role FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                int userId = rs.getInt("user_id");
                String role = rs.getString("role");

                JOptionPane.showMessageDialog(this,"Login Successful");

                dispose(); // close login window

                if(role.equals("admin"))
                    new AdminDashboard(userId);
                else
                    new UserDashboard(userId);

            } else {
                JOptionPane.showMessageDialog(this,"Invalid Credentials");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void register() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)"
            );

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "user");

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Registration Successful");

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this,"Username already exists");
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}