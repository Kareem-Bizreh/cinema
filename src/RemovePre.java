import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemovePre extends JFrame {
    RemovePre(Cinema c){
        setIconImage(new ImageIcon("test.png").getImage());
        setResizable(false);
        setSize(700,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JButton Exit = new JButton("Exit");
        Exit.setFocusable(false);
        JButton Back = new JButton("Back");
        Back.setFocusable(false);
        JButton Calncel = new JButton("Delete presentation");
        Calncel.setFocusable(false);
        String[] columnNames = {"Hall", "Movie", "Start time" , "Duration"};

        int size=0;
        ArrayList<Movie> movies = c.getMovies();
        for(Movie m:movies){
            size+=m.date_hall.size();
        }

        String[][] data=new String[size][4];

        for(Movie m:movies)
        {
            for(int i=0;i<m.date_hall.size();i++){
                data[i][0]= String.valueOf(m.date_hall.get(i).getValue());
                data[i][1]=m.name;
                data[i][2]= String.valueOf(m.date_hall.get(i).getKey().hour);
                data[i][3]= String.valueOf(m.duration);
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        //table.setPreferredSize(new Dimension(400,400));
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 680, 250);
        add(scrollPane);
        Calncel.setBounds(530,270,150,50);
        Back.setBounds(20,270,100,50);
        Exit.setBounds(580,340,100,50);
        add(Calncel);
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
                new Maneger(c);
            }
        });
        Calncel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row==-1){
                    JOptionPane.showMessageDialog(null,
                            "choose the presentation",
                            "",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Movie movie = c.findMovie((String) table.getValueAt(row,1));
                //System.out.println(movie.date_hall.get(row).getKey()+" "+ movie.date_hall.get(row).getValue());
                c.removePresentation(movie,c.getPresentation(movie,
                        movie.date_hall.get(row).getKey(),
                        movie.date_hall.get(row).getValue()));
                dispose();
                new RemovePre(c);
            }
        });
    }
}
