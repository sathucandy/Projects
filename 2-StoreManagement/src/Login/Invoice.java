package Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Invoice {

    public static void main(String[] args) {
        InvoiceSearch is = new InvoiceSearch();
    }
}

class InvoiceSearch extends JFrame {

    JTextField t1, t2, t3, t4, t5;
    JLabel l1, l2, l3, l4, l5;
    JButton b1, b2, b3;
    JTable table;
    JScrollPane jsp;
    String[] columnNames = {"Name", "Price"};
    String query1 = "select name, price, quantity, details from products";
    String query2 = "select name, price from products";
    String name = "";
    String price = "";
    String quantity = "";
    String details = "";
    Connection con = null;

    public InvoiceSearch() {

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);

        l1 = new JLabel("Product name");
        l2 = new JLabel("Product price");
        l3 = new JLabel("Product Quantity");
        l4 = new JLabel("Product Description");

        table = new JTable(0, 2);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
        jsp = new JScrollPane(table);

        b1 = new JButton("Search");
        b2 = new JButton("ADD");
        b3 = new JButton("Invoice");

        add(t1);
        add(l1);
        add(b1);
        add(t2);
        add(l2);
        add(t3);
        add(l3);
        add(t4);
        add(l4);
        add(jsp);
        add(b2);
        add(b3);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query1);
                    ResultSet rs = st.executeQuery();

                    //int i = 0;
                    while (rs.next()) {
                        t2.setText(rs.getString("price"));
                        t3.setText(rs.getString("quantity"));
                        t4.setText(rs.getString("details"));

                    }

                    st.close();
                    con.close();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
                    PreparedStatement st = con.prepareStatement(query2);
                    ResultSet rs = st.executeQuery();

                    //int i = 0;
                    if (table.getRowCount() < 10) {
                        if (rs.next()) {
                            name = rs.getString("name");
                            price = rs.getString("price");
                            model.addRow(new Object[]{name, price});
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maximum list reached!!!");

                    }

                    st.close();
                    con.close();

                } catch (Exception ae) {
                    // TODO Auto-generated catch block
                    ae.printStackTrace();
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name[] = new String[10];
                int price[] = new int[10];
                int l = table.getRowCount();
                for (int i = 0; i < l; i++) {
                    name[i] = table.getValueAt(i, 0).toString();
                    price[i] = Integer.parseInt(table.getValueAt(i, 1).toString());

                }
                new Print(name, price);
            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
