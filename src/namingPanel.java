import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class namingPanel {
    JList<String> jList;
    userInterface parent;
    List<YugiohCardDetails> list;
    String[] textNames;
    namingPanel(userInterface parent){
        this.parent = parent;
        list = parent.getData().getList();
        textNames = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            textNames[i] = list.get(i).getName();
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s: textNames){
            model.addElement(s);
        }
        jList = new JList<>(model);

    }

    public JList<String> getjList() {
        return jList;
    }


    JPanel getPanel(){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(jList);
        jList.setSelectedIndex(0);
        panel.add(scrollPane);
        return panel;
    }

    void updateTable(rarityModel table){
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table.fireTableDataChanged();
            }
        });
    }
}
