import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonChildPanel {
    JPanel panel;
    JButton butCart, butBag;
    rarityPanel rare;
    namingPanel name;
    userInterface parent;
    ButtonChildPanel(){
        panel = new JPanel();
        butCart = new JButton("Cart");
        butBag = new JButton("Inventory");
        panel.add(butCart, BorderLayout.WEST);
        panel.add(butBag, BorderLayout.EAST);
    }
    public void setButton(){
        butCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = name.getjList().getSelectedValue();
                String set_code = rare.getModel().getValueAt(rare.getTable().getSelectedRow(), 0).toString()+"_"+rare.getModel().getValueAt(rare.getTable().getSelectedRow(), 1);
                YugiohCardDetails detail = new YugiohCardDetails(nameString, " ", Arrays.asList(set_code));
                parent.updateTablePanel(detail);
            }
        });
        butBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = name.getjList().getSelectedValue();
                String set_code = rare.getModel().getValueAt(rare.getTable().getSelectedRow(), 0).toString()+"_"+rare.getModel().getValueAt(rare.getTable().getSelectedRow(), 1);
                YugiohCardDetails detail = new YugiohCardDetails(nameString, " ", Arrays.asList(set_code));
                parent.updateCollectionPanel(detail);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setName(namingPanel name) {
        this.name = name;
    }

    public void setRare(rarityPanel rare) {
        this.rare = rare;
    }

    public void setParent(userInterface parent) {
        this.parent = parent;
        setButton();
    }
}
