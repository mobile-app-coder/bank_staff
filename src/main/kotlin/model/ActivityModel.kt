package model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class ActivityModel(
    val activityID: String,
    val clientName: String,
    val applicationType: String,
    val amount: String,
    val status: String,
    val date: String
)


object ActivityGsonConverter {

    private val gson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter()) // Register custom adapter
            .create()
    }

    class LocalDateAdapter : com.google.gson.TypeAdapter<LocalDate>() {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Define your date format

        override fun write(out: com.google.gson.stream.JsonWriter, value: LocalDate?) {
            if (value == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(value))
            }
        }

        override fun read(input: com.google.gson.stream.JsonReader): LocalDate? {
            return try {
                if (input.peek() == com.google.gson.stream.JsonToken.NULL) {
                    input.nextNull()
                    null
                } else {
                    val dateString = input.nextString()
                    LocalDate.parse(dateString, formatter)
                }
            } catch (e: DateTimeParseException) {
                // Handle parsing errors, e.g., log the error or return a default value
                println("Error parsing date: ${e.message}")
                null // Or throw an exception if you want to enforce strict parsing
            }
        }
    }


    fun toJson(activity: ActivityModel): String? = try {
        gson.toJson(activity)
    } catch (e: Exception) {
        e.printStackTrace(); null
    }

    fun fromJson(jsonString: String): ActivityModel? = try {
        gson.fromJson(jsonString, ActivityModel::class.java)
    } catch (e: Exception) {
        e.printStackTrace(); null
    }

    fun toJsonList(activities: List<ActivityModel>): String? = try {
        gson.toJson(activities)
    } catch (e: Exception) {
        e.printStackTrace(); null
    }

    fun fromJsonList(jsonString: String): List<ActivityModel>? = try {
        val listType = object : TypeToken<List<ActivityModel>>() {}.type
        gson.fromJson(jsonString, listType)
    } catch (e: Exception) {
        e.printStackTrace(); null
    }

}