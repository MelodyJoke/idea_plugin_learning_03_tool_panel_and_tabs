package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs;

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.data.DataSetting;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class GidConfig implements Configurable {

    private JPanel mainPanel;

    private JPanel settingPanel;

    private JLabel gidLabel;

    private JTextField gidTextField;

    private final ConsoleUI consoleUI;

    public GidConfig(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    public JTextField getGidTextField() {
        return gidTextField;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Stock";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        List<String> gidsAlready = DataSetting.instance.getGids();

        if (gidsAlready != null) {
            gidsAlready.clear();
        } else {
            gidsAlready = new ArrayList<>();
        }

        String[] gids = gidTextField.getText().trim().split(",");

        for (String gid : gids) {
            gidsAlready.add(gid.trim());
        }

        // 刷新数据
        consoleUI.addRows(gidsAlready);
    }
}
