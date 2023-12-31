import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaymentWays extends JFrame {
    PaymentWays(Cinema c , Customer customer,Movie movie ,Presentation presentation,ArrayList<Ticket>tickets ,int x) {
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JLabel text = new JLabel("Choose way of payment");
        text.setFont(new Font("MV Boli",Font.PLAIN,30));
        text.setForeground(Color.RED);
        text.setBounds(120,30,600,30);
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.add(new JButton(new ImageIcon("paypal.png")));
        panel.add(new JButton(new ImageIcon("mastercard.png")));
        panel.add(new JButton(new ImageIcon("visa.png")));
        panel.add(new JButton(new ImageIcon("worldpay.png")));
        panel.add(new JButton(new ImageIcon("bitcoin.png")));
        panel.add(new JButton(new ImageIcon("bank.png")));
        panel.add(new JButton(new ImageIcon("cash.png")));
        panel.add(new JButton(new ImageIcon("Syriatel.png")));
        panel.setBackground(Color.BLACK);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(Color.WHITE);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showConfirmDialog(null,
                                "Your reservation has been completed successfully",
                                "",
                                JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showConfirmDialog(null,
                                "price with discount " + c.priceWithDiscounts(tickets) +
                                "price without discount "+c.priceWithoutDiscounts(tickets),
                                "",
                                JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        new Chair(c,customer,movie,presentation,x);
                    }
                });
            }
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50,100,500,400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
        add(text);
    }
}
