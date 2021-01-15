import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class rarityPanel {
    JPanel panel;
    JTable table;
    rarityModel model;
    JScrollPane scrollPane;
    userInterface parent;

    rarityPanel(userInterface parent, JList<String> list) {
        this.parent = parent;
        panel = new JPanel();
        model = new rarityModel(parent, list);
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
    JList<String> list;
    userInterface parent;
    namePanel name;
    rarityModel(userInterface parent, JList<String> list){
        this.parent = parent;
        this.list = list;
    }

    @Override
    public int getRowCount() {
        int size = 0;
        for (YugiohCardDetails detail : parent.getData().getList()){
            if (detail.getName().equals(list.getSelectedValue())){
                size = parent.getData().getList().indexOf(detail);
            }
        }
        return parent.getData().getList().get(size).getSet().size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<String> d = new ArrayList<>();
        for (YugiohCardDetails detail : parent.getData().getList()){
            if (detail.getName().equals(list.getSelectedValue())){
                d = detail.getSet();
            }
        }
        List<String> details = parent.getData().getList().get(list.getSelectedIndex()).getSet();
        switch (columnIndex){
            case 0:
                String nameSet = d.get(rowIndex);
                for (int n = 0; n < nameSet.length(); n++){
                    if (nameSet.charAt(n) ==  '_'){
                        return nameSet.substring(0, n);
                    }

                }
            case 1:
                String nameRarity = d.get(rowIndex);
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
