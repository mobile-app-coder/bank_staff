package model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class TransactionModel(
    val transactionId: String? = null,
    val date: String,
    val type: String,
    val amount: String,
    val status: String
)

object TransactionGsonConverter {

    private val gson: Gson by lazy {
        GsonBuilder()
            // Optional: Configure Gson further if needed (e.g., date formats, null handling)
            // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // Example date format
            // .serializeNulls() // To serialize null values
            .create()
    }

    fun toJson(transaction: TransactionModel): String? {
        return try {
            gson.toJson(transaction)
        } catch (e: Exception) {
            // Handle JSON conversion errors (e.g., logging)
            e.printStackTrace()
            null
        }
    }

    fun fromJson(jsonString: String): TransactionModel? {
        return try {
            gson.fromJson(jsonString, TransactionModel::class.java)
        } catch (e: Exception) {
            // Handle JSON parsing errors
            e.printStackTrace()
            null
        }
    }

    // For converting a list of TransactionModel objects
    fun toJson(transactions: List<TransactionModel>): String? {
        return try {
            gson.toJson(transactions)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun fromJsonList(jsonString: String): List<TransactionModel>? {
        return try {
            val listType = object : TypeToken<List<TransactionModel>>() {}.type
            gson.fromJson(jsonString, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}