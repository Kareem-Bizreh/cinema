import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JFrame {
    UserInfo(Cinema c, Customer customer){
        setIconImage(new ImageIcon("test.png").getImage());
        setResizable(false);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        setLayout(null);
        JLabel text = new JLabel("Welcome " +  customer.name);
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        text.setFont(new Font("MV Boli",Font.BOLD,25));
        text.setBounds(100,0,700,80);
        JButton ChangePassword = new JButton("Change password");
        JButton all = new JButton("All movies");
        JButton ChangeName = new JButton("Change Name");
        JButton MyTicket = new JButton("My Ticket");
        ChangePassword.setFocusable(false);
        all.setFocusable(false);
        ChangeName.setFocusable(false);
        MyTicket.setFocusable(false);
        panel.add(all);
        panel.add(MyTicket);
        panel.add(ChangeName);
        panel.add(ChangePassword);
        panel.add(Back);
        panel.add(Exit);
        panel.setBounds(0,80,700,290);
        add(text);
        add(panel);
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
                new Log(c);
            }
        });
        ChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewName = JOptionPane.showInputDialog("Enter new name");
                if(NewName==null)
                    return;
                if(NewName.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //statment (check if the name is valid)
                if(true){
                    JOptionPane.showMessageDialog(null,
                            "Information is invalid",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        ChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewPassword = JOptionPane.showInputDialog("Enter new password");
                if(NewPassword==null)
                    return;
                if(NewPassword.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //chang password
            }
        });
        MyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTickets(c,customer);
                dispose();
            }
        });
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AllMovies(c,customer,2);
            }
        });
    }
}
