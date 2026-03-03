import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddRouteFrame extends JFrame {

    JTextField sourceField, destField;

    public AddRouteFrame() {

        setTitle("Add Route");
        setSize(300,200);
        setLayout(new GridLayout(3,2,10,10));
        setLocationRelativeTo(null);

        sourceField = new JTextField();
        destField = new JTextField();
        JButton addBtn = new JButton("Add");

        add(new JLabel("Source:"));
        add(sourceField);
        add(new JLabel("Destination:"));
        add(destField);
        add(addBtn);

        addBtn.addActionListener(e -> addRoute());

        setVisible(true);
    }

    private void addRoute() {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO routes(source,destination) VALUES(?,?)"
            );

            ps.setString(1, sourceField.getText());
            ps.setString(2, destField.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Route Added");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}