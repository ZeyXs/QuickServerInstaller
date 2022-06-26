package fr.zeyx.qsi.window.panels;

import fr.zeyx.qsi.utils.ServerUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("ALL")
public class ConfigurationPanel extends JPanel implements ActionListener {

    private Border border;

    private JComboBox versionsBox;
    private JComboBox buildsBox;

    public ConfigurationPanel() {
        border = BorderFactory.createTitledBorder("Configuration");
        this.setBounds(20, 60, 340, 180);
        this.setBorder(border);
    }

    public void initComponents() {

        JLabel versionsLabel = new JLabel("Version: ");
        this.add(versionsLabel);

        ArrayList<String> versions = ServerUtils.getVersions();
        Collections.reverse(versions);
        versionsBox = new JComboBox(versions.toArray());
        versionsBox.addActionListener(this);
        this.add(versionsBox);

        JLabel buildsLabel = new JLabel("Build: ");
        this.add(buildsLabel);

        ArrayList<Integer> builds = ServerUtils.getBuilds(versionsBox.getSelectedItem().toString());
        Collections.reverse(builds);
        buildsBox = new JComboBox(builds.toArray());
        this.add(buildsBox);

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
