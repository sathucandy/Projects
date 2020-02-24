package Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class User {

    public static void main(String[] args) {
        UserForm uf = new UserForm();
    }
}

class UserForm extends JFrame {

    JLabel l;
    JButton b;
    JComboBox<String> cb;
    String[] user = new String[]{"Admin", "Employee"};

    public UserForm() {

        l = new JLabel("Select a user");
        b = new JButton("Submit");
        cb = new JComboBox<String>(user);

        add(l);
        add(cb);
        add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String value = cb.getSelectedItem().toString();

                if (value.equals("Admin")) {
                    new AdminRegisterationForm();
                } else {
                    new EmployeeForm();
                }

            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
