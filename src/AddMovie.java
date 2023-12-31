import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovie extends JFrame {
    AddMovie(Cinema c){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(720,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JLabel text=new JLabel("Add Movies");
        text.setFont(new Font("Arial", Font.ITALIC, 40));
        text.setForeground(Color.BLUE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        JLabel typeLabel = new JLabel("Movie Type:");
        typeLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        typeLabel.setForeground(Color.RED);
        TypeMovie[] movieTypes = TypeMovie.values();
        JComboBox<TypeMovie> typeComboBox = new JComboBox<>(movieTypes);
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);
        JLabel duration = new JLabel("Duration");
        duration.setFont(new Font("MV Boli",Font.PLAIN,20));
        duration.setForeground(Color.RED);
        String []time=new String[5];
        for(int i=1;i<=5;i++)
            time[i-1]=Integer.toString(i);
        JComboBox<Integer> dur = new JComboBox(time);
        dur.setEnabled(false);
        mainPanel.add(duration);
        mainPanel.add(dur);
        JLabel nameLabel = new JLabel("Movie Name:");
        nameLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        nameLabel.setForeground(Color.RED);
        JTextField nameTextField = new JTextField();
        Font font = nameTextField.getFont();
        font = font.deriveFont(font.getSize() + 25f);
        nameTextField.setFont(font);
        nameTextField.setEnabled(false);
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);
        text.setBounds(240,10,250,50);
        mainPanel.setBounds(0,170,700,150);
        add(mainPanel);
        JButton addButton = new JButton("Add Movie");
        addButton.setFocusable(false);
        addButton.setBounds(25,350,100,50);
        Back.setBounds(25,425,100,50);
        Exit.setBounds(600,350,100,50);
        add(text);
        add(addButton);
        add(Back);
        add(Exit);
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dur.setEnabled(true);
            }
        });
        dur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setEnabled(true);
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Maneger(c);
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dur.isEnabled() || nameTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Information is incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(c.addMovie(nameTextField.getText(),dur.getSelectedIndex()+1, (TypeMovie) typeComboBox.getSelectedItem())){
                    JOptionPane.showConfirmDialog(null,
                            "Your Add has been completed successfully",
                            "", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "add failes",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
