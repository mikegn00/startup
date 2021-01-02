import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class userInterface extends JFrame {
    public readJsonFile read;
    userInterface parent;
    List<YugiohCardDetails> currentList;
    TablePanel tablePanel;

    userInterface(){
        super("Yugi");
        this.read = new readJsonFile();
        this.parent = this;
        this.tablePanel = new TablePanel();
        this.currentList = new ArrayList<>();
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}
        add(buttonPanel(), BorderLayout.EAST);
        add(tablePanel.getPanel(), BorderLayout.WEST);
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



    public static void main(String[] args) {
        new userInterface();
    }
}

class TablePanel {
    JPanel panel;
    JTable table;
    TableModel model;
    List<YugiohCardDetails> list;
    public TablePanel(){
        panel = new JPanel();

        table = new JTable();
        list = new ArrayList<>();
        model = new TableModel(list);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setModel(model);
        panel.add(scrollPane, BorderLayout.WEST);
    }

    public JPanel getPanel() {
        return panel;
    }
}

class TableModel extends AbstractTableModel{
    String[] columns = {"Set", "Name", "Rarity", "Price", "Quantity"};
    List<YugiohCardDetails> currentList;
    TableModel(List<YugiohCardDetails> list){
        currentList = list;

    }

    @Override
    public int getRowCount() {
        return currentList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        YugiohCardDetails details = currentList.get(rowIndex);
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
