package fr.zeyx.qsi.window.panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class RAMPanel extends JPanel {

    private Border border;

    public RAMPanel() {
        border = BorderFactory.createTitledBorder("RAM");
        this.setBorder(border);
    }

    public void initComponents() {

        JLabel minRamLabel = new JLabel("Min: ");
        this.add(minRamLabel);

        JSpinner minRam = new JSpinner();
        minRam.setPreferredSize(new Dimension(80, 20));
        minRam.setValue(512);
        JFormattedTextField formatMinRam = ((JSpinner.NumberEditor) minRam.getEditor()).getTextField();
        ((NumberFormatter) formatMinRam.getFormatter()).setAllowsInvalid(false);
        this.add(minRam);

        JLabel maxRamLabel = new JLabel("      Max: ");
        this.add(maxRamLabel);

        JSpinner maxRam = new JSpinner();
        maxRam.setPreferredSize(new Dimension(80, 20));
        maxRam.setValue(1024);
        JFormattedTextField formatMaxRam = ((JSpinner.NumberEditor) maxRam.getEditor()).getTextField();
        ((NumberFormatter) formatMaxRam.getFormatter()).setAllowsInvalid(false);
        this.add(maxRam);

    }

}
