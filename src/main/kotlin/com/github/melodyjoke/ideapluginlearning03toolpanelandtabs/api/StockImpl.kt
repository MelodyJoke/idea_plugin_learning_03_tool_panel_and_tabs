package com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api

import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.Data
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.GoPicture
import com.github.melodyjoke.ideapluginlearning03toolpanelandtabs.api.bean.StockResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class StockImpl : IStock {

    companion object {
        const val KEY = "4bc5772802902af54f95e5570bbf0595"

        val GSON: Gson = GsonBuilder().create()
    }

    private val httpClient = HttpClient.newHttpClient()

    override fun queryPresetStockData(gids: List<String>?): List<Data> {
        val result = ArrayList<Data>()

        gids?.forEach {
            val response = get(it)

            val stockResult = GSON.fromJson(response, StockResult::class.java)

            if (stockResult.resultcode == 200) {
                stockResult.result.forEach { item ->
                    result.add(item.data)
                }
            }
        }

        return result
    }

    override fun queryGidGoPicture(gid: String): GoPicture? {
        val response = get(gid)

        val stockResult = GSON.fromJson(response, StockResult::class.java)

        if (stockResult.resultcode == 200) {
            if (stockResult.result.isNotEmpty()) {
                return stockResult.result[0].gopicture
            }
        }

        return null
    }

    private fun get(gid: String): String {
        val uri = URI.create("http://web.juhe.cn:8080/finance/stock/hs?gid=${gid}&key=${KEY}")

        val request = HttpRequest.newBuilder(uri).build()

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body()
    }
}
