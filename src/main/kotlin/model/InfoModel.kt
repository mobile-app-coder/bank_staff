package model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class InfoModel(
    val type: String,
    val amount: String,
    val totalMoney: String
)

object InfoGsonConverter {

    private val gson: Gson by lazy {
        GsonBuilder()
            // Optional: Configure Gson further if needed
            // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // Example date format
            // .serializeNulls() // To serialize null values
            .create()
    }

    fun toJson(info: InfoModel): String? = try {
        gson.toJson(info)
    } catch (e: Exception) {
        e.printStackTrace() // In real app use proper logging
        null
    }

    fun fromJson(jsonString: String): InfoModel? = try {
        gson.fromJson(jsonString, InfoModel::class.java)
    } catch (e: Exception) {
        e.printStackTrace() // In real app use proper logging
        null
    }

    fun toJsonList(infoList: List<InfoModel>): String? = try {
        gson.toJson(infoList)
    } catch (e: Exception) {
        e.printStackTrace() // In real app use proper logging
        null
    }

    fun fromJsonList(jsonString: String): List<InfoModel>? = try {
        val listType = object : TypeToken<List<InfoModel>>() {}.type
        gson.fromJson(jsonString, listType)
    } catch (e: Exception) {
        e.printStackTrace() // In real app use proper logging
        null
    }
}