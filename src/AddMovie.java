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
        mainPanel.setLayout(new GridLayout(5, 2));
        JLabel typeLabel = new JLabel("Movie Type:");
        typeLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        typeLabel.setForeground(Color.RED);
        TypeMovie[] movieTypes = TypeMovie.values();
        JComboBox<TypeMovie> typeComboBox = new JComboBox<>(movieTypes);
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);
        JLabel hallLabel = new JLabel("Movie Hall:");
        hallLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        hallLabel.setForeground(Color.RED);
        String[] halls = new String[20];
        for (int i = 0; i < 20; i++) {
            halls[i] = "Hall " + (i + 1);
        }
        JComboBox<String> hallComboBox = new JComboBox<>(halls);
        hallComboBox.setEnabled(false);
        mainPanel.add(hallLabel);
        mainPanel.add(hallComboBox);
        JLabel timeLabel = new JLabel("Start Time:");
        timeLabel.setFont(new Font("MV Boli",Font.PLAIN,20));
        timeLabel.setForeground(Color.RED);
        JComboBox<String> timeComboBox = new JComboBox<>();
        timeComboBox.setEnabled(false);
        mainPanel.add(timeLabel);
        mainPanel.add(timeComboBox);
        timeComboBox.setEnabled(false);
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
        mainPanel.setBounds(0,70,700,250);
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
                hallComboBox.setEnabled(true);
            }
        });
        hallComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hall = hallComboBox.getSelectedIndex();
                timeComboBox.addItem("100");
                timeComboBox.setEnabled(true);
                dur.setEnabled(false);
            }
        });
        timeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add duration
                dur.addItem(5);
                dur.setEnabled(true);
                nameTextField.setEnabled(false);
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
                if(c.addMovie(nameTextField.getText(),dur.getSelectedIndex()+1, (TypeMovie) typeComboBox.getSelectedItem())){
                    JOptionPane.showConfirmDialog(null,
                            "Your Add has been completed successfully",
                            "",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else
                    JOptionPane.showConfirmDialog(null,
                            "add failed",
                            "",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
