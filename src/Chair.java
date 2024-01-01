import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Chair extends JFrame {
    Chair(Cinema c , Customer customer,Movie movie ,Presentation presentation ,int x){
        setIconImage(new ImageIcon("test.png").getImage());
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
        JCheckBox [][]chairs = new JCheckBox[20][10];
        for (int i=0;i<20;i++)
            for (int j=0;j<10;j++)
            {
                chairs[i][j] = new JCheckBox();
                panel.add(chairs[i][j]);
                chairs[i][j].setBackground(Color.BLACK);
            }
        //put the chair in red
        for(int i=0;i<20;i++)
            for (int j=0;j<10;j++)
            {
                if(presentation.p_tickets[i][j].sold)
                {
                    chairs[i][j].setEnabled(false);
                    chairs[i][j].setBackground(Color.RED);
                }
            }
        JLabel chair = new JLabel("Choose the chairs you want to reserve");
        chair.setFont(new Font("MV Boli",Font.PLAIN,30));
        chair.setForeground(Color.RED);
        JButton reserve = new JButton("Reserve the chairs");
        reserve.setFocusable(false);
        panel.setBounds(25,100,550,300);
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
            ArrayList<Ticket> tickets = new ArrayList<>();
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<20;i++)
                    for (int j=0;j<10;j++)
                    {
                        if(chairs[i][j].isSelected()) {
                            tickets.add(presentation.p_tickets[i][j]);
                        }
                    }
                Thread t=new Thread();
                c.bookTicket(customer,movie,presentation,tickets,t);
                while (t.isAlive())
                {
                    JOptionPane.showConfirmDialog(null,
                            "Your order is being processed",
                            "wait",
                            JOptionPane.PLAIN_MESSAGE);
                }
                dispose();
                new PaymentWays(c,customer,movie,presentation,tickets,x);
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
                new MovieInfo(c,customer,movie,x);
            }
        });
    }
}
