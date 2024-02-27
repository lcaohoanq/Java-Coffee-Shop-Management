package views;

import controllers.IngredientFrameController;
import enums.IngredientMenu;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;

import javax.swing.*;
import java.awt.*;

public class IngredientFrame extends MyFrame {
    public IngredientFrame() {
        super();
        includeScrollPane = true;
    }
    @Override
    protected void initTitleZone(){
        super.initTitleZone();
        jLabel.setText("Ingredient Management");
    }
    @Override
    protected void initButtonZone() {
        jPanel_RightPanel.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_RightPanel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_Button.setLayout(new GridLayout(5, 1, 0, 5));
        for (IngredientMenu button : IngredientMenu.values()) {
            btn = new JButton(button.getValue());
            btn.setBackground(ColorHandler.TEXT_COLOR);
            btn.setForeground(ColorHandler.PRIMARY_COLOR);
            btn.setFont(FontHandler.BUTTON_FONT);
            btn.addActionListener(new IngredientFrameController(this));
            btn.setActionCommand(Integer.toString(button.getKey()));
            jPanel_Button.add(btn);
        }
        jPanel_RightPanel.setBorder(BorderHandler.BORDER);
        jPanel_RightPanel.add(jPanel_JLabel, BorderLayout.NORTH);
        jPanel_RightPanel.add(jPanel_Button, BorderLayout.CENTER);
    }
    @Override
    protected void initViewerZone() {
        super.initViewerZone();
    }
    @Override
    protected void initContainer() {
        super.initContainer();
        panel.add(jPanel_RightPanel, BorderLayout.CENTER);
        panel.add(jPanel_LeftPanel_TextArea, BorderLayout.WEST);
    }
    @Override
    protected void initApp(){
        super.initApp();
        initViewerZone();
    }

    public static void main(String[] args) {
        IngredientFrame ingredientFrame = new IngredientFrame();
        ingredientFrame.setVisible(true);
    }
}
