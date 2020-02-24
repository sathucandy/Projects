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

public class UpdateProduct {

    public static void main(String[] args) {
        ProductUpdate pu = new ProductUpdate();
    }

}

class ProductUpdate extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JButton b;
    Connection con = null;
    String query = "update products set price=?, quantity=?, details=? where name=?";

    public ProductUpdate() {

        t1 = new JTextField(20);
        l1 = new JLabel("Enter  name");

        t2 = new JTextField(20);
        l2 = new JLabel("Enter price");

        t3 = new JTextField(20);
        l3 = new JLabel("Enter quantity");

        t4 = new JTextField(20);
        l4 = new JLabel("Enter your details");

        b = new JButton("Submit");

        add(t1);
        add(l1);
        add(t2);
        add(l2);
        add(t3);
        add(l3);
        add(t4);
        add(l4);

        add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, t2.getText());
                    st.setString(2, t3.getText());
                    st.setString(3, t4.getText());
                    st.setString(4, t1.getText());

                    st.executeUpdate();
                    st.close();
                    con.close();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
