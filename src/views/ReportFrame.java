package views;

import controllers.ReportFrameController;
import enums.ReportMenu;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;

import javax.swing.*;
import java.awt.*;

public class ReportFrame extends MyFrame{
    public ReportFrame() {
        super();
        includeScrollPane = true;
    }
    @Override
    protected void initTitleZone(){
        super.initTitleZone();
        jLabel.setText("Report Management");
    }
    @Override
    protected void initButtonZone() {
        jPanel_RightPanel.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_RightPanel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_Button.setLayout(new GridLayout(4, 1, 0, 5));
        for (ReportMenu button : ReportMenu.values()) {
            btn = new JButton(button.getValue());
            btn.setBackground(ColorHandler.TEXT_COLOR);
            btn.setForeground(ColorHandler.PRIMARY_COLOR);
            btn.setFont(FontHandler.BUTTON_FONT);
            btn.addActionListener(new ReportFrameController(this));
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
        ReportFrame reportFrame = new ReportFrame();
        reportFrame.setVisible(true);
    }
}
