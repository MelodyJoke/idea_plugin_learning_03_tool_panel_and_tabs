package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.module

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.GidConfig
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.util.IconLoader


class SettingBar(private val panel: ViewBars) : DumbAwareAction("配置股票", "Click to setting", IconLoader.getIcon("/icons/config.svg", SettingBar::class.java)) {

    override fun actionPerformed(event: AnActionEvent) {
        ShowSettingsUtil.getInstance().editConfigurable(panel.project, GidConfig(panel.consoleUI))
    }
}
