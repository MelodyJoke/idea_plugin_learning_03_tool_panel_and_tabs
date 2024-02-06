package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.module.ViewBars
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TabFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, window: ToolWindow) {
        val viewPanel = ViewBars(project)

        val contentFactory = ContentFactory.getInstance()

        val content = contentFactory.createContent(viewPanel, "股票", false)

        window.contentManager.addContent(content, 0)
    }
}
