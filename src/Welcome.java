import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame {
    JLabel text=new JLabel("Welcome to our cinema");
    JButton b1,b2,b3;
    ImageIcon m;
    Welcome(Cinema c){
        setSize(550,500);
        setResizable(false);
        setIconImage(new ImageIcon("test.png").getImage());
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel front = new JLabel(new ImageIcon("background.png"));
        front.setBounds(0,0,550,500);
        add(front);
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
                if(password==null)
                    return;
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(c.findCustomer("maneger",password)!=null){
                    new Maneger(c);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "Information is incorrect",
                            "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Log(c);
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