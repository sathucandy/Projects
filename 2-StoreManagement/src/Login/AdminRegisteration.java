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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminRegisteration {

    public static void main(String[] args) {
        AdminRegisterationForm af = new AdminRegisterationForm();
    }

}

class AdminRegisterationForm extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JPasswordField p;
    JButton b;
    String query = "insert into admin values (?,?)";
    Connection con = null;

    public AdminRegisterationForm() {

        t1 = new JTextField(20);
        l1 = new JLabel("Enter your name");

        p = new JPasswordField(20);
        l2 = new JLabel("Enter your password");

        b = new JButton("Submit");

        add(l1);
        add(t1);
        add(l2);
        add(p);
        add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String v1 = new String(p.getPassword());

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, t1.getText());
                    st.setString(2, v1);
                    //  st.setCharacterStream(3, p.getPassword());

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
