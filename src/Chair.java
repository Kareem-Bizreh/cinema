import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chair extends JFrame {
    Chair(){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        setLayout(null);
        JPanel panel = new JPanel(new GridLayout(10,20,5,10));
        panel.setBackground(Color.BLACK);
        JCheckBox [][]chairs = new JCheckBox[10][20];
        for (int i=0;i<10;i++)
            for (int j=0;j<20;j++)
            {
                chairs[i][j] = new JCheckBox();
                panel.add(chairs[i][j]);
                chairs[i][j].setBackground(Color.BLACK);
            }
        //put the chair in red
        //for(int i=0--->n)
        JLabel chair = new JLabel("Choose the chairs you want to reserve");
        chair.setFont(new Font("MV Boli",Font.PLAIN,30));
        chair.setForeground(Color.RED);
        JButton reserve = new JButton("Reserve the chairs");
        reserve.setFocusable(false);
        panel.setBounds(90,100,400,300);
        chair.setBounds(10,30,600,30);
        reserve.setBounds(50,420,200,50);
        Back.setBounds(50,480,100,50);
        Exit.setBounds(440,420,100,50);
        add(panel);
        add(chair);
        add(reserve);
        add(Back);
        add(Exit);
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<10;i++)
                    for (int j=0;j<20;j++)
                    {
                        if(chairs[i][j].isSelected()) {
                            chairs[i][j].setEnabled(false);
                            chairs[i][j].setBackground(Color.RED);
                        }
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
                //movie info frame
            }
        });
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check how much he will pay
                dispose();
                new PaymentWays();
            }
        });
    }
}
