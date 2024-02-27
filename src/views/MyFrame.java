package views;

//import controllers.Controller;
import constants.Message;
import styles.*;
import utils.ConsoleColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MyFrame extends JFrame implements ActionListener {

    // tạo 2 panel: bên phải là JTextArea, bên trái là JPanel chứa các button
    // bọc thêm 1 JLabel nữa cho bên phải nhìn cho đẹp :))))
    protected JPanel panel = new JPanel(new BorderLayout());
    protected JPanel jPanel_JLabel = new JPanel();
    protected JPanel jPanel_Button = new JPanel();
    protected JPanel jPanel_LeftPanel_TextArea = new JPanel();

    public static JTextArea jTextArea_Viewer = new JTextArea();
    public JScrollPane jScrollPane = new JScrollPane(jTextArea_Viewer);
    // set panel tổng là Flow Layout Hoặc BorderLayout (button: phải, viewer: trái)

    protected JLabel jLabel = new JLabel(); // Company Employee Management Program
    protected JPanel jPanel_RightPanel = new JPanel(new BorderLayout()); // Company Employee Management Program

    // private GridLayout layout = new GridLayout(3, 3, 5, 5);
    protected JButton btn;
    private static int btnValue = 0; //
//    private IngredientController controller; // Reference to the Controller
    protected boolean includeScrollPane = false; // Flag for adding scroll pane
    public MyFrame() {
//        this.controller = new Controller(); // Set the reference to the Controller
        setIconImage(ImageHandler.icon);
        this.setSize(SizeHandler.WINDOW_WIDTH, SizeHandler.WINDOW_HEIGHT);
        this.setTitle(Message.COFFEE_SHOP_MANAGEMENT_PROGRAM);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initApp();

    }

    protected void initApp() {
        initTitleZone();
        initButtonZone();
        initContainer();
        this.add(panel);
    }

    protected void initTitleZone() {
//        jLabel.setText(Message.COFFEE_SHOP_MANAGEMENT_PROGRAM);
        jLabel.setFont(FontHandler.TITLE_FONT);
        jLabel.setBorder(BorderHandler.BORDER_TITLE);
        jLabel.setBackground(ColorHandler.PRIMARY_COLOR);
        jLabel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_JLabel.setBackground(ColorHandler.PRIMARY_COLOR);
        // jPanel_JLabel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_JLabel.add(jLabel, BorderLayout.NORTH);
    }

    protected abstract void initButtonZone();

    protected void initViewerZone(){
        jTextArea_Viewer.setRows(30);
        jTextArea_Viewer.setColumns(40);
        jTextArea_Viewer.setLineWrap(true);
        jTextArea_Viewer.setEditable(false);
        jTextArea_Viewer.setFont(FontHandler.TEXT_FONT);
        jTextArea_Viewer.setBackground(ColorHandler.PRIMARY_COLOR);
        jTextArea_Viewer.setForeground(ColorHandler.TEXT_COLOR);
        jTextArea_Viewer.setBorder(null);

        jPanel_LeftPanel_TextArea.setLayout(new BorderLayout());

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jPanel_LeftPanel_TextArea.add(jScrollPane, BorderLayout.CENTER);
    };

    protected void initContainer() {
        // panelText.setLayout(new BorderLayout());
        // // panelText.add(panelText, BorderLayout.CENTER);
        // panelText.add(panelButton, BorderLayout.SOUTH);
        panel.setBackground(ColorHandler.PRIMARY_COLOR);
        //panel.add(jPanel_RightPanel, BorderLayout.CENTER);

        if (includeScrollPane) { // Modify the condition here
            panel.add(jPanel_LeftPanel_TextArea, BorderLayout.WEST);
        }

        // add(panelButton, BorderLayout.WEST);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // consoleArea.setText(null);
        JButton clickedButton = (JButton) e.getSource();
        btnValue = Integer.parseInt(clickedButton.getActionCommand()); // đây là case bỏ vô main

        System.out.println(ConsoleColors.GREEN + "Action: " + btnValue + ConsoleColors.RESET);

        // Call the controller method with the button value
//        controller.doManagement(btnValue);
    }

    public static int getBtnValue() {
        if (btnValue != 0) {
            return btnValue;
        } else {
            return -1;
        }
    }
}
