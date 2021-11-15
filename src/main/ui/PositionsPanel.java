package ui;

import javax.swing.*;
import java.awt.*;

public class PositionsPanel extends JPanel {

    //EFFECTS: Constructs a panel to contain employee functions
    public PositionsPanel() {
        JPanel positionPanel = new JPanel();
        positionPanel.setBackground(new Color(209, 207, 226));
        positionPanel.setBounds(400,0,800,700);
        positionPanel.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        addPositionButtons(positionPanel, c);
//        addTable(positionPanel, c);
    }

}
