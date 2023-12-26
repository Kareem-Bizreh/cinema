import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JFrame {
    UserInfo(){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
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
        JLabel text = new JLabel("Welcome ");
        JPanel panel = new JPanel(new GridLayout(3,2));
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
                new Log();
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
                //statment
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
            }
        });
        MyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyTickets();
                dispose();
            }
        });
    }
}
