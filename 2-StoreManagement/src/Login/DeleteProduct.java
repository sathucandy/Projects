package Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteProduct {

    public static void main(String[] args) {
        ProductDelete pd = new ProductDelete();
    }
}

class ProductDelete extends JFrame {

    JLabel l;
    JTextField t;
    JButton b;
    Connection con = null;
    String query = "Delete from products where name = ?";

    public ProductDelete() {

        t = new JTextField(20);
        l = new JLabel("Enter the product name");
        b = new JButton("Submit");

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, t.getText());

                    st.executeUpdate();
                    st.close();
                    con.close();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        add(t);
        add(l);
        add(b);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
