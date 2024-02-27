package styles;

import constants.Path;
import views.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageHandler {
    public static final URL iconURL = MyFrame.class.getResource(Path.URL_KEY_ICON);
    public static final Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);
}