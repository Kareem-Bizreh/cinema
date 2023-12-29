import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieCard extends JPanel {
    //movie info
    MovieCard(String N, float rate , TypeMovie type){
        setSize(100,200);
        setBackground(Color.ORANGE);
        setLayout(new GridLayout(7,1));
        //setBorder(new LineBorder(Color.BLUE));
        JLabel Name= new JLabel("Name");
        JLabel Rate = new JLabel("Rate");
        JLabel t = new JLabel("Movie type");
        t.setFont(new Font("MV Boli",Font.PLAIN,16));
        Rate.setFont(new Font("MV Boli",Font.PLAIN,16));
        Name.setFont(new Font("MV Boli",Font.PLAIN,16));
        Name.setForeground(Color.red);
        Rate.setForeground(Color.red);
        t.setForeground(Color.red);
        add(Name);
        add(new JLabel(N));
        add(Rate);
        add(new JLabel(String.valueOf(rate)));
        add(t);
        add(new JLabel(String.valueOf(type)));
        JButton click = new JButton("Show movie");
        click.setFocusable(false);
        add(click);
    }
}