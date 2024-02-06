package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.Data
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.GoPicture

interface IStock {

    fun queryPresetStockData(gids: List<String>?): List<Data>

    fun queryGidGoPicture(gid: String): GoPicture?
}
