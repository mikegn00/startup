import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class userChild extends JDialog {
    readJsonFile file;
//    namePanel name;
    rarityPanel rarity;
    userInterface parent;
    String[] textName;
    namingPanel namePane;
    YugiohCardDetails detail;
    public userChild(userInterface parent){
        setTitle("Add card");
//        name = new namePanel(parent.getData());
        this.parent = parent;
        initial();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        namePane = new namingPanel(parent);
        rarity = new rarityPanel(parent, namePane.getjList());
        namePane.updateTable(rarity.getModel());
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namePane.getjList().getSelectedValue();
                String set_code = rarity.getModel().getValueAt(rarity.getTable().getSelectedRow(), 0).toString()+"_"+rarity.getModel().getValueAt(rarity.getTable().getSelectedRow(), 1);
                detail = new YugiohCardDetails(name, " ", Arrays.asList(set_code));
                parent.updateTablePanel(detail);
            }
        });
        panel.add(namePane.getPanel(), BorderLayout.NORTH);
        panel.add(rarity.getPanel(), BorderLayout.SOUTH);
//        setLayout(new GridLayout(3,1));
//        name.setPanel(rarity.getModel());
        add(searchPanel(), BorderLayout.NORTH);
//        add(name.getPanel(), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

//        add(rarity.getPanel(), BorderLayout.CENTER);
        setVisible(true);
        setSize(500,500);

    }
    JPanel searchPanel(){
        JPanel panel = new JPanel();
        JTextField bar = new JTextField(15);
        bar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = bar.getText();
                    filterModel((DefaultListModel<String>)namePane.getjList().getModel(), text);
                }
            }
        });
        JButton button = new JButton("Search");

        panel.add(bar, BorderLayout.WEST);
        panel.add(button, BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = bar.getText();
                filterModel((DefaultListModel<String>)namePane.getjList().getModel(), text);
            }
        });
        return panel;
    }
    public void filterModel(DefaultListModel<String> model, String filter) {
        model.removeAllElements();
        for (String s : textName) {
            if (!s.toLowerCase().contains(filter)){
                model.removeElement(s);
            }
            else{
                if (!model.contains(s)){
                    model.addElement(s);

                }
            }
//            if (!s.toLowerCase().startsWith(filter)) {
//                if (model.contains(s)) {
//                    model.removeElement(s);
//                }
//            } else {
//                if (!model.contains(s)) {
//                    model.addElement(s);
//                }
//            }
        }
    }
    void initial(){
        List<YugiohCardDetails> list = parent.getData().getList();
        textName = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            textName[i] = list.get(i).getName();
        }
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



class namePanel {
    JPanel panel;
    nameModel name;
    JTable table;
    JScrollPane scrollPane;
    TableRowSorter<nameModel> rowSorter;
    public namePanel(readJsonFile file){
        panel = new JPanel();
        name = new nameModel(file);
        table = new JTable(name);
        scrollPane = new JScrollPane(table);
        rowSorter = new TableRowSorter<nameModel>((nameModel) table.getModel());

        table.changeSelection(0,0, false, false);
//        scrollPane.setSize(250, 250);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        table.setRowSorter(rowSorter);
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

    public TableRowSorter<nameModel> getRowSorter() {
        return rowSorter;
    }

    JPanel getPanel(){
        return panel;
    }
    JTable getTable(){
        return table;
    }
}

