import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maneger extends JFrame {
    Maneger(){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JButton delete = new JButton("Delete movie");
        JButton add = new JButton("Add movie");
        JButton ChangePassword = new JButton("Change password");
        JButton all = new JButton("All movies");
        delete.setFocusable(false);
        add.setFocusable(false);
        ChangePassword.setFocusable(false);
        all.setFocusable(false);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(delete);
        panel.add(add);
        panel.add(all);
        panel.add(ChangePassword);
        panel.add(Back);
        panel.add(Exit);
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
                new Welcome();
            }
        });
        add(panel);
    }
}
