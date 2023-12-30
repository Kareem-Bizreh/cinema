import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieInfo extends JFrame {
    JPanel panel;
    MovieInfo(Cinema c,Customer customer,Movie movie,int x){
        setIconImage(new ImageIcon("cinema/test.png").getImage());
        setResizable(false);
        setSize(650,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        //add already comments on AL comment
        for(Pair<String,String> com: movie.comments)
        {
            addComment(c,customer,movie,com.getKey(), com.getValue(),0);
        }
        addComment(c,customer,movie,customer.name,null,0);

        JButton add = new JButton("Add Comment");
        add.setFocusable(false);
        JLabel text=new JLabel(customer.name);
        text.setFont(new Font("Arial", Font.BOLD, 40));
        text.setBounds(230,10,250,50);
        text.setForeground(Color.BLUE);

        JPanel Info = new JPanel(new GridLayout(4,1));
        JLabel dur = new JLabel("Duration : " + movie.duration);
        dur.setFont(new Font("MV Boli", Font.BOLD,20));
        JLabel type = new JLabel("type : " + movie.type);
        type.setFont(new Font("MV Boli", Font.BOLD,20));
        JLabel rate = new JLabel("full rate : " + movie.rate);
        rate.setFont(new Font("MV Boli", Font.BOLD,20));
        //hours
        JLabel hour = new JLabel("Busiest hour : " + "10:00");
        hour.setFont(new Font("MV Boli", Font.BOLD,20));
        Info.add(dur);
        Info.add(type);
        Info.add(rate);
        Info.add(hour);

        JButton reserve = new JButton("Reserve");
        reserve.setFocusable(false);

        JComboBox times = new JComboBox();
        //add items to times
        for (Pair<Date,Integer> p :movie.date_hall)
        {
            times.addItem("Hall : " + p.getValue() + "time : " +p.getKey().hour+":00 hour");
        }

        JSlider ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);

        JButton ratingButton = new JButton("rate");
        ratingButton.setFocusable(false);

        ratingSlider.setBounds(10,325,250,50);
        times.setBounds(10,250,250,50);
        add.setBounds(475,475,150,50);
        reserve.setBounds(10,475,100,50);
        scrollPane.setBounds(300,225,325,250);
        Info.setBounds(0,75,300,150);
        Back.setBounds(10,540,100,50);
        Exit.setBounds(525,540,100,50);
        ratingButton.setBounds(10,375,100,50);

        add(add);
        add(scrollPane);
        add(Back);
        add(Exit);
        add(times);
        add(Info);
        add(text);
        add(reserve);
        add(ratingSlider);
        add(ratingButton);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AllMovies(c, customer, x);
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment(c,customer,movie,customer.name,null,0);
            }
        });
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Chair(c , customer ,
                        movie,c.getPresentation(movie,
                        movie.date_hall.get(times.getSelectedIndex()).getKey(),
                        movie.date_hall.get(times.getSelectedIndex()).getValue()),x);
            }
        });
        ratingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addRate(customer,movie,ratingSlider.getValue());
                JOptionPane.showMessageDialog(null,
                        "rating complete",
                        "Accept",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private JTextArea previousCommentTextArea = null;
    private void addComment(Cinema c,Customer customer,Movie movie,String Name,String comment,int x) {
        if (previousCommentTextArea != null) {
            if(previousCommentTextArea.getText().isEmpty())
                return;
            previousCommentTextArea.setEditable(false);
            previousCommentTextArea.setForeground(Color.red);
            if(x!=0)
                c.addComment(customer,movie,previousCommentTextArea.getText());
        }
        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        commentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel nameLabel = new JLabel(Name);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.GRAY);

        JTextArea commentTextArea = new JTextArea();
        commentTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        commentTextArea.setRows(5);
        commentTextArea.setColumns(5);
        Font font = commentTextArea.getFont();
        font = font.deriveFont(font.getSize() + 25f);
        commentTextArea.setFont(font);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);

        commentPanel.add(nameLabel);
        if(comment!=null)
            commentTextArea.setText(comment);
        commentPanel.add(new JScrollPane(commentTextArea));

        panel.add(commentPanel);

        previousCommentTextArea = commentTextArea;
        revalidate();
        repaint();
    }
}
