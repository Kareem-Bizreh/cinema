import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maneger extends JFrame {
    Maneger(Cinema c){
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
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
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
                new Welcome(c);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DeleteMovies(c);
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
                //change
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddMovie(c);
            }
        });
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AllMovies(c,c.findCustomer("maneger","1234"),1);
            }
        });
        add(panel);
    }
}
