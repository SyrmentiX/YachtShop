package resources

import com.google.gson.Gson
import java.net.URL

class Sender {
    companion object {
        fun send(tables: Tables, id : Int? = null) : String {
            return try {
                val url = if (id == null) {
                    "https://yaht.azurewebsites.net/Home/Get?name=${tables.tableName}&id=all"
                } else {
                    "https://yaht.azurewebsites.net/Home/Get?name=${tables.tableName}&id=${id}"
                }
                URL(url).openStream().bufferedReader().use{ it.readText() }
            } catch (e : Exception) {
                "[]"
            }
        }
    }

    fun checkAuth(login : String, password : String) : Int { // !!! Если возвращён -1 - авторизация не прошла или произошла ошибка.
        val auth = Auth()
        auth.username = login
        auth.password = password

        val url = "https://yaht.azurewebsites.net/Account/AppLogin?json=${Gson().toJson(auth)}"
        val json = URL(url).openStream().bufferedReader().use{ it.readText() }

        return Gson().fromJson<Int>(json, Int::class.java)
    }
}