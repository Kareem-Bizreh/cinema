import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieInfo extends JFrame {
    JPanel panel;
    MovieInfo(String name,int x){
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
        //for(int i=0---->n) addComment(name,comment);
        addComment(name,null);

        JButton add = new JButton("Add Comment");
        add.setFocusable(false);
        JLabel text=new JLabel(name);
        text.setFont(new Font("Arial", Font.BOLD, 40));
        text.setBounds(230,10,250,50);
        text.setForeground(Color.BLUE);

        JPanel Info = new JPanel(new GridLayout(4,1));
        JLabel dur = new JLabel("Duration : " + "3 hours");
        dur.setFont(new Font("MV Boli", Font.BOLD,20));
        JLabel type = new JLabel("type : " + TypeMovie.SCIENCE_FICTION);
        type.setFont(new Font("MV Boli", Font.BOLD,20));
        JLabel rate = new JLabel("full rate : " + Float.toString(5.5f));
        rate.setFont(new Font("MV Boli", Font.BOLD,20));
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
        //test
        for (int i=0;i<10;i++)
            times.addItem("Hall : 1  time : 10:00");

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
                new AllMovies(x);
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
                addComment(name,null);
            }
        });
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Chair(x);
            }
        });
        ratingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Logic
            }
        });
    }
    private JTextArea previousCommentTextArea = null;
    private void addComment(String Name,String comment) {
        if (previousCommentTextArea != null) {
            if(previousCommentTextArea.getText().isEmpty())
                return;
            previousCommentTextArea.setEditable(false);
            previousCommentTextArea.setForeground(Color.red);
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
