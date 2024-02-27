package constants;

import javax.swing.JOptionPane;

public class Notification {

    public static void createCustomMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createReadFileFirstMsg() {
        JOptionPane.showMessageDialog(null, "Please read file first", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createSuccessMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg + "Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createConfirmationMsg(String msg) {
        int result = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            System.out.println("User clicked No");
            JOptionPane.getRootFrame().dispose();
        }
    }

    public static String createInputDialog(String msg) {
        return JOptionPane.showInputDialog(null, msg, "Input",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createResultMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Result",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
