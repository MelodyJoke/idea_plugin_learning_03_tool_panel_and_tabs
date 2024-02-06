package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.data

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "DataSetting", storages = [Storage("plugin.xml")])
class DataSetting : PersistentStateComponent<DataState> {

    private var state: DataState? = null

    companion object {

        @JvmField
        var instance: DataSetting = ApplicationManager.getApplication().getService(DataSetting::class.java)
    }

    fun getGids(): List<String>? = state?.gids

    override fun getState(): DataState? = state

    override fun loadState(state: DataState) {
        this.state = state
    }
}
