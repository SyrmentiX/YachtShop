package resources.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import resources.*
import resources.database.Customers
import resources.database.DatabaseClassParser
import resources.database.DatabaseGetter
import resources.database.DatabaseSender

const val nonExitedUserError = "Пользователь несуществует"

open class Base {
    var addedYacht : ObservableList<OrderCard> = FXCollections.observableArrayList()
    var isOrderLoaded : Boolean = false
    var isGuest = true
    var user : Customers = Customers()
    val databaseGetter = DatabaseGetter()
    val sender = DatabaseSender()
    val databaseClassParser = DatabaseClassParser()

    init {
        user.firstName = "Kek"
        user.secondName = "Stan"
    }

    fun deleteAllNotLocalYacht() {
        val newList : ObservableList<OrderCard> = FXCollections.observableArrayList()
        for (card in addedYacht) {
            if (card.yacht.isLocal) {
                newList.add(card)
            }
        }
        addedYacht = newList
    }
    //todo add some user info
}