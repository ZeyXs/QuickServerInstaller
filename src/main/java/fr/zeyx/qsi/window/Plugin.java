package fr.zeyx.qsi.window;

import javax.swing.*;

public class Plugin {

    private final String name;
    private final String versionMin;
    private final boolean checked;

    public Plugin(String name, String versionMin, boolean checked) {
        this.name = name;
        this.versionMin = versionMin;
        this.checked = checked;
    }

    public JCheckBox getCheckBox() {
        JCheckBox checkBox = new JCheckBox(this.name);
        checkBox.setSelected(this.checked);
        return checkBox;
    }

}
