import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        panel.add(scrollPane);
        return panel;
    }
}
