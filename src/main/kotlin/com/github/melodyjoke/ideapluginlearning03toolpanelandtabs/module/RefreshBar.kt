package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.module

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.data.DataSetting
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.util.IconLoader

class RefreshBar(private val panel: ViewBars) : DumbAwareAction("刷新指数", "Click to refresh", IconLoader.getIcon("/icons/refresh.svg", RefreshBar::class.java)) {

    override fun actionPerformed(event: AnActionEvent) {
        panel.consoleUI.addRows(DataSetting.instance.getGids())
    }
}
