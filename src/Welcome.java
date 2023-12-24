import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame {
    JLabel text=new JLabel("Welcome to our cinema");
    JButton b1,b2,b3;
    ImageIcon m;
    Welcome(){
        setSize(550,500);
        setResizable(false);
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text.setFont(new Font("MV Boli",Font.PLAIN,20));
        text.setForeground(Color.red);
        text.setBounds(140,0,500,20);
        b1=new JButton("Maneger");
        b1.setFocusable(false);
        b2=new JButton("Costumer");
        b2.setFocusable(false);
        b3=new JButton("Exit");
        b3.setFocusable(false);
        b1.setBounds(20,300,100,50);
        b2.setBounds(400,300,100,50);
        b3.setBounds(400,370,100,50);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password=JOptionPane.showInputDialog("Enter the Password");
                dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Log();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
        add(text);
        add(b1);add(b2);add(b3);
        setLocationRelativeTo(null);
    }
}