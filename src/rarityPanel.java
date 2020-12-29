import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class rarityPanel {
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
        table.setRowSorter(null);
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
class rarityModel extends AbstractTableModel {
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
                String nameSet = details.get(rowIndex);
                for (int n = 0; n < nameSet.length(); n++){
                    if (nameSet.charAt(n) ==  '_'){
                        return nameSet.substring(0, n);
                    }

                }
            case 1:
                String nameRarity = details.get(rowIndex);
                for (int n = 0; n < nameRarity.length(); n++){
                    if (nameRarity.charAt(n) == '_'){
                        return nameRarity.substring(n+1);
                    }
                }
        }
        return null;
    }

    @Override
    public String getColumnName(int num) {
        return column[num];
    }
}
