import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTickets extends JFrame {
    MyTickets(){
        setIconImage(new ImageIcon("test.PNG").getImage());
        setResizable(false);
        setSize(700,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JButton Calncel = new JButton("cancel reservation");
        Calncel.setFocusable(false);
        String[] columnNames = {"Hall", "Movie", "location" , "Start time" , "Duration"};
        // size = size tickets
        Object[][] data={
                {"1 ","2","2","3","5"},
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        //table.setPreferredSize(new Dimension(400,400));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 680, 250);
        add(scrollPane);
        Calncel.setBounds(530,270,150,50);
        Back.setBounds(20,270,100,50);
        Exit.setBounds(580,340,100,50);
        add(Calncel);
        add(Back);
        add(Exit);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserInfo();
            }
        });
        Calncel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Logic
            }
        });
    }
}
