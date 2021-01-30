import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CollectionTablePanel {

    JPanel panel;
    JTable table;
    userInterface parent;
    List<YugiohCardDetails> list;
    TableModelCollection model;

    public CollectionTablePanel(userInterface parent){
        this.parent = parent;
        panel = new JPanel();
        list = new ArrayList<>();
        table = new JTable();
        model = new TableModelCollection(list);
    }

    void updateModel(YugiohCardDetails details){
        model.updateTable(details);
    }

    public JPanel getPanel() {
        return panel;
    }
}
class TableModelCollection extends AbstractTableModel {

    String[] columns = {"Set", "Name", "Rarity","Quantity"};
    List<YugiohCardDetails> temp;

    TableModelCollection(List<YugiohCardDetails> list){
        temp = list;
    }

    void updateTable(YugiohCardDetails details){
        temp.add(details);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return temp.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        YugiohCardDetails details = temp.get(rowIndex);
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
                return details.getCount();
        }
        return null;
    }
}
