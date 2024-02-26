package viewer;

import constants.Message;
import enums.IngredientMenu;
import enums.OrderMenu;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;

import javax.swing.*;
import java.awt.*;

public class OrderFrame extends MyFrame{
    public OrderFrame() {
        super();
    }
    @Override
    protected void initTitleZone(){
        super.initTitleZone();
        jLabel.setText("Order Management");
    }
    @Override
    protected void initButtonZone() {
        jPanel_RightPanel.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_RightPanel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_Button.setLayout(new GridLayout(3, 1, 0, 5));
        for (OrderMenu button : OrderMenu.values()) {
            btn = new JButton(button.getValue());
            btn.setBackground(ColorHandler.TEXT_COLOR);
            btn.setForeground(ColorHandler.PRIMARY_COLOR);
            btn.setFont(FontHandler.BUTTON_FONT);
            btn.addActionListener(this);
            btn.setActionCommand(Integer.toString(button.getKey()));
            jPanel_Button.add(btn);
        }
        jPanel_RightPanel.setBorder(BorderHandler.BORDER);
        jPanel_RightPanel.add(jPanel_JLabel, BorderLayout.NORTH);
        jPanel_RightPanel.add(jPanel_Button, BorderLayout.CENTER);
    }

    @Override
    protected void initContainer() {
        super.initContainer();
        panel.add(jPanel_RightPanel, BorderLayout.EAST);
        panel.add(jPanel_LeftPanel_TextArea, BorderLayout.WEST);
    }
}
