import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyTickets extends JFrame {
    MyTickets(Cinema c , Customer customer){
        setIconImage(new ImageIcon("test.png").getImage());
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
        String[] columnNames = {"Hall", "Movie", "position" , "Start time" , "Price"};

        ArrayList<Ticket>tickets = customer.user_tickets;

        String[][] data=new String[tickets.size()][5];
        for(int i=0;i<tickets.size();i++)
        {
            data[i][0]=tickets.get(i).hall_name;
            data[i][1]=tickets.get(i).movie_name;
            data[i][2]=tickets.get(i).position.getKey() + "," + tickets.get(i).position.getValue();
            data[i][3]= String.valueOf(tickets.get(i).time.hour);
            data[i][4]= String.valueOf(tickets.get(i).ticket_price);
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        //table.setPreferredSize(new Dimension(400,400));
        table.getTableHeader().setReorderingAllowed(false);
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
                new UserInfo(c,customer);
            }
        });
        Calncel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row==-1){
                    JOptionPane.showMessageDialog(null,
                            "choose the ticket",
                            "",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                c.unbookTicket(customer,tickets.get(row));
                dispose();
                new MyTickets(c,customer);
            }
        });
    }
}
