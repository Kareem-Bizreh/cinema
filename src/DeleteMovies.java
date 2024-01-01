import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMovies extends JFrame {
    DeleteMovies(Cinema c){
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
        JButton deleteFilm = new JButton("Delete film");
        deleteFilm.setFocusable(false);
        String[] columnNames = {"Movie", "Duration" ,"Type"};
        // size = size tickets
        Object[][] data = new Object[c.getMovies().size()][3];
        for(int i=0;i<c.getMovies().size();i++)
        {
            data[i][0]=c.getMovies().get(i).name;
            data[i][1]=c.getMovies().get(i).duration;
            data[i][2]=c.getMovies().get(i).type;
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
        deleteFilm.setBounds(530,270,150,50);
        Back.setBounds(20,270,100,50);
        Exit.setBounds(580,340,100,50);
        add(deleteFilm);
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
        deleteFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()==-1)
                {
                    JOptionPane.showMessageDialog(null,
                            "choose the film",
                            "",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                c.removeMovie(table.getSelectedRow());
                JOptionPane.showConfirmDialog(null,
                        "Your deletion has been completed successfully",
                        "",
                        JOptionPane.PLAIN_MESSAGE);
                // size = size tickets
                Object[][] data = new Object[c.getMovies().size()][3];
                for(int i=0;i<c.getMovies().size();i++)
                {
                    data[i][0]=c.getMovies().get(i).name;
                    data[i][1]=c.getMovies().get(i).duration;
                    data[i][2]=c.getMovies().get(i).type;
                }
                model.setDataVector(data,columnNames);
            }
        });
    }
}
