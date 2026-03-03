import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddBusFrame extends JFrame {

    JTextField numberField, nameField, seatsField;

    public AddBusFrame() {

        setTitle("Add Bus");
        setSize(350,250);
        setLayout(new GridLayout(4,2,10,10));
        setLocationRelativeTo(null);

        numberField = new JTextField();
        nameField = new JTextField();
        seatsField = new JTextField();

        JButton addBtn = new JButton("Add");

        add(new JLabel("Bus Number:"));
        add(numberField);
        add(new JLabel("Bus Name:"));
        add(nameField);
        add(new JLabel("Total Seats:"));
        add(seatsField);
        add(addBtn);

        addBtn.addActionListener(e -> addBus());

        setVisible(true);
    }

    private void addBus() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO buses(bus_number,bus_name,total_seats) VALUES(?,?,?)"
            );

            ps.setString(1, numberField.getText());
            ps.setString(2, nameField.getText());
            ps.setInt(3, Integer.parseInt(seatsField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Bus Added");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}