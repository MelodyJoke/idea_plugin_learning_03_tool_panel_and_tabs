package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.module

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.ConsoleUI
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.JBSplitter

class ViewBars(val project: Project) : SimpleToolWindowPanel(false, true) {

    val consoleUI = ConsoleUI()

    init {
        // 设置窗体侧边栏按钮
        val group = DefaultActionGroup()
        group.add(SettingBar(this))
        group.add(RefreshBar(this))

        val toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false)
        toolbar.targetComponent = this
        setToolbar(toolbar.component)

        // 添加
        val splitter = JBSplitter(false)
        splitter.setAndLoadSplitterProportionKey("main.splitter.key")
        splitter.firstComponent = consoleUI.panel
        splitter.proportion = 0.3f
        setContent(splitter)
    }
}
