package fr.zeyx.qsi.window;

import fr.zeyx.qsi.utils.ServerUtils;
import fr.zeyx.qsi.window.panels.ConfigurationPanel;
import fr.zeyx.qsi.window.panels.PluginsPanel;
import fr.zeyx.qsi.window.panels.RAMPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("ALL")
public class Window extends JFrame implements ActionListener {

    private static final String TITLE = "QuickServerInstaller";
    private static final String VERSION = "1.0-SNAPSHOT";
    private static final String SELECT_VERSION = "Versions: ";
    private static final String SELECT_BUILDS = "Builds: ";
    private static final String MIN_RAM = "Minimum: ";

    private static final int WIDTH = 400;
    private static final int HEIGHT = 340;

    JComboBox versionsBox;
    JComboBox buildsBox;
    JPanel plugins;

    public Window() {

        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        init();

        this.setVisible(true);
    }

    private void init() {
        ConfigurationPanel configuration = new ConfigurationPanel();
        RAMPanel ram = new RAMPanel();
        PluginsPanel plugins = new PluginsPanel();

        initComponents();

        this.add(configuration);
        configuration.initComponents();

        configuration.add(ram);
        ram.initComponents();

        configuration.add(plugins);
        plugins.initComponents();
    }

    private void initComponents() {
        JLabel title = createLabel(TITLE, 0, 0, WIDTH-16, 30, 25);
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title);

        JLabel subtitle = createLabel(VERSION, 0, 30, WIDTH-16, 30, 10);
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setVerticalAlignment(JLabel.TOP);
        this.add(subtitle);

        JButton install = new JButton("Install");
        install.setBounds((WIDTH-16)/2-40, 270, 80, 25);
        this.add(install);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int size) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(null, Font.BOLD, size));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() ==  versionsBox) {

            // Update builds
            buildsBox.removeAllItems();
            ArrayList<Integer> builds = ServerUtils.getBuilds(versionsBox.getSelectedItem().toString());
            Collections.reverse(builds);
            for (Integer build : builds) {
                buildsBox.addItem(build);
            }

            // Update plugins

        }
    }
}
