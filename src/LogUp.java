import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogUp extends JFrame {
    LogUp(Cinema c){
        setIconImage(new ImageIcon("test.png").getImage());
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
        JLabel Confirm = new JLabel("Confirm Password:");
        JPasswordField passwordConfirm = new JPasswordField();
        passwordField.setFont(font);
        passwordConfirm.setFont(font);
        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String confirmPassword = passwordConfirm.getText();
                if(!password.equals(confirmPassword)){
                    JOptionPane.showMessageDialog(null,"Password does not match confirmation",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(c.addCustomer(username,password))
                {
                    dispose();
                    new UserInfo(c,c.findCustomer(username,password));
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Information is invalid",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
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
                new Log(c);
            }
        });
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(Confirm);
        panel.add(passwordConfirm);
        panel.add(loginButton);
        panel.add(Back);
        panel.add(Exit);
        panel.setSize(500,500);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
