package fr.zeyx.qsi;

import fr.zeyx.qsi.utils.Server;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
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
    private static final int HEIGHT = 320;

    JComboBox versionsBox;
    JComboBox buildsBox;

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

        JButton install = new JButton("Install");
        install.setBounds((WIDTH-16)/2-40, 245, 80, 25);
        this.add(install);

        Border configurationBorder = BorderFactory.createTitledBorder("Configuration");
        JPanel configuration = new JPanel();
        configuration.setBounds(20, 60, WIDTH - 60, 180);
        configuration.setBorder(configurationBorder);
        this.add(configuration);

        JLabel versionsLabel = new JLabel(SELECT_VERSION);
        configuration.add(versionsLabel);

        ArrayList<String> versions = Server.getVersions();
        Collections.reverse(versions);
        versionsBox = new JComboBox(versions.toArray());
        versionsBox.addActionListener(this);
        configuration.add(versionsBox);

        JLabel buildsLabel = new JLabel(SELECT_BUILDS);
        configuration.add(buildsLabel);

        ArrayList<Integer> builds = Server.getBuilds(versionsBox.getSelectedItem().toString());
        Collections.reverse(builds);
        buildsBox = new JComboBox(builds.toArray());
        configuration.add(buildsBox);

        Border ramBorder = BorderFactory.createTitledBorder("RAM");
        JPanel ram = new JPanel();
        ram.setBounds(20, 60, WIDTH - 60, 180);
        ram.setBorder(ramBorder);
        configuration.add(ram);

        JLabel minRamLabel = new JLabel("Min: ");
        ram.add(minRamLabel);

        JSpinner minRam = new JSpinner();
        minRam.setPreferredSize(new Dimension(80, 20));
        minRam.setValue(512);
        JFormattedTextField formatMinRam = ((JSpinner.NumberEditor) minRam.getEditor()).getTextField();
        ((NumberFormatter) formatMinRam.getFormatter()).setAllowsInvalid(false);
        ram.add(minRam);

        JLabel maxRamLabel = new JLabel("      Max: ");
        ram.add(maxRamLabel);

        JSpinner maxRam = new JSpinner();
        maxRam.setPreferredSize(new Dimension(80, 20));
        maxRam.setValue(1024);
        JFormattedTextField formatMaxRam = ((JSpinner.NumberEditor) maxRam.getEditor()).getTextField();
        ((NumberFormatter) formatMaxRam.getFormatter()).setAllowsInvalid(false);
        ram.add(maxRam);

        Border pluginsBorder = BorderFactory.createTitledBorder("Plugins");
        JPanel plugins = new JPanel();
        plugins.setBounds(20, 60, WIDTH - 60, 180);
        plugins.setBorder(pluginsBorder);
        configuration.add(plugins);

    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int size, int style) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(null, style, size));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() ==  versionsBox) {
            buildsBox.removeAllItems();
            ArrayList<Integer> builds = Server.getBuilds(versionsBox.getSelectedItem().toString());
            Collections.reverse(builds);
            for (Integer build : builds) {
                buildsBox.addItem(build);
            }
        }
    }
}
