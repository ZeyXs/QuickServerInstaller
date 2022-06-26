package fr.zeyx.qsi.window.panels;

import fr.zeyx.qsi.window.Plugin;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PluginsPanel extends JPanel {

    private static final String NAME = "Plugins";
    private Border border;

    public PluginsPanel() {
        border = BorderFactory.createTitledBorder("Plugins");

        this.setBorder(border);
        this.setLayout(new GridLayout(2, 1));
    }

    public void initComponents() {

        Plugin fawe = new Plugin("FastAsyncWorldEdit", "1.16.5", true);
        this.add(fawe.getCheckBox());

        Plugin spark = new Plugin("Spark", "1.16.5", true);
        this.add(spark.getCheckBox());

    }

}
