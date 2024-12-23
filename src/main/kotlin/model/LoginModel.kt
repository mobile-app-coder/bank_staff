package model

import com.google.gson.Gson

data class LoginModel(
    val username: String,
    val password: String
) {


}

fun parseJsonToLoginModel(json: String): LoginModel {
    return Gson().fromJson(json, LoginModel::class.java)
}

// Convert LoginModel to JSON string
fun convertLoginModelToJson(model: LoginModel): String {
    return Gson().toJson(model)
}
