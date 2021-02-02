import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
    userChild child;
    CollectionTablePanel collectionTablePanel;

    userInterface(){
        super("Yugi");
        this.read = new readJsonFile();
        this.parent = this;
        this.tablePanel = new TablePanel();
        this.currentList = new ArrayList<>();
        this.collectionTablePanel = new CollectionTablePanel(this);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}
        add(buttonPanel(), BorderLayout.CENTER);
        add(tablePanel.getPanel(), BorderLayout.WEST);
        add(collectionTablePanel.getPanel(), BorderLayout.EAST);
        setSize(1000, 1000);
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
                child = new userChild(parent);

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
    void updateTablePanel(YugiohCardDetails details){
        tablePanel.updateTable(details);
        child.dispose();
    }
    void updateCollectionPanel(YugiohCardDetails details){
        collectionTablePanel.updateModel(details);
        child.dispose();
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        list = new ArrayList<>();
        model = new TableModel(list);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setModel(model);
//        table.setSize(new Dimension(800, 900));
        scrollPane.setPreferredSize(new Dimension(800, 800));
        table.getColumnModel().getColumn(0).setPreferredWidth(70); //column Set
        table.getColumnModel().getColumn(1).setPreferredWidth(300); //column Name
        table.getColumnModel().getColumn(2).setPreferredWidth(250); //column Rarity
        table.getColumnModel().getColumn(3).setPreferredWidth(50); //column Rarity
        table.getColumnModel().getColumn(4).setPreferredWidth(50); //column Rarity

        panel.add(scrollPane, BorderLayout.WEST);
//        panel.setSize(800, 800);
    }

    public JPanel getPanel() {
        return panel;
    }
    void updateTable(YugiohCardDetails details){
        model.updateTable(details);
    }
}

class TableModel extends AbstractTableModel{
    String[] columns = {"Set", "Name", "Rarity", "Price", "Quantity"};
    List<YugiohCardDetails> currentList;
    TableModel(List<YugiohCardDetails> list){
        currentList = list;

    }
    void updateTable(YugiohCardDetails detail){
        currentList.add(detail);
        this.fireTableDataChanged();
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
                String set_name = details.getSet().get(0);
                for (int n = 0; n < set_name.length(); n++){
                    if (set_name.charAt(n) ==  '_'){
                        return set_name.substring(0, n);
                    }
                }
                return details.getSet();
            case 1:
                return details.getName();
            case 2:
                String set_rarity = details.getSet().get(0);
                for (int n = 0; n < set_rarity.length(); n++){
                    if (set_rarity.charAt(n) == '_'){
                        return set_rarity.substring(n+1);
                    }
                }
                return 0;
            case 3:
                return "£0.00";
            case 4:
                return details.getCount();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
