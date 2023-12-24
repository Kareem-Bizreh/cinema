import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Log extends JFrame{

    Log(){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        Font font = usernameField.getFont();
        font = font.deriveFont(font.getSize() + 25f);
        usernameField.setFont(font);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(font);
        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        JButton logupButton = new JButton("Logup");
        logupButton.setFocusable(false);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(logupButton);
        panel.add(Back);
        panel.add(Exit);
        panel.setSize(500,500);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if(username.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Logic
            }
        });
        logupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogUp();
            }
        });
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
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
