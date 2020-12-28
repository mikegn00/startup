import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class userChild extends JDialog {
    readJsonFile file;
    namePanel name;
    rarityPanel rarity;
    public userChild(readJsonFile file){
        setTitle("Add card");
        name = new namePanel(file);
        rarity = new rarityPanel(file, name);
        setLayout(new GridLayout(3,1));
        this.file = file;
        name.setPanel(rarity.getModel());
        add(searchPanel());
        add(name.getPanel());
        add(rarity.getPanel());
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




}

class rarityPanel {
    JPanel panel;
    JTable table;
    rarityModel model;
    JScrollPane scrollPane;

    rarityPanel(readJsonFile file, namePanel name) {
        panel = new JPanel();
        model = new rarityModel(file, name);
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setModel(model);
        model.fireTableDataChanged();
        panel.add(scrollPane);
    }


    public JPanel getPanel() {
        return panel;
    }

    public rarityModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }
}


class rarityModel extends AbstractTableModel{
    String[] column = {"Set", "Rarity", "Count"};
    JTable list;
    readJsonFile file;
    namePanel name;
    rarityModel(readJsonFile file, namePanel name){
        this.file = file;
        this.name = name;
        list = name.getTable();
    }

    @Override
    public int getRowCount() {
        return file.getList().get(list.getSelectedRow()).getRarity().size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<String> details = file.getList().get(list.getSelectedRow()).getRarity();
        switch (columnIndex){
            case 0:
                return details.get(rowIndex);
        }
        return null;
    }

    @Override
    public String getColumnName(int num) {
        return column[num];
    }
}

class namePanel {
    JPanel panel;
    nameModel name;
    JTable table;
    JScrollPane scrollPane;
    public namePanel(readJsonFile file){
        panel = new JPanel();
        name = new nameModel(file);
        table = new JTable();
        scrollPane = new JScrollPane(table);

        table.setModel(name);
        table.changeSelection(0,0, false, false);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        panel.add(scrollPane);
    }

    public void setPanel(rarityModel tab) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tab.fireTableDataChanged();
            }
        });
    }

    JPanel getPanel(){
        return panel;
    }
    JTable getTable(){
        return table;
    }
}

class nameModel extends AbstractTableModel{
    String[] column = {"Name"};
    List<YugiohCardDetails> list;
    nameModel(readJsonFile file){
        list = file.getList();
    }

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