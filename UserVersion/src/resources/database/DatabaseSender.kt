package resources.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

class DatabaseSender {
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

    fun authorization(login : String, password : String) : Int { // !!! Если возвращён -1 - авторизация не прошла или произошла ошибка.
        val auth = Auth()
        auth.username = login.replace(" ", "")
        auth.password = password.replace(" ", "")

        val url = "https://yaht.azurewebsites.net/Account/AppLogin?json=${Gson().toJson(auth)}"
        val json = URL(url).openStream().bufferedReader().use{ it.readText() }

        return Gson().fromJson<Int>(json, Int::class.java)
    }

    fun registration(auth: Auth, customer: Customers) : ArrayList<String> {
        val map : MutableMap<String, Any> = mutableMapOf()
        map["auth"] = auth
        map["customer"] = customer

        val url = "https://yaht.azurewebsites.net/Account/AppRegistration?json=${Gson().toJson(map)}"
        val json = URL(url).openStream().bufferedReader().use{ it.readText() }

        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

    fun sendOrder(order : Orders, contract: Contract, details: ArrayList<Details>) {
        val map : MutableMap<String, Any> = mutableMapOf()
        map["orders"] = order
        map["contract"] = contract
        map["details"] = details

        println(Gson().toJson(map))
        val url = "https://yaht.azurewebsites.net/Orders/Set?json=${Gson().toJson(map)}".replace(' ', '_')
        URL(url).openStream().bufferedReader().use{ it.readText() }
    }
}