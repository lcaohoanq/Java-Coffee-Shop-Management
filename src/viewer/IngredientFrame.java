package viewer;

import constants.Message;
import enums.IngredientMenu;
import enums.MainMenu;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientFrame extends MyFrame {
    private MainFrame mainFrame;
    public IngredientFrame(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
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
            btn.addActionListener(this);
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
        panel.add(jPanel_RightPanel, BorderLayout.EAST);
        panel.add(jPanel_LeftPanel_TextArea, BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add ingredient")) {
            System.out.println("Add ingredient");
        }else if(e.getActionCommand().equals("Update ingredient information")) {
            System.out.println("Update ingredient information");
        }else if(e.getActionCommand().equals("Delete ingredient")) {
            System.out.println("Delete ingredient");
        }else if(e.getActionCommand().equals("Show all ingredients")) {
            System.out.println("Show all ingredients");
        }else if(e.getActionCommand().equals("Exit to main menu")) {
            mainFrame.setVisible(true);
            this.dispose();
        }
    }
}
