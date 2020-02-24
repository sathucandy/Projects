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

public class EmployeeRegister {

    public static void main(String[] args) {
        EmployeeForm ef = new EmployeeForm();
    }
}

class EmployeeForm extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JPasswordField p;
    JButton b;
    String query = "insert into employee (name, pass, contact, details, email, address) values (?,?,?,?,?,?)";
    Connection con = null;

    public EmployeeForm() {

        t1 = new JTextField(20);
        l1 = new JLabel("Enter your name");

        p = new JPasswordField(20);
        l2 = new JLabel("Enter your password");

        t2 = new JTextField(20);
        l3 = new JLabel("Enter your contact");

        t3 = new JTextField(20);
        l4 = new JLabel("Enter your details");

        t4 = new JTextField(20);
        l5 = new JLabel("Enter your email");

        t5 = new JTextField(20);
        l6 = new JLabel("Enter your address");

        b = new JButton("Submit");

        add(t1);
        add(l1);
        add(p);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(t5);
        add(l6);
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
                    st.setString(3, t2.getText());
                    st.setString(4, t3.getText());
                    st.setString(5, t4.getText());
                    st.setString(6, t5.getText());
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
