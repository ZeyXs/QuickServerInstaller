package fr.zeyx.qsi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("ALL")
public class Window extends JFrame {

    private static final String TITLE = "QuickServerInstaller";
    private static final String VERSION = "1.0-SNAPSHOT";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public Window() {

        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        drawComponents();
        this.setVisible(true);
    }

    private void drawComponents() {
        JLabel title = createLabel(TITLE, 0, 0, WIDTH-16, 30, 25, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

        JLabel subtitle = createLabel(VERSION, 0, 30, WIDTH-16, 30, 10, Font.BOLD);
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setVerticalAlignment(JLabel.TOP);
        this.add(subtitle);

        ArrayList<String> versions = ServerUtils.getVersions();
        Collections.reverse(versions);
        JComboBox versionsBox = new JComboBox(versions.toArray());
        versionsBox.setBounds(0, 0, 80, 20);
        this.add(versionsBox);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int size, int style) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(null, style, size));
        return label;
    }

}
