package resources

import java.net.URL

class Sender() {
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
}