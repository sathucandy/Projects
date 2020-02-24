package Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Employee {

    public static void main(String[] args) {
        Emp emp = new Emp();
    }
}

class Emp extends JFrame {

    JButton b1, b2, b3, b4, b5;

    public Emp() {

        b1 = new JButton("Add");
        b2 = new JButton("Show");
        b3 = new JButton("Update");
        b4 = new JButton("Delete");
        b5 = new JButton("Sell");

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ProductAdd();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ProductsShow();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ProductUpdate();
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ProductDelete();
            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new InvoiceSearch();
            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
