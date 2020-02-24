package Login;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowProducts {

    public static void main(String[] args) {
        ProductsShow ps = new ProductsShow();
    }
}

class ProductsShow extends JFrame {

    JTable table;
    Connection con = null;
    String query = "select * from products";
    JScrollPane sp;

    // Column Names 
    String[] columnNames = {"ID", "Name", "Price", "Quantity", "Details", "Date"};

    String id = "";
    String name = "";
    String price = "";
    String quantity = "";
    String details = "";
    String date = "";

    ;

    public ProductsShow() {

        table = new JTable(0, 6);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        sp = new JScrollPane(table);
        //add(table);
        add(sp);
        model.setColumnIdentifiers(columnNames);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sarthak", "root", "Papaloveus@98");
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            //int i = 0;
            while (rs.next()) {
                id = rs.getString("id");
                name = rs.getString("name");
                price = rs.getString("price");
                quantity = rs.getString("quantity");
                details = rs.getString("details");
                date = rs.getString("mfgDate");

                model.addRow(new Object[]{id, name, price, quantity, details, date});

            }

            st.close();
            con.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
