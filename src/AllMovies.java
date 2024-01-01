import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllMovies extends JFrame {
    AllMovies(Cinema c,Customer customer,int x){
        setIconImage(new ImageIcon("test.png").getImage());
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
        for (Movie m: c.getMovies())
        {
            //add movie
            MovieCard movieCard = new MovieCard(m.name,m.rate,m.type);
            for(Component component : movieCard.getComponents())
                if(component instanceof JButton)
                    ((JButton) component).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            new MovieInfo(c,customer,m,x);
                        }
                    });
            panel.add(movieCard);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        JLabel text=new JLabel("All Movies");
        //ArrayList
        JLabel most;
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
                    new UserInfo(c,customer);
                else
                    new Maneger(c);
            }
        });
    }
}