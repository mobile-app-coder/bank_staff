package model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class ApplicationModel(
    val applicationID: String,
    val clientName: String,
    val applicationType: String,
    val amount: String,
    val status: String
)


object ApplicationGsonConverter {

    private val gson: Gson by lazy {
        GsonBuilder()
            // Optional: Configure Gson further if needed
            // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // Example date format
            // .serializeNulls() // To serialize null values
            .create()
    }

    fun toJson(application: ApplicationModel): String? = try {
        gson.toJson(application)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun fromJson(jsonString: String): ApplicationModel? = try {
        gson.fromJson(jsonString, ApplicationModel::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun toJsonList(applications: List<ApplicationModel>): String? = try {
        gson.toJson(applications)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun fromJsonList(jsonString: String): List<ApplicationModel>? = try {
        val listType = object : TypeToken<List<ApplicationModel>>() {}.type
        gson.fromJson(jsonString, listType)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}