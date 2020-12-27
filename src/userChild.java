import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class userChild extends JDialog {
    readJsonFile file;
    JPanel name;
    public userChild(readJsonFile file){
        setTitle("Add card");
        this.file = file;
        add(searchPanel(), BorderLayout.NORTH);
        name = namePanel();

        add(name, BorderLayout.CENTER);
        setVisible(true);
        setSize(500,500);

    }
    JPanel searchPanel(){
        JPanel panel = new JPanel();
        JTextField bar = new JTextField(15);
        JButton button = new JButton("Search");
        panel.add(bar, BorderLayout.WEST);
        panel.add(button, BorderLayout.EAST);
        return panel;
    }

    JPanel namePanel() {
        JPanel panel = new JPanel();
        nameModel name = new nameModel();
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(table.getValueAt(table.getSelectedRow(),0));
            }
        });
        table.setModel(name);
        panel.add(scrollPane);
        return panel;
    }

    class nameModel extends AbstractTableModel{
        String[] column = {"Name"};
        List<YugiohCardDetails> list = file.getList();

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return column.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            YugiohCardDetails details = list.get(rowIndex);
            return details.getName();
        }

        @Override
        public String getColumnName(int num) {
            return column[num];
        }
    }


}
