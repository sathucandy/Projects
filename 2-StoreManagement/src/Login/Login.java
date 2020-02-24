package Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

    public static void main(String[] args) {
        LoginForm lf = new LoginForm();
    }
}

class LoginForm extends JFrame {

    JLabel l1, l2;
    JTextField t1, t2;
    JPasswordField p;
    JButton b;
    JComboBox<String> cb;

    Connection con = null;
    String[] user = new String[]{"Admin", "Employee"};
    String query = null;

    //String query = "select * from registeration where uemail=? and upass=?";
    public LoginForm() {

        cb = new JComboBox<String>(user);
        t1 = new JTextField(20);
        l1 = new JLabel("Enter Your Name");
        p = new JPasswordField(20);
        l2 = new JLabel("Enter Your Password");
        b = new JButton("Submit");

        add(l1);
        add(t1);
        add(l2);
        add(p);
        add(cb);
        add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String v1 = new String(p.getPassword());

                try {
                    String value = cb.getSelectedItem().toString();

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");

                    if (value.equals("Admin")) {
                        query = "select * from admin where name=? and pass=?";
                        PreparedStatement st = con.prepareStatement(query);
                        String name = t1.getText();
                        String pass = v1;

                        st.setString(1, name);
                        st.setString(2, pass);

                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            new UserForm();
                        } else {
                            JOptionPane.showMessageDialog(null, "Login Unsuccessful");
                        }

                        st.close();
                        con.close();
                    } else {
                        query = "select * from employee where name=? and pass=?";
                        PreparedStatement st = con.prepareStatement(query);
                        String name = t1.getText();
                        String pass = v1;

                        st.setString(1, name);
                        st.setString(2, pass);

                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            new Emp();
                        } else {
                            JOptionPane.showMessageDialog(null, "Login Unsuccessful");
                        }

                        st.close();
                        con.close();
                    }

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
