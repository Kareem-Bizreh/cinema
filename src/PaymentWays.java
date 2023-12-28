import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentWays extends JFrame {
    PaymentWays() {
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
        panel.add(new JButton(new ImageIcon("cinema/paypal.png")));
        panel.add(new JButton(new ImageIcon("cinema/mastercard.png")));
        panel.add(new JButton(new ImageIcon("cinema/visa.png")));
        panel.add(new JButton(new ImageIcon("cinema/worldpay.png")));
        panel.add(new JButton(new ImageIcon("cinema/bitcoin.png")));
        panel.add(new JButton(new ImageIcon("cinema/bank.png")));
        panel.add(new JButton(new ImageIcon("cinema/cash.png")));
        panel.add(new JButton(new ImageIcon("cinema/Syriatel.png")));
        panel.setBackground(Color.BLACK);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(Color.WHITE);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showConfirmDialog(null,"Your reservation has been completed successfully",
                                "",
                                JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        new Chair();
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
