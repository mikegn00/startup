import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class userInterface extends JFrame {
    public readJsonFile read;
    userInterface parent;
    userInterface(){
        super("Yugi");
        this.read = new readJsonFile();
        this.parent = this;
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}
        add(buttonPanel(), BorderLayout.EAST);
        add(tablePanel(), BorderLayout.WEST);
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    readJsonFile getData(){
        return read;
    }

    JPanel buttonPanel(){

        JPanel panel = new JPanel(new GridLayout(5,1));
        JButton addItems = new JButton("+");
        addItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userChild child = new userChild(parent);

            }
        });
        JButton removeItems = new JButton("-");
        JButton updateData = new JButton("Update");
        JButton checkPrice = new JButton("Check Price");
        JButton saveCollection = new JButton("Save Collection");
        panel.add(addItems);
        panel.add(removeItems);
        panel.add(updateData);
        panel.add(checkPrice);
        panel.add(saveCollection);
        return panel;
    }
    JPanel tablePanel(){
        JPanel panel = new JPanel();
//        TableModel model = new TableModel();
//        JTable table = new JTable();
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setModel(model);
//        panel.add(scrollPane, BorderLayout.WEST);


        return panel;
    }

    class TableModel extends AbstractTableModel{
        String[] columns = {"Name", "Rarity", "Quantity"};
        List<YugiohCardDetails> list = read.getList();


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            YugiohCardDetails details = list.get(rowIndex);
            switch (columnIndex){
                case 0:
                    return details.getName();
                case 1:
                    return details.getRarity();
                case 2:
                    return 0;
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
    public static void main(String[] args) {
        new userInterface();
    }
}
