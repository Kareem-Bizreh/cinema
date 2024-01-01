import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class AddPres extends JFrame {
    AddPres(Cinema c){
        setIconImage(new ImageIcon("test.png").getImage());
        //setResizable(false);
        setSize(720,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JButton add = new JButton("Add");
        add.setFocusable(false);

        JLabel text=new JLabel("Add Presentation");
        text.setFont(new Font("Arial", Font.ITALIC, 40));
        text.setForeground(Color.BLUE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Movie Name:");
        nameLabel.setFont(new Font("MV Boli",Font.PLAIN,30));
        nameLabel.setForeground(Color.RED);
        String []AllNames=new String[c.getMovies().size()];

        for (int i=0;i<c.getMovies().size();i++)
            AllNames[i]=c.getMovies().get(i).name;

        JComboBox<String> Names = new JComboBox(AllNames);
        mainPanel.add(nameLabel);
        mainPanel.add(Names);

        JLabel hallLabel = new JLabel("Movie Hall:");
        hallLabel.setFont(new Font("MV Boli",Font.PLAIN,30));
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
        timeLabel.setFont(new Font("MV Boli",Font.PLAIN,30));
        timeLabel.setForeground(Color.RED);

        JComboBox<String> timeComboBox = new JComboBox<>();
        timeComboBox.setEnabled(false);
        mainPanel.add(timeLabel);
        mainPanel.add(timeComboBox);
        timeComboBox.setEnabled(false);

        text.setBounds(205,10,320,50);
        mainPanel.setBounds(0,70,700,250);
        add(mainPanel);
        JButton addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.setBounds(25,350,100,50);
        Back.setBounds(25,425,100,50);
        Exit.setBounds(590,350,100,50);
        add(text);
        add(addButton);
        add(Back);
        add(Exit);
        Names.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hallComboBox.setEnabled(true);
            }
        });
        hallComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeComboBox.removeAllItems();
                ArrayList<Integer> arrayList = c.allFreTime(c.findMovie((String) Names.getSelectedItem()),hallComboBox.getSelectedIndex()+1,
                        DayOfWeek.SUNDAY);
                for (int i : arrayList)
                    timeComboBox.addItem(Integer.toString(i));
                timeComboBox.setEnabled(true);
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
                if(timeComboBox.getSelectedIndex()==-1)
                {
                    JOptionPane.showMessageDialog(null,
                            "the information incomplete",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null,
                        "adding successfully",
                        "Accept",JOptionPane.INFORMATION_MESSAGE);
                Movie movie=c.findMovie((String) Names.getSelectedItem());
                c.addPresentation(movie
                        ,new Date(DayOfWeek.SUNDAY,Integer.parseInt((String) timeComboBox.getSelectedItem()))
                        ,hallComboBox.getSelectedIndex()+1);
                dispose();
                new AddPres(c);
            }
        });
    }
}
