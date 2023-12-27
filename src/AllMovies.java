import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AllMovies extends JFrame {
    AllMovies(int x){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(600,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        setLayout(null);
        setBackground(Color.BLACK);
        GridLayout gridLayout = new GridLayout(0,3);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        JPanel panel = new JPanel(gridLayout);
        panel.setBackground(Color.BLACK);
        for (int i=0;i<50;i++)
        {
            //add movie
            panel.add(new MovieCard("test",3.6f,TypeMovie.SCIENCE_FICTION));
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        JLabel text=new JLabel("All Movies");
        text.setFont(new Font("Arial", Font.ITALIC, 40));
        text.setBounds(180,10,250,50);
        text.setForeground(Color.RED);
        text.setBackground(Color.BLACK);
        scrollPane.setBounds(40,75,500,300);
        add(text);
        add(scrollPane);
        Back.setBounds(40,400,100,50);
        Exit.setBounds(440,400,100,50);
        add(Back);
        add(Exit);
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
                if(x==2)
                    new UserInfo();
                else
                    new Maneger();
            }
        });
    }
}