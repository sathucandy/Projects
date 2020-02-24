package Login;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InvoicePrint {

    public static void main(String[] args) {
        Print print = new Print(null, null);
    }

}

class Print extends JFrame {

    JTextField t1, t2, t3, t4, t5;
    JLabel l1, l2, l3, l4, l5;
    JButton b1, b2;
    JTable table;
    JScrollPane jsp;
    String[] columnNames = {"Name", "Price"};
    String query = "insert into customers values (?, ?, ?, ?);";
    String name = "";
    String price = "";
    String quantity = "";
    String details = "";
    Connection con = null;

    public Print(String name[], int price[]) {

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);

        l1 = new JLabel("customer name");
        l2 = new JLabel("customer email");
        l3 = new JLabel("customer contact");
        l4 = new JLabel("customer address");

        table = new JTable(0, 2);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
        jsp = new JScrollPane(table);

        for (int i = 0; i < 10; i++) {
            model.addRow(new Object[]{name[i], price[i]});
        }

        b1 = new JButton("Print");

        add(t1);
        add(l1);
        add(t2);
        add(l2);
        add(t3);
        add(l3);
        add(t4);
        add(l4);
        add(jsp);
        add(b1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, t1.getText());
                    st.setString(2, t2.getText());
                    st.setString(3, t3.getText());
                    st.setString(4, t4.getText());

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
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
